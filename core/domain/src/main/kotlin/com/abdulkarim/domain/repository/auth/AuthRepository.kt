package com.abdulkarim.domain.repository.auth

import com.abdulkarim.common.base.Result
import com.abdulkarim.domain.apiusecase.auth.PostLoginApiUseCase
import com.abdulkarim.entity.auth.LoginApiEntity
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun postLoginApi(params: PostLoginApiUseCase.Params): Flow<Result<LoginApiEntity>>

}