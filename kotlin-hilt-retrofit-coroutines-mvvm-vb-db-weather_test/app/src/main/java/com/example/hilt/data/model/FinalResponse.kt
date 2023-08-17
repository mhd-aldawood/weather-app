package com.example.hilt.data.model

import com.squareup.moshi.Json

//TODO() use polymorphic JSON ins
data class FinalResponse(
    @Json(name = "temp") var temp: Double? = null,
    @Json(name = "timezone"   ) var timezone   : Int?               = null,
    @Json(name = "id"         ) var id         : Int?               = null,
    @Json(name = "name"       ) var name       : String?            = null,
    @Json(name = "cod"        ) var cod        : Int?               = null
)