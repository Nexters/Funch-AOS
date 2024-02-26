package com.moya.funch.di

import com.moya.funch.repository.MemberRepository
import com.moya.funch.repository.MemberRepositoryImpl
import com.moya.funch.usecase.CreateUserProfileUseCase
import com.moya.funch.usecase.CreateUserProfileUseCaseImpl
import com.moya.funch.usecase.DeleteUserProfileUseCase
import com.moya.funch.usecase.DeleteUserProfileUseCaseImpl
import com.moya.funch.usecase.LoadUserProfileUseCase
import com.moya.funch.usecase.LoadUserProfileUseCaseImpl
import com.moya.funch.usecase.LoadViewCountUseCase
import com.moya.funch.usecase.LoadViewCountUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MemberModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface UseCaseBinder {
        @Binds
        @Singleton
        fun bindLoadUserProfileUseCase(useCase: LoadUserProfileUseCaseImpl): LoadUserProfileUseCase

        @Binds
        @Singleton
        fun bindLoadViewCountUseCase(useCase: LoadViewCountUseCaseImpl): LoadViewCountUseCase

        @Binds
        @Singleton
        fun bindCreateUserProfileUseCase(useCase: CreateUserProfileUseCaseImpl): CreateUserProfileUseCase

        @Binds
        @Singleton
        fun bindDeleteUserProfileUseCase(useCase: DeleteUserProfileUseCaseImpl): DeleteUserProfileUseCase
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryBinder {
        @Binds
        @Singleton
        fun bindMemberRepository(repository: MemberRepositoryImpl): MemberRepository
    }
}
