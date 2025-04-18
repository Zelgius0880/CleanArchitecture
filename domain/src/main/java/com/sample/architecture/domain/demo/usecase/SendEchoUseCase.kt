package com.sample.architecture.domain.demo.usecase

import com.sample.architecture.data.demo.repository.EchoRepository
import com.sample.architecture.domain.demo.model.Echo
import com.sample.architecture.domain.demo.model.toModel
import jakarta.inject.Inject

class SendEchoUseCase @Inject constructor(
    private val echoRepository: EchoRepository
){

    suspend fun execute(string: String) = try{
        Success(echoRepository.echo(string).toModel())
    } catch (e: Exception){
        e.printStackTrace()
        Failure
    }
}

sealed interface EchoResult

data class Success(val echo: Echo) : EchoResult
data object Failure : EchoResult