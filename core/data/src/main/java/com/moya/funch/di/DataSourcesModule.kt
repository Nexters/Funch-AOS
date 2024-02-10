package com.moya.funch.di

import com.moya.funch.datasource.local.LocalUserDataSource
import com.moya.funch.datasource.local.LocalUserDataSourceImpl
import com.moya.funch.datasource.remote.RemoteMemberDataSource
import com.moya.funch.datasource.remote.RemoteMemberDataSourceImpl
import com.moya.funch.datasource.remote.RemoteUserDataSource
import com.moya.funch.datasource.remote.RemoteUserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    @Singleton
    abstract fun provideRemoteUserDataSource(remoteDataSource: RemoteUserDataSourceImpl): RemoteUserDataSource

    @Binds
    @Singleton
    abstract fun provideLocalUserDataSource(localDataSource: LocalUserDataSourceImpl): LocalUserDataSource

    @Binds
    @Singleton
    abstract fun provideMemberDataSource(remoteDataSource: RemoteMemberDataSourceImpl): RemoteMemberDataSource
}
