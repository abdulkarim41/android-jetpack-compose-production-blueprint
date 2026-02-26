package com.abdulkarim.securestorage

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class SecureStorageImpl @Inject constructor(
    @ApplicationContext context: Context
) : SecureStorage {

    companion object {
        private const val PREF_NAME = "secure_storage"
        private const val KEY_ALIAS = "secure_token_key"
        private const val KEY_ACCESS_IV = "access_iv"
        private const val KEY_REFRESH_IV = "refresh_iv"
        private const val KEY_ACCESS_TOKEN = "access_token_value"
        private const val KEY_REFRESH_TOKEN = "refresh_token_value"
    }

    private val prefs =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private val cryptoManager: CryptoManager

    init {
        KeyStoreHelper.generateKeyIfNeeded(KEY_ALIAS)
        cryptoManager = CryptoManager(KEY_ALIAS)
    }

    override suspend fun saveAccessToken(token: String) {
        val (iv, encrypted) = cryptoManager.encrypt(token)
        prefs.edit {
            putString(KEY_ACCESS_IV, iv)
            putString(KEY_ACCESS_TOKEN, encrypted)
            commit()
        }
    }

    override suspend fun getAccessToken(): String {
        val iv = prefs.getString(KEY_ACCESS_IV, null) ?: return ""
        val encrypted = prefs.getString(KEY_ACCESS_TOKEN, null) ?: return ""

        return cryptoManager.decrypt(iv, encrypted)
    }

    override suspend fun saveRefreshToken(token: String) {
        val (iv, encrypted) = cryptoManager.encrypt(token)
        prefs.edit {
            putString(KEY_REFRESH_IV, iv)
            putString(KEY_REFRESH_TOKEN, encrypted)
            commit()
        }
    }

    override suspend fun getRefreshToken(): String {
        val iv = prefs.getString(KEY_REFRESH_IV, null) ?: return ""
        val encrypted = prefs.getString(KEY_REFRESH_TOKEN, null) ?: return ""

        return cryptoManager.decrypt(iv, encrypted)
    }

    override suspend fun clear() {
        prefs.edit { clear() }
    }
}