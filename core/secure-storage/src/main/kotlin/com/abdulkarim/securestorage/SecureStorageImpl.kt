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
        private const val KEY_IV = "token_iv"
        private const val KEY_TOKEN = "token_value"
    }

    private val prefs =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private val cryptoManager: CryptoManager

    init {
        KeyStoreHelper.generateKeyIfNeeded(KEY_ALIAS)
        cryptoManager = CryptoManager(KEY_ALIAS)
    }

    override suspend fun saveToken(token: String) {
        val (iv, encrypted) = cryptoManager.encrypt(token)

        prefs.edit {
            putString(KEY_IV, iv)
                .putString(KEY_TOKEN, encrypted)
        }
    }

    override suspend fun getToken(): String? {
        val iv = prefs.getString(KEY_IV, null) ?: return null
        val encrypted = prefs.getString(KEY_TOKEN, null) ?: return null

        return cryptoManager.decrypt(iv, encrypted)
    }

    override suspend fun clear() {
        prefs.edit { clear() }
    }
}