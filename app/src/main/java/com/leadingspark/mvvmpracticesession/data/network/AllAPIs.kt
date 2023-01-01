package com.leadingspark.mvvmpracticesession.data.network

import com.leadingspark.mvvmpracticesession.data.network.responses.AuthResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AllAPIs {

    @FormUrlEncoded
    @POST("loginUser")
    suspend fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<AuthResponse>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): AllAPIs {

            val okkHttpClient = OkHttpClient
                .Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit
                .Builder()
                .client(okkHttpClient)
                .baseUrl("http://bechobooks.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AllAPIs::class.java)
        }
    }
}