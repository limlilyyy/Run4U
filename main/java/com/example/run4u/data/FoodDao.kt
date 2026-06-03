package com.example.run4u.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Query("SELECT * FROM food_entries WHERE date = :date")
    fun getFoodsForDay(date: String): Flow<List<Food>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(food: Food)

    @Query("DELETE FROM food_entries WHERE date = :date")
    suspend fun deleteAllFoodsForDay(date: String)
}
