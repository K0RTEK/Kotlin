package com.example.myapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostsSchema (
    @PrimaryKey val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)