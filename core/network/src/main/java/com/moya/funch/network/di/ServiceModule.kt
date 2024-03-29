package com.moya.funch.network.di

import com.moya.funch.network.service.MatchingService
import com.moya.funch.network.service.MemberService
import com.moya.funch.network.service.SubwayService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesMatchingService(retrofit: Retrofit): MatchingService = retrofit.create()

    @Provides
    @Singleton
    fun providesMemberService(retrofit: Retrofit): MemberService = retrofit.create()

    @Provides
    @Singleton
    fun providesSubwayStationService(retrofit: Retrofit): SubwayService = retrofit.create()
}
