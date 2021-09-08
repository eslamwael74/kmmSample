package com.fawry.atfawry.remote

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

class ApiClient {

    val baseUrl = "https://gitcdn.link"

    companion object {
        val instance = ApiClient()
    }

    val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            })
        }
        //Ktor specific logging: reenable if needed to debug requests
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }


    suspend inline fun <reified T : Any> getResponse(endpoint: String): T? {
        val url = baseUrl + endpoint
        try {
            // please notice, Ktor Client is switching to a background thread under the hood
            // so the http call doesn't happen on the main thread, even if the coroutine has been launched on Dispatchers.Main
            val resp = client.get<T>(url)
            print("$url API SUCCESS")
            return resp
        } catch (e: Exception) {
            print("$url API FAILED: " + e.message)
        }
        return null
    }


}