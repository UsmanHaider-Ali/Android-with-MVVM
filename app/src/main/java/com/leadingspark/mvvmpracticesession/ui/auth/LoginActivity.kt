package com.leadingspark.mvvmpracticesession.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.leadingspark.mvvmpracticesession.R
import com.leadingspark.mvvmpracticesession.databinding.ActivityLoginBinding
import com.leadingspark.mvvmpracticesession.utils.toast

class LoginActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

        val viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        binding.authViewModel = viewModel

        viewModel.authListener = this
    }

    override fun onStarted() {
        toast("Login Started")
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            toast(it)
        })
    }

    override fun onFailed(message: String) {
        toast(message)
    }
}