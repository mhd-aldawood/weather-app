package com.example.hilt.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hilt.data.db.entity.MainEntity
@Dao
interface MainDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mainEntity: MainEntity):Long

    // NOTE - Since we are already using LIVE-DATA no need to use suspend function
    @Query("SELECT * FROM MAINENTITY")
    fun getOfflineMain(): LiveData<List<MainEntity>>

    @Delete
    suspend fun delete(articleEntity: MainEntity)
}