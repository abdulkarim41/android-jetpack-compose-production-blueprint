package com.abdulkarim.data.repoimpl.auth

import com.abdulkarim.common.base.Result
import com.abdulkarim.data.apiservice.AuthApiService
import com.abdulkarim.data.mapper.auth.LoginApiMapper
import com.abdulkarim.data.mapper.auth.ProfileApiMapper
import com.abdulkarim.data.mapper.mapFromApiResponse
import com.abdulkarim.data.wrapper.NetworkBoundResource
import com.abdulkarim.datastore.DatastorePreferences
import com.abdulkarim.domain.apiusecase.auth.PostLoginApiUseCase
import com.abdulkarim.domain.repository.auth.AuthRepository
import com.abdulkarim.entity.auth.LoginApiEntity
import com.abdulkarim.entity.auth.ProfileApiEntity
import com.abdulkarim.securestorage.SecureStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val networkBoundResource: NetworkBoundResource,
    private val secureStorage: SecureStorage,
    private val datastorePreferences: DatastorePreferences,
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
                datastorePreferences.setUserLoggedIn(true)
                secureStorage.saveAccessToken(result.data.accessToken)
                secureStorage.saveRefreshToken(result.data.refreshToken)
            }
        }
    }

    override suspend fun fetchProfileApi(): Flow<Result<ProfileApiEntity>> {
        return mapFromApiResponse(
            result = networkBoundResource.fetchData {
                authApiService.fetchProfileApi()
            },
            mapper = profileApiMapper
        ).onEach {
            if (it is Result.Success){
                datastorePreferences.cacheProfile(it.data)
            }
        }
    }

}