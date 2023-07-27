package com.example.hilt.data.repo


import androidx.lifecycle.LiveData
import com.example.hilt.data.db.AppDatabase
import com.example.hilt.data.db.Transformer.convertMainModelToMainEntity
import com.example.hilt.data.db.entity.MainEntity
import com.example.hilt.data.model.Main

import javax.inject.Inject

class DBRepository @Inject constructor(val appDatabase: AppDatabase) {

    suspend fun insertMain(main: Main): Long {
        return appDatabase.MainDao()
            .insert(convertMainModelToMainEntity(main))
    }

    suspend fun delete(main: Main) {
        appDatabase.MainDao().delete(convertMainModelToMainEntity(main))
    }

    // NOTE - Since we are already using LIVE-DATA no need to use suspend function
    fun getMain(): LiveData<List<MainEntity>> {
        return appDatabase.MainDao().getOfflineMain()
    }


}