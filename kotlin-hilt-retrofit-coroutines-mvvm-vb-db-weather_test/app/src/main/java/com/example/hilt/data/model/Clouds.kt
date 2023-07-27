package com.example.hilt.data.model

import com.squareup.moshi.Json


data class Clouds(
    @Json(name="all" ) var all : Int? = null

)