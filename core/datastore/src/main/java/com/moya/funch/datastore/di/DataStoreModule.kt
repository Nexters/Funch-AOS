package com.moya.funch.datastore.di

import android.content.SharedPreferences
import com.moya.funch.datastore.DefaultUserCodeDataStore
import com.moya.funch.datastore.PreferenceFactory
import com.moya.funch.datastore.UserCodeDataStore
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
        abstract fun bindAppPreferences(dataStore: DefaultUserCodeDataStore): UserCodeDataStore
    }
}
