package com.example.run4u.data

import kotlinx.coroutines.flow.Flow

class BMIRepository(private val dao: BMIDao) {
    val allRecords: Flow<List<BMIRecordEntity>> = dao.getAllRecords()

    suspend fun insert(record: BMIRecordEntity) {
        dao.insert(record)
    }

    suspend fun delete(record: BMIRecordEntity) {
        dao.delete(record)
    }
}
