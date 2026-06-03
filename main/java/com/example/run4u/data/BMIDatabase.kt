package com.example.run4u.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BMIRecordEntity::class], version = 1, exportSchema = false)
abstract class BMIDatabase : RoomDatabase() {
    abstract fun bmiDao(): BMIDao

    companion object {
        @Volatile
        private var INSTANCE: BMIDatabase? = null

        fun getDatabase(context: Context): BMIDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    BMIDatabase::class.java,
                    "bmi_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
