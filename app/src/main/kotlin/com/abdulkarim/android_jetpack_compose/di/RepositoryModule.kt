package com.abdulkarim.android_jetpack_compose.di

import com.abdulkarim.data.repoimpl.auth.AuthRepoImpl
import com.abdulkarim.data.repoimpl.product.ProductRepoImpl
import com.abdulkarim.domain.repository.auth.AuthRepository
import com.abdulkarim.domain.repository.product.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAuthRepository(authRepoImpl: AuthRepoImpl): AuthRepository

    @Binds
    fun bindRepository(productRepoImpl: ProductRepoImpl): ProductRepository

}