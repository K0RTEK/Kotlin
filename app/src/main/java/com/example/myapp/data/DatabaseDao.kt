package com.example.myapp.data

import androidx.room.*

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(dataSchema: List<DataSchema>)

    @Query("SELECT * FROM TodoTable")
    suspend fun getAllData(): List<DataSchema>

    @Query("SELECT * FROM TodoTable WHERE id = :id")
    suspend fun getDataById(id: Int): DataSchema?
}