package com.abdulkarim.data.apiservice

import com.abdulkarim.apiresponse.auth.LoginApiResponse
import com.abdulkarim.apiresponse.auth.ProfileApiResponse
import com.abdulkarim.domain.apiusecase.auth.PostLoginApiUseCase
import retrofit2.Response
import retrofit2.http.*

interface AuthApiService {

    @POST("auth/login")
    suspend fun postLoginApi(@Body request: PostLoginApiUseCase.Params): Response<LoginApiResponse>

    @GET("auth/me")
    suspend fun fetchProfileApi() : Response<ProfileApiResponse>

}