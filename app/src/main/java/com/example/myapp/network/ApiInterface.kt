package com.example.myapp.network

import com.example.myapp.data.DataSchema
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    fun getApiAnswer(): Call<List<DataSchema>>
}