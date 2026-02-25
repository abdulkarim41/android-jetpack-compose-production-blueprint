package com.abdulkarim.entity.auth

data class ProfileApiEntity(
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
)
