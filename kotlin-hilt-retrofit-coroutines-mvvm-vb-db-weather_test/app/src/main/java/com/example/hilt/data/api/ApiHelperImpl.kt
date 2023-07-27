package com.example.hilt.data.api

import com.example.hilt.data.model.FinalResponse
import com.mindorks.framework.mvvm.data.api.ApiService
import com.example.hilt.data.model.Main
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getWeather( lat: Double,
                                     lon: Double,
                                    cnt: Int,
                                    appid: String): Response<FinalResponse> = apiService.getWeather(lat,lon,cnt,appid)

}