package com.example.chat.database.model

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*


class Message(var id:String? = null ,
                   var senderId:String? = null,
                   var senderName:String? = null,
                   var messageText:String? = null,
                   var roomId:String? = null ,
                   var time:Timestamp?= null){
    fun swapTimeToString(): String {

      val date= time?.toDate()
        val simpleDateFormat= SimpleDateFormat("HH : mm", Locale.ENGLISH)
        return simpleDateFormat.format(date)
    }

}
