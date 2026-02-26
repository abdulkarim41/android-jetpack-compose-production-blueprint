package com.abdulkarim.datastore

import android.content.Context
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.abdulkarim.entity.auth.ProfileApiEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val DATASTORE_NAME = "com.abdulkarim.core_datastore"

private val Context.dataStore by preferencesDataStore(
    name = DATASTORE_NAME
)

@Singleton
class DatastorePreferencesImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DatastorePreferences {

    private object Keys {
        val IS_USER_LOGGED_IN = booleanPreferencesKey("is_user_logged_in")
        val IS_ONBOARDING_LAUNCHED= booleanPreferencesKey("is_onboarding_launched")
    }

    private object ProfileKeys {
        val FIRST_NAME = stringPreferencesKey("first_name")
        val LAST_NAME = stringPreferencesKey("last_name")
        val MAIDEN_NAME = stringPreferencesKey("maiden_name")
        val EMAIL = stringPreferencesKey("email")
        val PHONE = stringPreferencesKey("phone")
        val AVATAR = stringPreferencesKey("avatar")
        val GENDER = stringPreferencesKey("gender")
    }

    override val isUserLoggedIn: Flow<Boolean> =
        context.dataStore.data
            .catch { exception ->
                if (exception is IOException) emit(emptyPreferences())
                else throw exception
            }
            .map { preferences ->
                preferences[Keys.IS_USER_LOGGED_IN] ?: false
            }

    override val isOnboardingLaunched: Flow<Boolean> =
        context.dataStore.data
            .catch { exception ->
                if (exception is IOException) emit(emptyPreferences())
                else throw exception
            }
            .map { preferences ->
                preferences[Keys.IS_ONBOARDING_LAUNCHED] ?: false
            }

    override suspend fun setUserLoggedIn(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[Keys.IS_USER_LOGGED_IN] = value
        }
    }

    override suspend fun setOnboardingLaunched(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[Keys.IS_ONBOARDING_LAUNCHED] = value
        }
    }

    override suspend fun cacheProfile(profile: ProfileApiEntity) {
        context.dataStore.edit { preferences ->
            preferences[ProfileKeys.FIRST_NAME] = profile.firstName
            preferences[ProfileKeys.LAST_NAME] = profile.lastName
            preferences[ProfileKeys.MAIDEN_NAME] = profile.maidenName
            preferences[ProfileKeys.EMAIL] = profile.email
            preferences[ProfileKeys.PHONE] = profile.phone
            preferences[ProfileKeys.AVATAR] = profile.image
            preferences[ProfileKeys.GENDER] = profile.gender
        }
    }

    override fun getCachedProfile(): Flow<ProfileApiEntity?> {
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) emit(emptyPreferences())
                else throw exception
            }
            .map { preferences ->

                val firstName = preferences[ProfileKeys.FIRST_NAME]
                val lastName = preferences[ProfileKeys.LAST_NAME]
                val maidenName = preferences[ProfileKeys.MAIDEN_NAME]
                val email = preferences[ProfileKeys.EMAIL]
                val phone = preferences[ProfileKeys.PHONE]
                val avatar = preferences[ProfileKeys.AVATAR]
                val gender = preferences[ProfileKeys.GENDER]

                ProfileApiEntity(
                    firstName = firstName ?: "",
                    lastName = lastName ?: "",
                    maidenName = maidenName ?: "",
                    phone = phone ?: "",
                    email = email ?: "",
                    image = avatar ?: "",
                    gender = gender ?: ""
                )
            }
    }

    override suspend fun clearAll() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}