package com.example.hilt.data.model

import com.squareup.moshi.Json

//TODO() use polymorphic JSON ins
data class FinalResponse(
    @Json(name = "temp") var temp: Double? = null,
    @Json(name = "coord"      ) var coord      : Coord,
    @Json(name = "weather"    ) var weather    : List<Weather>,
    @Json(name = "base"       ) var base       : String?            = null,
    @Json(name = "main"       ) var main       : Main,
    @Json(name = "visibility" ) var visibility : Int?               = null,
    @Json(name = "wind"       ) var wind       : Wind,
    @Json(name = "clouds"     ) var clouds     : Clouds,
    @Json(name = "dt"         ) var dt         : Int?               = null,
    @Json(name = "sys"        ) var sys        : Sys,
    @Json(name = "timezone"   ) var timezone   : Int?               = null,
    @Json(name = "id"         ) var id         : Int?               = null,
    @Json(name = "name"       ) var name       : String?            = null,
    @Json(name = "cod"        ) var cod        : Int?               = null
)