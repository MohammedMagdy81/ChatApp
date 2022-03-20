package com.example.chat.roomDetails

import androidx.databinding.ObservableField
import com.example.chat.Data
import com.example.chat.base.BaseViewModel
import com.example.chat.database.dao.MessageDao
import com.example.chat.database.model.Message
import com.google.firebase.Timestamp

class RoomDetailsViewModel : BaseViewModel<RoomDetailsNavigator>() {
    var  messageField  = ObservableField<String>()
    var roomId : String?=null

    fun send(){
        if (!valid()) return
        val messageObj = Message(messageText = messageField.get(),senderId = Data.user?.id ,
                                 senderName = Data.user?.userName ,
                                 time = Timestamp.now() ,
                                 roomId = roomId)
        MessageDao.sendMessage(messageObj, {
            messageField.set("")
        },{
            messageLiveData.value= it.localizedMessage
        })

    }

    private fun valid(): Boolean {
    var isValid= true
    if (messageField.get().isNullOrBlank()){
        isValid= false
    }
        return isValid
    }

}