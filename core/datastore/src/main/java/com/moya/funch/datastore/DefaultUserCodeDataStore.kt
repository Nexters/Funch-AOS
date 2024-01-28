package com.moya.funch.datastore

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@SuppressLint("HardwareIds")
@Singleton
class DefaultUserCodeDataStore @Inject constructor(
    private val preferences: SharedPreferences,
    @ApplicationContext private val context: Context,
) : UserCodeDataStore() {

    override var userId: String
        get() {
            initUserId()
            return preferences.getString(DEVICE_ID, "").orEmpty()
        }
        set(value) {
            preferences.edit(commit = true) {
                putString(DEVICE_ID, value)
            }
        }

    private fun initUserId() {
        if (preferences.contains(DEVICE_ID).not()) {
            userId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }
    }

    override var userCode: String
        get() = preferences.getString(USER_CODE, "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString(USER_CODE, value)
            }
        }

    override fun hasUserCode(): Boolean {
        return preferences.contains(USER_CODE)
    }

    override fun clear() {
        preferences.edit(commit = true) {
            clear()
        }
    }

    private companion object {
        const val DEVICE_ID = "DEVICE_ID"
        const val USER_CODE = "USER_CODE"
    }
}
