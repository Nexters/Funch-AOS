package com.moya.funch.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.moja.funch.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideJson(): Json =
        Json {
            coerceInputValues = true
        }

    @Singleton
    @Provides
    fun provideJsonConverterFactory(json: Json): Converter.Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            },
        )

    @Singleton
    @Provides
    fun provideOkHttpClient(logInterceptor: Interceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(logInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS).build()

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.FUNCH_DEBUG_BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
}
