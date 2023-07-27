package com.example.hilt.data.db

import com.example.hilt.data.db.entity.MainEntity
import com.example.hilt.data.model.Main

object Transformer {

    fun convertMainModelToMainEntity(main: Main): MainEntity = MainEntity(temperature = main.temp.toString(), requestDate = main.requestDAte)

    fun convertMainEntityToMainModel(mainEntity: MainEntity): Main = Main(temp = mainEntity.temperature?.toDouble(), requestDAte = mainEntity.requestDate)

}