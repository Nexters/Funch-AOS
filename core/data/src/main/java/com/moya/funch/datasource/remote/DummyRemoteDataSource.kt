package com.moya.funch.datasource.remote

import com.moya.funch.datasource.DummyDataSource

class DummyRemoteDataSource : DummyDataSource {
    override fun getDummyData(): String {
        return "Remote Data"
    }
}
