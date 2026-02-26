package com.abdulkarim.securestorage

interface SecureStorage {

    suspend fun saveAccessToken(token: String)
    suspend fun getAccessToken(): String

    suspend fun saveRefreshToken(token: String)
    suspend fun getRefreshToken(): String
    suspend fun clear()
}