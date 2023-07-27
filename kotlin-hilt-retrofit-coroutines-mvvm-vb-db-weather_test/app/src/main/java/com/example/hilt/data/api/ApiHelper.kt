package com.example.hilt.data.api

import com.example.hilt.data.model.FinalResponse
import com.example.hilt.data.model.Main
import retrofit2.Response

interface ApiHelper {

    suspend fun getWeather( lat: Double,
                            latitude: Double,
                            cnt: Int,
                            appid: String): Response<FinalResponse>
}