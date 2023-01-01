package com.leadingspark.mvvmpracticesession.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.leadingspark.mvvmpracticesession.data.repositories.UserRepository
import com.leadingspark.mvvmpracticesession.utils.APIExceptions
import com.leadingspark.mvvmpracticesession.utils.Coroutines
import com.leadingspark.mvvmpracticesession.utils.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

//    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClicked(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailed("Invalid email or password")
            return
        }

        Coroutines.main {
            try {
                val loginResponse = repository.userLogin(email!!, password!!)
                loginResponse.user?.let {
                    authListener?.onSuccess(it)
//                    repository.saveUser(it)
                    return@main
                }
                if (loginResponse.message == null)
                    authListener?.onFailed("Something wrong, please try again")
                else
                    authListener?.onFailed(loginResponse.message)
            } catch (e: APIExceptions) {
                authListener?.onFailed(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailed(e.message!!)
            }
        }
    }

    fun onRegisterTextViewClicked(view: View) {
    }
}