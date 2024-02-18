package com.moya.funch.di

import com.moya.funch.repository.SubwayRepository
import com.moya.funch.repository.SubwayRepositoryImpl
import com.moya.funch.usecase.LoadSubwayStationsUseCase
import com.moya.funch.usecase.LoadSubwayStationsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SubwayModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface UseCaseBinder {
        @Binds
        @Singleton
        fun bindLoadSubwayUseCase(useCase: LoadSubwayStationsUseCaseImpl): LoadSubwayStationsUseCase
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryBinder {
        @Binds
        @Singleton
        fun bindSubwayRepository(repository: SubwayRepositoryImpl): SubwayRepository
    }
}
