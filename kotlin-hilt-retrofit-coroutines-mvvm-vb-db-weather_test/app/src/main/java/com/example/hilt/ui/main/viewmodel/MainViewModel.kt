package com.example.hilt.ui.main.viewmodel


import androidx.lifecycle.*
import com.example.hilt.data.model.FinalResponse
import com.example.hilt.data.model.Main
import com.example.hilt.data.repo.DBRepository
import com.example.hilt.utils.NetworkHelper
import com.example.hilt.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dbRepository: DBRepository
) : ViewModel() {
    private val _response = MutableLiveData<Resource<FinalResponse>>()

    val response: LiveData<Resource<FinalResponse>>
        get() = _response



    private fun toStringDate(millisecond: Long): String {
        val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        return formatter.format(Date(millisecond))
    }

    fun insertMain(main: Main) = viewModelScope.launch (Dispatchers.IO){ dbRepository.insertMain(main)}


    fun deleteMain(main: Main) = viewModelScope.launch (Dispatchers.IO) { dbRepository.delete(main)}


    fun getRecorde() = dbRepository.getMain()
}