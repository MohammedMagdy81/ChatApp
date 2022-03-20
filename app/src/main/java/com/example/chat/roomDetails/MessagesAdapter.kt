package com.example.chat.roomDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.Data
import com.example.chat.R
import com.example.chat.database.model.Message
import com.example.chat.databinding.LayoutReceivedMessageBinding
import com.example.chat.databinding.LayoutSentMessageBinding
import kotlin.contracts.Returns

class MessagesAdapter(var messagesList:MutableList<Message>) :RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>(){

    val SENT_VIEW_TYPE= 1
    val RECEIVED_VIEW_TYPE= 2


    abstract class MessageViewHolder(view:View) : RecyclerView.ViewHolder(view){
       abstract fun bind(message:Message)
    }
    class ReceiverMessageViewHolder(val itemBinding : LayoutReceivedMessageBinding) : MessageViewHolder(itemBinding.root){
        override fun bind(message: Message) {
            itemBinding.setMessage(message)
        }

    }

    class SenderMessageViewHolder(val itemBinding : LayoutSentMessageBinding) : MessageViewHolder(itemBinding.root){
        override fun bind(message: Message) {
            itemBinding.setMessage(message)
        }

    }

    override fun getItemViewType(position: Int): Int {
        var message= messagesList[position]
        if (message.senderId.equals(Data.user?.id)){
            return SENT_VIEW_TYPE
        }else {
            return RECEIVED_VIEW_TYPE
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if (viewType==SENT_VIEW_TYPE){
            val view : LayoutSentMessageBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.layout_sent_message,parent,false)
            SenderMessageViewHolder(view)
        }else {
            val view : LayoutReceivedMessageBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.layout_received_message,parent,false)
            ReceiverMessageViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message=messagesList[position]
        holder.bind(message)

    }

    override fun getItemCount() = messagesList.size
    fun addedMessages(addedMessages: MutableList<Message>) {
        val oldMessageSize= messagesList.size
        messagesList.addAll(addedMessages)

        notifyItemRangeInserted(oldMessageSize,addedMessages.size)
    }
}




