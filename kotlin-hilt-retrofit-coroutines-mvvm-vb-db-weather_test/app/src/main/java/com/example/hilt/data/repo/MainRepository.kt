package com.example.hilt.data.repo

import com.example.hilt.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getWeather(lat: Double,
                           lon: Double,
                           cnt: Int,
                           appid: String) =  apiHelper.getWeather(lat,lon,cnt,appid)

}