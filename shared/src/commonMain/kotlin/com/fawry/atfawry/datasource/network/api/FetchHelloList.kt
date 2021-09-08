package com.fawry.atfawry.datasource.network.api

import com.fawry.atfawry.remote.ApiClient
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

suspend fun ApiClient.fetchHelloList() : List<Hello>? {
    return getResponse("")
}

@Serializable
data class Hello(
    @SerialName("string")
    val name: String
)