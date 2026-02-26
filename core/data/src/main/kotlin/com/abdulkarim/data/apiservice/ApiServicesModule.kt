package com.abdulkarim.data.apiservice

import com.abdulkarim.data.apiservice.auth.AuthApiService
import com.abdulkarim.data.apiservice.product.ProductApiService
import com.abdulkarim.di.authrefresh.AuthRefreshApiService
import com.abdulkarim.di.authrefresh.AuthRefreshServiceHolder
import com.abdulkarim.di.qualifer.AppBaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServicesModule {

    @Provides
    @Singleton
    fun provideAuthApiService(
        @AppBaseUrl retrofit: Retrofit,
        authRefreshServiceHolder: AuthRefreshServiceHolder
    ): AuthApiService {
        authRefreshServiceHolder.setAuthRefreshApi(retrofit.create(AuthRefreshApiService::class.java))
        return retrofit.create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApiService(
        @AppBaseUrl retrofit: Retrofit,
        authRefreshServiceHolder: AuthRefreshServiceHolder
    ): ProductApiService {
        authRefreshServiceHolder.setAuthRefreshApi(retrofit.create(AuthRefreshApiService::class.java))
        return retrofit.create(ProductApiService::class.java)
    }

}