package com.abdulkarim.data.apiservice

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
    fun providePostApiService(
        @AppBaseUrl retrofit: Retrofit,
    ): PostApiService {
        return retrofit.create(PostApiService::class.java)
    }

}