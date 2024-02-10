package com.moya.funch.datastore.di

import android.content.SharedPreferences
import com.moya.funch.datastore.PreferenceFactory
import com.moya.funch.datastore.UserDataStore
import com.moya.funch.datastore.UserDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideAppPreferences(factory: PreferenceFactory): SharedPreferences = factory.create()

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class Binder {
        @Binds
        @Singleton
        abstract fun bindUserDataStore(userDataStore: UserDataStoreImpl): UserDataStore
    }
}
