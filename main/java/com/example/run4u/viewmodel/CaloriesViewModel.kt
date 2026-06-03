package com.example.run4u.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.run4u.data.Food
import com.example.run4u.respository.CaloriesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CaloriesViewModel(private val repository: CaloriesRepository) : ViewModel() {
    private val _foodList = MutableStateFlow<List<Food>>(emptyList())
    val foodList: StateFlow<List<Food>> = _foodList

    private val _totalCalories = MutableStateFlow(0)
    val totalCalories: StateFlow<Int> = _totalCalories

    private val todayDate: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

    init {
        loadFoodForToday()
    }

    private fun loadFoodForToday() {
        viewModelScope.launch {
            repository.getFoodsForDay(todayDate).collect { foods ->
                _foodList.value = foods
                _totalCalories.value = foods.sumOf { it.calories }
            }
        }
    }

    fun addFood(name: String, category: String, calories: Int) {
        val food = Food(name = name, category = category, calories = calories, date = todayDate)
        viewModelScope.launch {
            repository.insert(food)
            loadFoodForToday()
        }
    }

    fun clearAllEntries() {
        viewModelScope.launch {
            repository.deleteAllFoodsForDay(todayDate)
            loadFoodForToday()
        }
    }
}
