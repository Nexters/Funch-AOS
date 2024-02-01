package com.moya.funch.network.di

import com.moya.funch.network.service.MatchingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesMatchingService(retrofit: Retrofit): MatchingService = retrofit.create()
}
