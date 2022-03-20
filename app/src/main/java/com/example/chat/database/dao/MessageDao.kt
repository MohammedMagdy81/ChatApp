package com.example.chat.database.dao

import com.example.chat.database.Database
import com.example.chat.database.model.Message
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference

class MessageDao {
    companion object{
        fun sendMessage(message: Message , onSuccessListener:OnSuccessListener<Void>, onFailureListener: OnFailureListener){
            val room  = Database.getRoomsCollction()
                .document(message.roomId?:"")
            val  messages= room.collection("messages")
            val newMessageDoc= messages.document()
           message.id = newMessageDoc.id
            newMessageDoc.set(message).addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener)

        }
        fun getMessagesRef(roomId:String): CollectionReference{
            return Database.getRoomsCollction()
                .document(roomId?:"")
                .collection("messages")
        }
    }
}