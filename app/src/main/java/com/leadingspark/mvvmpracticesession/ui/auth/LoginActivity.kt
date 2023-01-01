package com.leadingspark.mvvmpracticesession.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.leadingspark.mvvmpracticesession.R
import com.leadingspark.mvvmpracticesession.databinding.ActivityLoginBinding
import com.leadingspark.mvvmpracticesession.utils.hide
import com.leadingspark.mvvmpracticesession.utils.show
import com.leadingspark.mvvmpracticesession.utils.toast

class LoginActivity : AppCompatActivity(), AuthListener {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

        val viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        binding.authViewModel = viewModel

        viewModel.authListener = this
    }

    override fun onStarted() {
        binding.pbProgressBar.show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            binding.pbProgressBar.hide()
            toast(it)
        })
    }

    override fun onFailed(message: String) {
        binding.pbProgressBar.hide()
        toast(message)
    }
}