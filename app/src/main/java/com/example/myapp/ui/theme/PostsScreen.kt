package com.example.myapp.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapp.data.PostsSchema

@Composable
fun PostsScreen(viewModel: PostsModelView) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Posts list", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(10.dp))

        if (viewModel.isLoading) {
            CircularProgressIndicator()
        } else {
            if (viewModel.data.isEmpty()) {
                Text("Нету данных")
            } else {
                Button(onClick = { viewModel.loadData() }) {
                    Text("Загрузить данные")
                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(viewModel.data) { data ->
                        DataItem(data)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { viewModel.loadData() }) {
            Text("Загрузить данные")
        }
    }
}

@Composable
fun DataItem(data: PostsSchema) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID: ${data.id}", style = MaterialTheme.typography.titleLarge)
            Text(text = "userId: ${data.userId}", style = MaterialTheme.typography.titleLarge)
            Text(text = "title: ${data.title}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "body: ${data.body}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

