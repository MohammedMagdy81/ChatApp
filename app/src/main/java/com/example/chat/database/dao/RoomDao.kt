package com.example.chat.database.dao

import com.example.chat.database.Database
import com.example.chat.database.model.Room
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.QuerySnapshot

class RoomDao {
    companion object{
        fun insertRoom(room:Room, onCompleteListener: OnCompleteListener<Void>){
            val document = Database.getRoomsCollction()
                .document()
            room.id= document.id
            document.set(room)
                .addOnCompleteListener(onCompleteListener)
        }
        fun getRoomsList(onSuccessListener:OnSuccessListener<QuerySnapshot>,onFailureListener : OnFailureListener){
            Database.getRoomsCollction()
                .get()
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener)

        }
    }
}