package com.example.chat.addRoom

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chat.base.BaseViewModel
import com.example.chat.database.dao.RoomDao
import com.example.chat.database.model.Room
class AddRoomViewModel : BaseViewModel<AddRoomNavigator>() {
    val roomName= ObservableField<String>()
    val roomDesc= ObservableField<String>()
    val roomNameError= MutableLiveData<String>()
    val roomDescError= MutableLiveData<String>()
    val roomAdded= MutableLiveData<Boolean>()

    fun addRoom(){
        if (!valid()) return
        showLoad.value=true
        val room= Room(name = roomName.get(), desc = roomDesc.get())
        RoomDao.insertRoom(room) { task ->
            showLoad.value= false
            if (task.isSuccessful){
                roomAdded.value=true
                messageLiveData.value= "Room Added  Successfully . . ."
            }else{
                messageLiveData.value= task.exception?.localizedMessage
            }
        }
    }

    private fun valid(): Boolean {
     var isValid= true
     if (roomName.get().isNullOrBlank()){
         isValid=false
         roomNameError.value= "Please Enter Room Name !"
     }
        if (roomDesc.get().isNullOrBlank()){
            isValid=false
            roomDescError.value= "Please Enter Room Desc !"
        }
     return isValid
    }
}