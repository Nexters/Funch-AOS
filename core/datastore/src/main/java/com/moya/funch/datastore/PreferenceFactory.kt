package com.moya.funch.datastore

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.moja.funch.datastore.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import java.security.GeneralSecurityException
import java.security.KeyStore
import javax.inject.Inject

class PreferenceFactory
    @Inject
    constructor(
        @ApplicationContext private val context: Context,
    ) {
        fun create(): SharedPreferences {
            return if (BuildConfig.DEBUG) {
                context.getSharedPreferences(DEBUG_DATASTORE_KEY, Context.MODE_PRIVATE)
            } else {
                try {
                    createEncryptedSharedPreferences(DATASTORE_KEY, context)
                } catch (e: GeneralSecurityException) {
                    deleteMasterKeyEntry()
                    deletePreference(DATASTORE_KEY, context)
                    createEncryptedSharedPreferences(DATASTORE_KEY, context)
                }
            }
        }

        private fun createEncryptedSharedPreferences(
            fileName: String,
            context: Context,
        ): SharedPreferences {
            return EncryptedSharedPreferences.create(
                context,
                fileName,
                MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build(),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
            )
        }

        private fun deletePreference(
            fileName: String,
            context: Context,
        ) {
            context.deleteSharedPreferences(fileName)
        }

        private fun deleteMasterKeyEntry() {
            KeyStore.getInstance(ANDROID_KEY_STORE).apply {
                load(null)
                deleteEntry(MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            }
        }

        companion object {
            private const val DEBUG_DATASTORE_KEY = "DEBUG_DATASTORE_KEY"
            private const val DATASTORE_KEY = "DATASTORE_KEY"
            private const val ANDROID_KEY_STORE = "AndroidKeyStore"
        }
    }
