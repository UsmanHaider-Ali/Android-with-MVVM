package com.leadingspark.mvvmpracticesession.data.network

import com.leadingspark.mvvmpracticesession.utils.APIExceptions
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeAPIRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {

        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                } catch (e: JSONException) {
                    message.append(e.message)
                }
                message.append("\n")
            }
            message.append("Error Code: ${response.code()}")
            throw APIExceptions(message.toString())
        }
    }
}