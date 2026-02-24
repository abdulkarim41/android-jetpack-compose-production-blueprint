package com.abdulkarim.domain.apiusecase.auth

import com.abdulkarim.domain.repository.auth.AuthRepository
import com.abdulkarim.domain.usecase.ApiUseCaseParams
import com.abdulkarim.entity.auth.LoginApiEntity
import kotlinx.coroutines.flow.Flow
import com.abdulkarim.common.base.Result
import javax.inject.Inject

class PostLoginApiUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) : ApiUseCaseParams<PostLoginApiUseCase.Params, LoginApiEntity> {

    data class Params(
        val username: String,
        val password: String,
        //val expiresInMins: Int = 60
    )

    override suspend fun execute(params: Params): Flow<Result<LoginApiEntity>> {
        return authRepository.postLoginApi(params)
    }
}

