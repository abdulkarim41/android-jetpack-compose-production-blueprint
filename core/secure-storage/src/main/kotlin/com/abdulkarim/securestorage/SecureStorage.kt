package com.abdulkarim.securestorage

interface SecureStorage {

    fun saveAccessToken(token: String)
    fun getAccessToken(): String

    fun saveRefreshToken(token: String)
    fun getRefreshToken(): String
    suspend fun clear()
}