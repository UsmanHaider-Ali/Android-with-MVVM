package com.leadingspark.mvvmpracticesession.data.network.responses

import com.leadingspark.mvvmpracticesession.data.database.entities.User

data class AuthResponse(
    val message: String?,
    val token: String?,
    val user: User?
)