package com.abdulkarim.datastore

import android.content.Context
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
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
        val IS_USER_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val IS_ONBOARDING_LAUNCHED= booleanPreferencesKey("is_onboarding_completed")
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

    override suspend fun clearAll() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}