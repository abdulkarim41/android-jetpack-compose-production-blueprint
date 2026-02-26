package com.abdulkarim.di.module

import com.abdulkarim.di.authrefresh.AuthenticationRefreshToken
import com.abdulkarim.securestorage.SecureStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OkHttpModule {

    @Provides
    @Singleton
    fun provideLoggerInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message -> Timber.e(message) }
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.HEADERS }
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        return interceptor
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggerInterceptor: HttpLoggingInterceptor,
        secureStorage: SecureStorage,
        refreshToken: AuthenticationRefreshToken,
    ): OkHttpClient {
        val accessToken = secureStorage.getAccessToken()
        val timeOut = 30
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .writeTimeout(timeOut.toLong(), TimeUnit.SECONDS)

        httpClient.authenticator(refreshToken)
        httpClient.addInterceptor(loggerInterceptor)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer $accessToken")
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        return httpClient.build()
    }
}