package com.abdulkarim.data.repoimpl.auth

import android.util.Log
import com.abdulkarim.common.base.Result
import com.abdulkarim.data.apiservice.AuthApiService
import com.abdulkarim.data.mapper.auth.LoginApiMapper
import com.abdulkarim.data.mapper.auth.ProfileApiMapper
import com.abdulkarim.data.mapper.mapFromApiResponse
import com.abdulkarim.data.wrapper.NetworkBoundResource
import com.abdulkarim.domain.apiusecase.auth.PostLoginApiUseCase
import com.abdulkarim.domain.repository.auth.AuthRepository
import com.abdulkarim.entity.auth.LoginApiEntity
import com.abdulkarim.entity.auth.ProfileApiEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val networkBoundResource: NetworkBoundResource,
    private val authApiService: AuthApiService,
    private val loginApiMapper: LoginApiMapper,
    private val profileApiMapper: ProfileApiMapper,

    ) : AuthRepository {

    override suspend fun postLoginApi(params: PostLoginApiUseCase.Params): Flow<Result<LoginApiEntity>> {
        return mapFromApiResponse(
            result = networkBoundResource.fetchData { authApiService.postLoginApi(params) },
            mapper = loginApiMapper
        ).onEach { result ->
                if (result is Result.Success){
                    Log.d("KKK", "access token ${result.data.accessToken}")
                }
            }
    }

    override suspend fun fetchProfileApi(): Flow<Result<ProfileApiEntity>> {
        return mapFromApiResponse(
            result = networkBoundResource.fetchData {
                authApiService.fetchProfileApi()
            },
            mapper = profileApiMapper
        )
    }

}