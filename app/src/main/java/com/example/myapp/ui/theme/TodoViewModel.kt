package com.example.myapp.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.AppDatabase
import com.example.myapp.data.DataSchema
import com.example.myapp.repository.TodoRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class TodoViewModel(db: AppDatabase) : ViewModel() {
    private val repository = TodoRepository(db)

    var data by mutableStateOf<List<DataSchema>>(emptyList())
    var isLoading by mutableStateOf(false)

    fun loadData() {
        viewModelScope.launch {
            isLoading = true
            data = repository.fetchData()
            isLoading = false
        }
    }
}