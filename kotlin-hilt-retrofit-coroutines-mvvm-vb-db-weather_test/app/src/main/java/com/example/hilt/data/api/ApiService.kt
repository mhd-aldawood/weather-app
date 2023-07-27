package com.mindorks.framework.mvvm.data.api

import com.example.hilt.data.model.FinalResponse
import com.example.hilt.data.model.Main
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("cnt") cnt: Int,
        @Query("appid") appid: String
    ): Response<FinalResponse>

}