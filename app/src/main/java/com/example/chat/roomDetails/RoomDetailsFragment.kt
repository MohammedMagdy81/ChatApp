package com.example.chat.roomDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chat.R
import com.example.chat.base.BaseFragment
import com.example.chat.database.dao.MessageDao
import com.example.chat.database.model.Message
import com.example.chat.database.model.Room
import com.example.chat.databinding.ActivityRoomDetailsBinding
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query

class RoomDetailsFragment: BaseFragment<RoomDetailsViewModel, ActivityRoomDetailsBinding>() {
    var room : Room?= null
    lateinit var adapter : MessagesAdapter
    val args:RoomDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //setSupportActionBar(viewBinding.toolBar)
        // todo receive arg from Save Arg
        room= args.room
       // room = intent.getParcelableExtra("room")
        //supportActionBar?.title=room?.name
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.vm= viewModel
        viewModel.roomId= room?.id
        initRecyclerView()
        subescripeToMessage(room?.id?:"")
    }
    private fun subescripeToMessage(roomId: String?)
    {
        MessageDao.getMessagesRef(roomId?:"")
            .orderBy("time", Query.Direction.ASCENDING)
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
        val layoutManager = LinearLayoutManager(context,
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