package com.moya.funch.datastore.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.moja.funch.datastore.BuildConfig
import com.moya.funch.datastore.DefaultUserCodeDataStore
import com.moya.funch.datastore.UserCodeDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.security.GeneralSecurityException
import java.security.KeyStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    private const val DEBUG_DATASTORE_KEY = "DEBUG_DATASTORE_KEY"
    private const val DATASTORE_KEY = "DATASTORE_KEY"
    private const val ANDROID_KEY_STORE = "AndroidKeyStore"

    @Provides
    @Singleton
    fun provideAppPreferences(
        @ApplicationContext context: Context,
    ): SharedPreferences = if (BuildConfig.DEBUG) {
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

    private fun deletePreference(fileName: String, context: Context) {
        context.deleteSharedPreferences(fileName)
    }

    private fun deleteMasterKeyEntry() {
        KeyStore.getInstance(ANDROID_KEY_STORE).apply {
            load(null)
            deleteEntry(MasterKey.DEFAULT_MASTER_KEY_ALIAS)
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
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM

        )
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class Binder {

        @Binds
        abstract fun bindAppPreferences(dataStore: DefaultUserCodeDataStore): UserCodeDataStore
    }
}
