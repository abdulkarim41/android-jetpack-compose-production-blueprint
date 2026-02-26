package com.abdulkarim.apiresponse.auth

data class LoginApiResponse(
    val id: Int?,
    val username: String?,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val gender: String?,
    val image: String?,
    val accessToken: String?,
    val refreshToken: String?,
)
