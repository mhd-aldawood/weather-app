package com.example.hilt.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hilt.data.db.dao.MainDao
import com.example.hilt.data.db.entity.MainEntity

@Database(
    version = 1,
    entities = [MainEntity::class],
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun MainDao(): MainDao
}