package com.sample.architecture.domain.demo.model

import com.sample.architecture.data.demo.model.EchoModel

data class Echo (
    val echo: String
)

fun EchoModel.toModel() = Echo(echo = echo)