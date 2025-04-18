package com.sample.architecture.data.demo.repository

import com.sample.architecture.data.demo.api.EchoApiService
import com.sample.architecture.data.demo.api.response.toModel
import com.sample.architecture.data.demo.model.EchoModel
import javax.inject.Inject

class EchoRepository @Inject constructor(
    private val echoApiService: EchoApiService
){
    suspend fun echo(string: String): EchoModel {
        return echoApiService.echo(string).toModel()
    }

}