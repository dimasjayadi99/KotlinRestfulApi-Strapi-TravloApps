package com.example.travlo.Api.Destination

import com.example.travlo.Response.ResponseDestination
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceDestination {

    @GET("destinations?populate=*")
    fun getListAll() : Call<ResponseDestination>

    @GET("destinations?populate=*&filters[recommended][\$eq]=true")
    fun getListRecommended() : Call<ResponseDestination>

    @GET("destinations?populate=*&filters[category][name][\$eq]=Natural")
    fun getListNatural() : Call<ResponseDestination>

    @GET("destinations?populate=*&filters[category][name][\$eq]=Beach")
    fun getListBeach() : Call<ResponseDestination>

    @GET("destinations?populate=*&filters[category][name][\$eq]=Forest")
    fun getListForest() : Call<ResponseDestination>

    @GET("destinations?populate=*&filters[category][name][\$eq]=Historic")
    fun getListHistoric() : Call<ResponseDestination>
}