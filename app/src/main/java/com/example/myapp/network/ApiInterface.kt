package com.example.myapp.network

import com.example.myapp.data.PostsSchema
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    fun getApiAnswer(): Call<List<PostsSchema>>
}