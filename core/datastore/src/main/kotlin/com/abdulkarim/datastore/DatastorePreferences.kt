package com.abdulkarim.datastore

import com.abdulkarim.entity.auth.ProfileApiEntity
import kotlinx.coroutines.flow.Flow

interface DatastorePreferences {

    val isUserLoggedIn: Flow<Boolean>

    val isOnboardingLaunched: Flow<Boolean>

    suspend fun setUserLoggedIn(value: Boolean)

    suspend fun setOnboardingLaunched(value: Boolean)

    suspend fun cacheProfile(profile: ProfileApiEntity)
    fun getCachedProfile(): Flow<ProfileApiEntity?>

    suspend fun clearAll()
}