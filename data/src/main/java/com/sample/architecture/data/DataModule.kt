package com.sample.architecture.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sample.architecture.data.demo.api.EchoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideAnalyticsService(
        // Potential dependencies of this type
    ): EchoApiService {
        return Retrofit.Builder()
            .baseUrl("https://echo.free.beeceptor.com/")
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()
            .create(EchoApiService::class.java)
    }
}