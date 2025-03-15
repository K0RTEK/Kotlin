package com.example.myapp.repository

import com.example.myapp.data.AppDatabase
import com.example.myapp.data.DataSchema
import com.example.myapp.network.ApiInterface
import com.example.myapp.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class TodoRepository(private val db: AppDatabase) {
    private val api = RetrofitService.retrofit.create(ApiInterface::class.java)

    suspend fun fetchData(): List<DataSchema> {
        return try {
            val response = api.getApiAnswer().awaitResponse()
            if (response.isSuccessful) {
                response.body()?.also { saveData(it) } ?: emptyList()
            } else {
                getDataFromDatabase()
            }
        } catch (e: Exception) {
            getDataFromDatabase()
        }
    }

    private suspend fun saveData(data: List<DataSchema>) {
        withContext(Dispatchers.IO) {
            db.databaseDao().insertData(data)
        }
    }

    private suspend fun getDataFromDatabase(): List<DataSchema> {
        return withContext(Dispatchers.IO) {
            db.databaseDao().getAllData()
        }
    }
}