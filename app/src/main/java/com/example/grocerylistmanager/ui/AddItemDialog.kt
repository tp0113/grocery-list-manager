@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.grocerylistmanager.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.window.DialogProperties

@Composable
fun AddItemDialog(
    onAdd: (String) -> Unit,
    onCancel: () -> Unit
) {
    var text by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Add New Item") },
        text = {
            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("Item name") },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors()
            )
        },
        confirmButton = {
            Button(onClick = {
                if (text.isNotBlank()) onAdd(text.trim())
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = onCancel) {
                Text("Cancel")
            }
        },
        properties = DialogProperties()
    )
}
