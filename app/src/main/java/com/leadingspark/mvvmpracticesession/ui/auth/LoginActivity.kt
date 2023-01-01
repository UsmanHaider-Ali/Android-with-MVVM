package com.leadingspark.mvvmpracticesession.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.leadingspark.mvvmpracticesession.R
import com.leadingspark.mvvmpracticesession.data.database.AppDatabase
import com.leadingspark.mvvmpracticesession.data.database.entities.User
import com.leadingspark.mvvmpracticesession.data.network.AllAPIs
import com.leadingspark.mvvmpracticesession.data.network.NetworkConnectionInterceptor
import com.leadingspark.mvvmpracticesession.data.repositories.UserRepository
import com.leadingspark.mvvmpracticesession.databinding.ActivityLoginBinding
import com.leadingspark.mvvmpracticesession.ui.home.HomeActivity
import com.leadingspark.mvvmpracticesession.utils.hide
import com.leadingspark.mvvmpracticesession.utils.show
import com.leadingspark.mvvmpracticesession.utils.snackBar

class LoginActivity : AppCompatActivity(), AuthListener {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val apis = AllAPIs(networkConnectionInterceptor)
        val db = AppDatabase(this@LoginActivity)
        val repository = UserRepository(apis)
        val viewModelFactory = AuthViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]

        binding.authViewModel = viewModel

        viewModel.authListener = this

//        viewModel.getLoggedInUser().observe(this) { user ->
//            if (user != null) {
//                Intent(this, HomeActivity::class.java).also { intent ->
//                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    startActivity(intent)
//                }
//            }
//        }
    }

    override fun onStarted() {
        binding.pbProgressBar.show()
    }

    override fun onSuccess(user: User?) {
        binding.pbProgressBar.hide()
//        binding.clMain.snackBar(user?.name!!)
    }

    override fun onFailed(message: String) {
        binding.pbProgressBar.hide()
        binding.clMain.snackBar(message)
    }
}