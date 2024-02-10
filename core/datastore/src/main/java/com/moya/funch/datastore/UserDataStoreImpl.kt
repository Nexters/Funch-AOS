package com.moya.funch.datastore

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@SuppressLint("HardwareIds")
class UserDataStoreImpl @Inject constructor(
    private val preferences: SharedPreferences,
    @ApplicationContext private val context: Context
) : UserDataStore {
    override var deviceId: String
        get() {
            initDeviceId()
            return preferences.getString(DEVICE_ID, "").orEmpty()
        }
        set(value) {
            preferences.edit(commit = true) {
                putString(DEVICE_ID, value)
            }
        }

    override var userCode: String
        get() = preferences.getString(USER_CODE, "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString(USER_CODE, value)
            }
        }
    override var userId: String
        get() = preferences.getString(USER_ID, "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString(USER_ID, value)
            }
        }

    override var userName: String
        get() = preferences.getString(USER_NAME, "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString(USER_NAME, value)
            }
        }
    override var jobGroup: String
        get() = preferences.getString(JOB_GROUP, "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString(JOB_GROUP, value)
            }
        }
    override var bloodType: String
        get() = preferences.getString(BLOOD_TYPE, "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString(BLOOD_TYPE, value)
            }
        }

    override var clubs: Set<String>
        get() = preferences.getStringSet(CLUBS, setOf()).orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putStringSet(CLUBS, value)
            }
        }

    override var subwayName: String
        get() = preferences.getString(SUBWAY_NAME, "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString(SUBWAY_NAME, value)
            }
        }
    override var subwayLines: Set<String>
        get() = preferences.getStringSet(SUBWAY_LINE, setOf()).orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putStringSet(SUBWAY_LINE, value)
            }
        }

    override var mbti: String
        get() = preferences.getString(MBTI, "").orEmpty()
        set(value) {
            preferences.edit(commit = true) {
                putString(MBTI, value)
            }
        }

    override fun hasUserCode(): Boolean {
        return preferences.contains(USER_CODE)
    }

    override fun hasUserId(): Boolean {
        return preferences.contains(USER_ID)
    }

    override fun clear() {
        preferences.edit(commit = true) {
            clear()
        }
    }

    private fun initDeviceId() {
        if (preferences.contains(DEVICE_ID).not()) {
            deviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }
    }

    private companion object {
        const val DEVICE_ID = "DEVICE_ID"
        const val USER_CODE = "USER_CODE"
        const val USER_ID = "USER_ID"
        const val USER_NAME = "USER_NAME"
        const val JOB_GROUP = "JOB_GROUP"
        const val BLOOD_TYPE = "BLOOD_TYPE"
        const val CLUBS = "CLUBS"
        const val SUBWAY_NAME = "SUBWAY_NAME"
        const val SUBWAY_LINE = "SUBWAY_LINE"
        const val MBTI = "MBTI"
    }
}
