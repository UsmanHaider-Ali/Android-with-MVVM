package com.leadingspark.mvvmpracticesession.data.repositories

import com.leadingspark.mvvmpracticesession.data.database.AppDatabase
import com.leadingspark.mvvmpracticesession.data.database.entities.User
import com.leadingspark.mvvmpracticesession.data.network.AllAPIs
import com.leadingspark.mvvmpracticesession.data.network.SafeAPIRequest
import com.leadingspark.mvvmpracticesession.data.network.responses.AuthResponse

class UserRepository(
    private val apis: AllAPIs,
//    private val db: AppDatabase,
) : SafeAPIRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest {
            apis.userLogin(email, password)
        }
    }

//    suspend fun saveUser(user: User) = db.getUserDAO().upsert(user)
//
//    fun getUser() = db.getUserDAO().getUser()
}