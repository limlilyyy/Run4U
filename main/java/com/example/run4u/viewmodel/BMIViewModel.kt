package com.example.run4u.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.run4u.data.BMIRecordEntity
import com.example.run4u.data.BMIDatabase
import com.example.run4u.data.BMIRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BMIViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BMIRepository = BMIRepository(
        BMIDatabase.getDatabase(application).bmiDao()
    )

    val allRecords: Flow<List<BMIRecordEntity>> = repository.allRecords

    fun insert(record: BMIRecordEntity) = viewModelScope.launch {
        repository.insert(record)
    }

    fun delete(record: BMIRecordEntity) = viewModelScope.launch {
        repository.delete(record)
    }
}
