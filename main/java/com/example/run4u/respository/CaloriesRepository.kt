package com.example.run4u.respository

import com.example.run4u.data.FoodDao
import com.example.run4u.data.Food
import kotlinx.coroutines.flow.Flow

class CaloriesRepository(private val foodDao: FoodDao) {
    fun getFoodsForDay(date: String): Flow<List<Food>> = foodDao.getFoodsForDay(date)
    suspend fun insert(food: Food) = foodDao.insert(food)
    suspend fun deleteAllFoodsForDay(date: String) = foodDao.deleteAllFoodsForDay(date)
}
