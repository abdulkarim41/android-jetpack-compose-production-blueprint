package com.abdulkarim.securestorage

interface SecureStorage {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
    suspend fun clear()
}