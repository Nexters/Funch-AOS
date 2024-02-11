package com.moya.funch.di

import com.moya.funch.repository.MatchingRepository
import com.moya.funch.repository.MatchingRepositoryImpl
import com.moya.funch.usecase.CanMatchProfileUseCase
import com.moya.funch.usecase.CanMatchProfileUseCaseImpl
import com.moya.funch.usecase.MatchProfileUseCase
import com.moya.funch.usecase.MatchProfileUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MatchModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface UseCaseBinder {
        @Binds
        @Singleton
        fun bindCanMatchUseCase(useCase: CanMatchProfileUseCaseImpl): CanMatchProfileUseCase

        @Binds
        @Singleton
        fun bindMatchProfileUseCase(useCase: MatchProfileUseCaseImpl): MatchProfileUseCase
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryBinder {
        @Binds
        @Singleton
        fun bindMatchingRepository(repository: MatchingRepositoryImpl): MatchingRepository
    }
}
