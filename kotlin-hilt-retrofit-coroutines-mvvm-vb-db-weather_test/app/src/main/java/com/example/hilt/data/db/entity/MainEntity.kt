package com.example.hilt.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MAINENTITY")
data class MainEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
                      val temperature: String?,
                      val requestDate: String?,
)
