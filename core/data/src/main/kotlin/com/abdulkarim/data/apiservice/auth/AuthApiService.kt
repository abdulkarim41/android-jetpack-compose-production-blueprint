package com.abdulkarim.data.apiservice.auth

import com.abdulkarim.apiresponse.auth.LoginApiResponse
import com.abdulkarim.apiresponse.auth.ProfileApiResponse
import com.abdulkarim.domain.apiusecase.auth.PostLoginApiUseCase
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/login")
    suspend fun postLoginApi(@Body request: PostLoginApiUseCase.Params): Response<LoginApiResponse>

    @GET("auth/me")
    suspend fun fetchProfileApi() : Response<ProfileApiResponse>

}