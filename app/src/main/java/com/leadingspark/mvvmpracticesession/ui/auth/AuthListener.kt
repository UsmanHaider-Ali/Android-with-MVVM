package com.leadingspark.mvvmpracticesession.ui.auth

import com.leadingspark.mvvmpracticesession.data.database.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User?)
    fun onFailed(message: String)
}