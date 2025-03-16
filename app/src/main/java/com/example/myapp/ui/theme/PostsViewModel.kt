package com.example.myapp.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.AppDatabase
import com.example.myapp.data.PostsSchema
import com.example.myapp.repository.PostsRepository
import kotlinx.coroutines.launch

class PostsModelView(db: AppDatabase): ViewModel() {
    private val repository = PostsRepository(db)

    var data by mutableStateOf<List<PostsSchema>>(emptyList())
    var isLoading by mutableStateOf(false)

    fun loadData() {
        viewModelScope.launch {
            isLoading = true
            data = repository.fetchApi()
            isLoading = false
        }
    }
}