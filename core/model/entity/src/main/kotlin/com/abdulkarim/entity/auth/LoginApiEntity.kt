package com.abdulkarim.entity.auth

data class LoginApiEntity(
    val accessToken: String,
    val refreshToken : String
)