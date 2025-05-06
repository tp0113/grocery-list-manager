package com.example.grocerylistmanager.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryItemDao {

    @Query("SELECT * FROM grocery_items")
    fun getAllItems(): Flow<List<GroceryItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: GroceryItemEntity): Long

    @Update
    fun update(item: GroceryItemEntity): Int

    @Delete
    fun delete(item: GroceryItemEntity): Int
}
