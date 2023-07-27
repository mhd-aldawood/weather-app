package com.example.hilt.data.model

import com.squareup.moshi.Json


data class Wind(
    @Json(name = "temp") var temp: Double? = null,
    @Json(name ="speed" ) var speed : Double? = null,
    @Json(name ="deg"   ) var deg   : Int?    = null
)