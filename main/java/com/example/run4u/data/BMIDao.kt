package com.example.run4u.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BMIDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: BMIRecordEntity)

    @Query("SELECT * FROM bmi_records ORDER BY id DESC")
    fun getAllRecords(): Flow<List<BMIRecordEntity>>

    @Delete
    suspend fun delete(record: BMIRecordEntity)
}
