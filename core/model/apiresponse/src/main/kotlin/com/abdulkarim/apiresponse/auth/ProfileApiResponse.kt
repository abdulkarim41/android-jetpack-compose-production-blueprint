package com.abdulkarim.apiresponse.auth

data class ProfileApiResponse(
    val id: Int?,
    val username: String?,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val gender: String?,
    val image: String?,
)
