package com.example.hilt.data.model

import com.squareup.moshi.Json


data class Main(
    @Json(name = "temp") var temp: Double? = null,
    @Json(name = "feels_like") var feelsLike: Double? = null,
    @Json(name = "temp_min") var tempMin: Double? = null,
    @Json(name = "temp_max") var tempMax: Double? = null,
    @Json(name = "pressure") var pressure: Int? = null,
    @Json(name = "humidity") var humidity: Int? = null,
    var requestDAte:String?=null,
)