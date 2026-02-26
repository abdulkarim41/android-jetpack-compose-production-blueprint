package com.abdulkarim.entity.auth

data class ProfileApiEntity(
    val email: String,
    val firstName: String,
    val lastName: String,
    val maidenName: String,
    val phone: String,
    val gender: String,
    val image: String,
)
