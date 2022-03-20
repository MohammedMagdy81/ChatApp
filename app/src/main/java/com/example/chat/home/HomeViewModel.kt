package com.example.chat.home

import androidx.lifecycle.MutableLiveData
import com.example.chat.base.BaseViewModel
import com.example.chat.database.dao.RoomDao
import com.example.chat.database.model.Room
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener

class HomeViewModel : BaseViewModel<HomeNavigator>() {

    var roomsLiveData= MutableLiveData<List<Room>>()

    fun getRoomsList(){
        RoomDao.getRoomsList({result->
            val roomsList:MutableList<Room> = mutableListOf()
            for (document in result){
                val room = document.toObject(Room::class.java)
                roomsList.add(room)
            }
            roomsLiveData.value= roomsList
        }, {
            messageLiveData.value= it.localizedMessage
        })
    }

    fun goToAddRoom(){
        navigator?.goToAddRoom()
    }
}