package com.leadingspark.mvvmpracticesession.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leadingspark.mvvmpracticesession.data.network.AllAPIs
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    fun userLogin(email: String, password: String): LiveData<String> {

        val loginResponse = MutableLiveData<String>()

        AllAPIs().userLogin(email, password)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        loginResponse.value = response.body()?.string()
                    } else {
                        loginResponse.value = response.errorBody()?.string()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value = t.message
                }
            })
        return loginResponse
    }
}