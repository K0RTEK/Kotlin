package com.example.myapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PostsSchema::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDatabase::class.java,
                    name = "Database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}