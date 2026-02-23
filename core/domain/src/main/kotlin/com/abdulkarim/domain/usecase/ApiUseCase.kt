package com.abdulkarim.domain.usecase

import com.abdulkarim.common.base.Result
import kotlinx.coroutines.flow.Flow

interface ApiUseCaseParams<Params, Type> : UseCase {
    suspend fun execute(params: Params): Flow<Result<Type>>
}

interface ApiUseCaseNonParams<Type> : UseCase {
    suspend fun execute(): Flow<Result<Type>>
}