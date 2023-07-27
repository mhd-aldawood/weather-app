package com.example.hilt.data.model

import com.squareup.moshi.Json


data class Sys(
    @Json(name = "type") var type: Int? = null,
    @Json(name = "id") var id: Int? = null,
    @Json(name = "country") var country: String? = null,
    @Json(name = "sunrise") var sunrise: Int? = null,
    @Json(name = "sunset") var sunset: Int? = null
)