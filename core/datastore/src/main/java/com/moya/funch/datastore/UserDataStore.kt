package com.moya.funch.datastore

interface UserDataStore {
    var deviceId: String
    var userCode: String
    var userId: String
    var userName: String
    var jobGroup: String
    var bloodType: String
    var clubs: Set<String>
    var subwayName: String
    var subwayLines: Set<String>
    var mbti: String
    var mbtiCollection: Set<String>

    fun hasUserCode(): Boolean

    fun hasUserId(): Boolean

    fun clear()
}
