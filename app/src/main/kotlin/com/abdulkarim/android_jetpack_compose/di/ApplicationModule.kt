package com.abdulkarim.android_jetpack_compose.di

import com.abdulkarim.di.authrefresh.AuthRefreshServiceHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideAuthRefreshServiceHolder() : AuthRefreshServiceHolder = AuthRefreshServiceHolder()

}