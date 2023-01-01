package com.leadingspark.mvvmpracticesession.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AllAPIs {

    @FormUrlEncoded
    @POST("loginUser")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseBody>

    companion object {
        operator fun invoke(): AllAPIs {
            return Retrofit
                .Builder()
                .baseUrl("http://bechobooks.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AllAPIs::class.java)
        }
    }
}