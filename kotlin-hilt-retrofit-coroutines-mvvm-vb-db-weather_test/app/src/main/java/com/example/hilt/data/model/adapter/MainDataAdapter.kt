package com.example.hilt.data.model.adapter

import com.example.hilt.data.model.Main
import com.example.hilt.data.model.Weather
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import javax.inject.Inject

class MainDataAdapter /*@Inject constructor()*/ {
    @FromJson
    fun fromJson(jsonReader: JsonReader): Weather? {
        var temperature: Double? = null
        jsonReader.beginObject()
        while (jsonReader.hasNext()) {
            val name = jsonReader.nextName()
            if (name == "temp") {
                temperature = jsonReader.nextDouble()
            } else {
                jsonReader.skipValue()
            }
        }
        jsonReader.endObject()
        if (temperature != null) {
//            return Weather(Main(temperature, 0.0, 0.0, 0.0, 0, 0))
        }
        return null
    }
}