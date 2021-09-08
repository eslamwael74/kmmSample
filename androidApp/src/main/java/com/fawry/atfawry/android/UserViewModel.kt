package com.fawry.atfawry.android

import androidx.lifecycle.ViewModel
import com.fawry.atfawry.datasource.network.api.Hello
import com.fawry.atfawry.repository.IUserRepository
import com.fawry.atfawry.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserViewModel : ViewModel() {

    private val repository: IUserRepository = UserRepository()

    suspend fun getUsers(): Flow<List<Hello>> {
        val results: Flow<List<Hello>> = repository.fetchUsers()
        return results
    }

}