package com.abdulkarim.apiresponse.auth

data class ProfileApiResponse(
    val email: String?,
    val phone: String?,
    val firstName: String?,
    val maidenName: String?,
    val lastName: String?,
    val gender: String?,
    val image: String?,
)
