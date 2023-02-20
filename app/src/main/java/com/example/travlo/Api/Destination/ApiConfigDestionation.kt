package com.example.travlo.Api.Destination

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfigDestionation {
    const val base_url = "http://10.0.2.2:1337/api/"

    fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() : ApiServiceDestination{
        return getRetrofit().create(ApiServiceDestination::class.java)
    }

}