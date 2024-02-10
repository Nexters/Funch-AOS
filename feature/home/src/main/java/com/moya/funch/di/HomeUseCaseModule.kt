package com.moya.funch.di

import com.moya.funch.usecase.CanMatchProfileUseCase
import com.moya.funch.usecase.CanMatchProfileUseCaseImpl
import com.moya.funch.usecase.LoadUserProfileUseCase
import com.moya.funch.usecase.LoadUserProfileUseCaseImpl
import com.moya.funch.usecase.LoadViewCountUseCase
import com.moya.funch.usecase.LoadViewCountUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeUseCaseModule {
    @Binds
    @ViewModelScoped
    abstract fun provideCanMatchUseCase(canMatchProfileUseCase: CanMatchProfileUseCaseImpl): CanMatchProfileUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideLoadUserProfileUseCase(loadUserProfileUseCase: LoadUserProfileUseCaseImpl): LoadUserProfileUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideLoadViewCountUseCase(loadViewCountUseCase: LoadViewCountUseCaseImpl): LoadViewCountUseCase
}
