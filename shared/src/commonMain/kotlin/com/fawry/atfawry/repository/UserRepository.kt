package com.fawry.atfawry.repository

import com.fawry.atfawry.datasource.network.api.Hello
import com.fawry.atfawry.remote.ApiClient
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface IUserRepository {
    suspend fun fetchUsers(): Flow<List<Hello>>
}

class UserRepository : IUserRepository {

    override suspend fun fetchUsers(): Flow<List<Hello>> {
        val result = ApiClient.instance.client.get<List<Hello>>(
            urlString = "${ApiClient.instance.baseUrl}/cdn/KaterinaPetrova/greeting/" +
                    "7d47a42fc8d28820387ac7f4aaf36d69e434adc1/greetings.json"
        )
        return flowOf(result)
    }

}