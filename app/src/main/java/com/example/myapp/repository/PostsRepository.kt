package com.example.myapp.repository

import com.example.myapp.data.AppDatabase
import com.example.myapp.data.PostsSchema
import com.example.myapp.network.ApiInterface
import com.example.myapp.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class PostsRepository(private val db: AppDatabase) {
    private val api = RetrofitService.retrofit.create(ApiInterface::class.java)

    suspend fun fetchApi(): List<PostsSchema> {
        return try {
            val response = api.getApiAnswer().awaitResponse()
            if (response.isSuccessful) {
                response.body()?.also { saveData(it) } ?: emptyList()
            } else {
                getData()
            }
        } catch (e: Exception) {
            getData()
        }
    }

    private suspend fun saveData(data: List<PostsSchema>) {
        withContext(Dispatchers.IO) {
            db.databaseDao().insertData(data)
        }
    }

    private suspend fun getData(): List<PostsSchema> {
        return withContext(Dispatchers.IO) {
            db.databaseDao().selectData()
        }
    }
}