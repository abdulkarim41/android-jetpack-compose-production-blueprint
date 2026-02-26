package com.abdulkarim.di.authrefresh

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class RefreshTokenApiResponse(
    val accessToken: String,
    val refreshToken: String
)

interface AuthRefreshApiService{

    @POST("auth/refresh")
    fun refreshToken(
        @Body refreshToken: String
    ): Call<RefreshTokenApiResponse>
}

class AuthRefreshServiceHolder{
    private var authRefreshApi: AuthRefreshApiService? = null
    fun getAuthRefreshApi(): AuthRefreshApiService? {
        return authRefreshApi
    }

    fun setAuthRefreshApi(authRefreshApi: AuthRefreshApiService) {
        this.authRefreshApi = authRefreshApi
    }
}
