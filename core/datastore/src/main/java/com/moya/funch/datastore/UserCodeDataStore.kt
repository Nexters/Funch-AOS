package com.moya.funch.datastore

abstract class UserCodeDataStore {

    abstract var userId: String
    abstract var userCode: String

    abstract fun hasUserCode(): Boolean

    abstract fun clear()
}
