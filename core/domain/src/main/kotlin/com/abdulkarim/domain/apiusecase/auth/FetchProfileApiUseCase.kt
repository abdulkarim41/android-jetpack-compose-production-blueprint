package com.abdulkarim.domain.apiusecase.auth

import com.abdulkarim.common.base.Result
import com.abdulkarim.domain.repository.auth.AuthRepository
import com.abdulkarim.domain.usecase.ApiUseCaseNonParams
import com.abdulkarim.entity.auth.ProfileApiEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchProfileApiUseCase @Inject constructor(
    private val authRepository: AuthRepository,
): ApiUseCaseNonParams<ProfileApiEntity> {

    override suspend fun execute(): Flow<Result<ProfileApiEntity>> {
        return authRepository.fetchProfileApi()
    }

}

