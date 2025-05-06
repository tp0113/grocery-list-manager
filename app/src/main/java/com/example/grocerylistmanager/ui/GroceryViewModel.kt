package com.example.grocerylistmanager.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocerylistmanager.data.AppDatabase
import com.example.grocerylistmanager.data.GroceryItemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GroceryViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase
        .getDatabase(application)
        .groceryItemDao()

    val items: StateFlow<List<GroceryItemEntity>> =
        dao.getAllItems()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    /** Insert a new item off the main thread */
    fun addItem(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(GroceryItemEntity(name = name))
        }
    }

    /** Update the purchased flag off the main thread */
    fun setPurchased(item: GroceryItemEntity, purchased: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(item.copy(purchased = purchased))
        }
    }
    // add inside GroceryViewModel class:

    /** Delete an item off the main thread */
    fun deleteItem(item: GroceryItemEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(item)
        }
    }

}
