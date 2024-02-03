package com.moya.funch.datastore

interface UserCodeDataStore {

    var userId: String
    var userCode: String
    var deviceId: String

    fun hasUserCode(): Boolean

    fun hasUserId(): Boolean

    fun clear()
}
