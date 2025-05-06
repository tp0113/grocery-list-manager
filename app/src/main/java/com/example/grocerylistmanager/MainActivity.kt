package com.example.grocerylistmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.grocerylistmanager.ui.GroceryListScreen
import com.example.grocerylistmanager.ui.theme.GroceryListManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GroceryListManagerTheme {
                GroceryListScreen()
            }
        }
    }
}
