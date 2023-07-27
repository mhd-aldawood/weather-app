package com.example.hilt.data.model

import com.squareup.moshi.Json


data class Coord(
    @Json(name ="lon" ) var lon : Double? = null,
    @Json(name ="lat" ) var lat : Double? = null
)