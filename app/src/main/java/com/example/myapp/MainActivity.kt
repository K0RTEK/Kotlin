package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapp.data.AppDatabase
import com.example.myapp.ui.theme.PostsModelView
import com.example.myapp.ui.theme.PostsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(this)
        val viewModel = PostsModelView(db)

        setContent {
            PostsScreen(viewModel)
        }
    }
}