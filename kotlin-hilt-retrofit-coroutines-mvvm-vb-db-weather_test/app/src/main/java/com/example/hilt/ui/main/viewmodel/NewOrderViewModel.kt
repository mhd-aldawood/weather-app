package com.example.hilt.ui.main.viewmodel

import android.content.ClipData
import androidx.lifecycle.ViewModel
import com.example.hilt.data.model.Item
import com.example.hilt.data.repo.DBRepository
import javax.inject.Inject

class NewOrderViewModel @Inject constructor(
    private val dbRepository: DBRepository
) : ViewModel()  {
    fun getListOfItem(): List<Item> {
        val list = listOf<Item>(
            Item("كتاب القلق","51.45"),
            Item("سلسلة مقدمه موجزه","36.75"),
            Item("اميرة مدينه العاج","12.86"),
            Item("علم موسيقى الشعوب:سلسله مقدمات موجزة","36.75"),
            Item("سولونغو سر البعثه الضائعه","36.75"),
            Item("كتاب القلق","51.45"),
            Item("سلسلة مقدمه موجزه","36.75"),
            Item("اميرة مدينه العاج","12.86"),
            Item("علم موسيقى الشعوب:سلسله مقدمات موجزة","36.75"),
            Item("سولونغو سر البعثه الضائعه","36.75"),
            Item("كتاب القلق","51.45"),
            Item("سلسلة مقدمه موجزه","36.75"),
            Item("اميرة مدينه العاج","12.86"),
            Item("علم موسيقى الشعوب:سلسله مقدمات موجزة","36.75"),
            Item("سولونغو سر البعثه الضائعه","36.75"),
            Item("كتاب القلق","51.45"),
            Item("سلسلة مقدمه موجزه","36.75"),
            Item("اميرة مدينه العاج","12.86"))
        return list
    }
}