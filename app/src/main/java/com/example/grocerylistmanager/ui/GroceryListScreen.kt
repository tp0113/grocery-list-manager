package com.example.grocerylistmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import com.example.grocerylistmanager.data.GroceryItemEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroceryListScreen(
    viewModel: GroceryViewModel = viewModel()
) {
    var showDialog by remember { mutableStateOf(false) }
    val items by viewModel.items.collectAsState(initial = emptyList())

    Scaffold(
        topBar = { SmallTopAppBar(title = { Text("Grocery List Manager") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add item")
            }
        }
    ) { padding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (items.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No items yet. Tap + to add.")
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(items) { item ->
                        ItemRow(
                            item = item,
                            onCheckedChange = { checked ->
                                viewModel.setPurchased(item, checked)
                            },
                            onDelete = { viewModel.deleteItem(item) }
                        )
                    }
                }
            }
        }

        if (showDialog) {
            AddItemDialog(
                onAdd = {
                    viewModel.addItem(it)
                    showDialog = false
                },
                onCancel = { showDialog = false }
            )
        }
    }
}
