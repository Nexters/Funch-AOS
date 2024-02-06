package com.moya.funch.datasource.local

import com.moya.funch.datasource.DummyDataSource

class DummyLocalDataSource : DummyDataSource {
    override fun getDummyData(): String {
        return "Local Data"
    }
}
