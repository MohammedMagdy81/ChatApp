package com.example.chat.database

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

object Database {
    private val database= FirebaseFirestore.getInstance()
    val USERS_PATH="users"
    val ROOMS_PATH = "rooms"

    fun getUsersCollection():CollectionReference{
       return database.collection(USERS_PATH)
    }
    fun getRoomsCollction() : CollectionReference {
        return database.collection(ROOMS_PATH)
    }
}