package com.azimzada.retrofitrecycleexamson.networkjk

import com.azimzada.retrofitrecycleexamson.Constants
import com.azimzada.retrofitrecycleexamson.api.API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{
        var instance : RetrofitClient? = null
        lateinit var api : API
        fun getRetrofitInstance(): RetrofitClient? {
            if (instance == null) {
                instance = RetrofitClient()
                val retrofit: Retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                api = retrofit.create<API>(API::class.java)
            }
            return instance
        }
    }

    fun getApi(): API? {
        return api
    }
}