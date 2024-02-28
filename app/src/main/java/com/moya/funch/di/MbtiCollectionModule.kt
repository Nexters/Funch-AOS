package com.moya.funch.di

import com.moya.funch.repository.MbtiCollectionRepository
import com.moya.funch.repository.MbtiCollectionRepositoryImpl
import com.moya.funch.usecase.LoadMbtiCollectionUseCase
import com.moya.funch.usecase.LoadMbtiCollectionUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MbtiCollectionModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface UseCaseBinder {
        @Binds
        @Singleton
        fun bindLoadMbtiCollectionUseCase(useCase: LoadMbtiCollectionUseCaseImpl): LoadMbtiCollectionUseCase
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryBinder {
        @Binds
        @Singleton
        fun bindMbtiCollectionRepository(repository: MbtiCollectionRepositoryImpl): MbtiCollectionRepository
    }
}
