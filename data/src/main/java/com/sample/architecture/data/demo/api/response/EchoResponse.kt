package com.sample.architecture.data.demo.api.response

import com.sample.architecture.data.demo.model.EchoModel
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class EchoResponse(
    val path: String,
    val parsedQueryParams: Map<String, String>
)

fun EchoResponse.toModel() = EchoModel(
    echo = parsedQueryParams["string"] ?: ""
)