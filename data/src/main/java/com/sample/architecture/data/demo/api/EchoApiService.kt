package com.sample.architecture.data.demo.api

import com.sample.architecture.data.demo.api.response.EchoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EchoApiService {
    @GET("test")
    suspend fun echo(@Query("string") string: String): EchoResponse
}