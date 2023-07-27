package com.example.hilt.ui.main.viewmodel


import androidx.lifecycle.*
import com.example.hilt.BuildConfig
import com.example.hilt.data.model.FinalResponse
import com.example.hilt.data.model.Main
import com.example.hilt.data.repo.DBRepository
import com.example.hilt.data.repo.MainRepository
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
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper,
    private val dbRepository: DBRepository
) : ViewModel() {
    private val _response = MutableLiveData<Resource<FinalResponse>>()

    val response: LiveData<Resource<FinalResponse>>
        get() = _response

    fun getWeather(lat: Double,lon: Double,cnt: Int)
    {
        viewModelScope.launch(Dispatchers.IO) {
            _response.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getWeather(lat, lon, cnt, BuildConfig.TOKEN).let {
                    if (it.isSuccessful) {
                        _response.postValue(Resource.success(it.body(),toStringDate(it.raw().sentRequestAtMillis)))
                        insertMain(it.body()!!.main )
                    } else
                        _response.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else
                _response.postValue(Resource.error("No internet connection", null))
        }

    }

    private fun toStringDate(millisecond: Long): String {
        val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        return formatter.format(Date(millisecond))
    }

    fun insertMain(main: Main) = viewModelScope.launch (Dispatchers.IO){ dbRepository.insertMain(main)}


    fun deleteMain(main: Main) = viewModelScope.launch (Dispatchers.IO) { dbRepository.delete(main)}


    fun getRecorde() = dbRepository.getMain()
}