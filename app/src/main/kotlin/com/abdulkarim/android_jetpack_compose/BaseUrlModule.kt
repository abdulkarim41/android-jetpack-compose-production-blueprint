package com.abdulkarim.android_jetpack_compose

import com.abdulkarim.di.qualifer.AppBaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BaseUrlModule{

//    @Singleton
//    @Provides
//    @AppBaseUrl
//    fun provideBaseUrl():String = "https://jsonplaceholder.typicode.com/"

    @Singleton
    @Provides
    @AppBaseUrl
    fun provideBaseUrl():String = "https://dummyjson.com/"

}