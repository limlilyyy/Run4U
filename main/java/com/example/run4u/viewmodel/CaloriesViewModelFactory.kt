package com.example.run4u.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.run4u.respository.CaloriesRepository

class CaloriesViewModelFactory(
    private val repository: CaloriesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CaloriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CaloriesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
