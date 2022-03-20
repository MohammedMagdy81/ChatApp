package com.example.chat.roomDetails

import android.graphics.drawable.ClipDrawable.VERTICAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.R
import com.example.chat.database.dao.MessageDao
import com.example.chat.database.model.Message
import com.example.chat.database.model.Room
import com.example.chat.databinding.ActivityRoomDetailsBinding
import com.example.todoapp.base.BaseActivity
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query

class RoomDetailsActivity : BaseActivity<RoomDetailsViewModel,ActivityRoomDetailsBinding>() {
    var room : Room?= null
    lateinit var adapter : MessagesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(viewBinding.toolBar)
        viewBinding.vm= viewModel
        room = intent.getParcelableExtra("room")
        supportActionBar?.title=room?.name

        viewModel.roomId= room?.id
        initRecyclerView()
        subescripeToMessage(room?.id?:"")
    }

    private fun subescripeToMessage(roomId: String?)
    {
        MessageDao.getMessagesRef(roomId?:"")
            .orderBy("time",Query.Direction.ASCENDING)
            .addSnapshotListener { snapshpt, e ->
                if (e!=null){
                    showDialog(message = e.localizedMessage)
                }
                val addedMessages= mutableListOf<Message>()
                for (dc in snapshpt!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED->{
                        val newMessage= dc.document.toObject(Message::class.java)
                            addedMessages.add(newMessage)
                        }
                    }
                }
                adapter.addedMessages(addedMessages)
                viewBinding.messagesRecyclerView.smoothScrollToPosition(adapter.messagesList.size)
            }
    }

    private fun initRecyclerView() {
        adapter= MessagesAdapter(mutableListOf())
        val layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        viewBinding.messagesRecyclerView.layoutManager=layoutManager
        layoutManager.stackFromEnd=true
        viewBinding.messagesRecyclerView.adapter=adapter
    }

    override fun initializeViewMode(): RoomDetailsViewModel {
        return ViewModelProvider(this).get(RoomDetailsViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_room_details
    }
}