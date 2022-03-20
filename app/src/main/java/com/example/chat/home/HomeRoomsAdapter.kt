package com.example.chat.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.R
import com.example.chat.database.model.Room
import com.example.chat.databinding.LayoutRoomBinding

class HomeRoomsAdapter(var roomsList:List<Room>): RecyclerView.Adapter<HomeRoomsAdapter.HomeViewHolder>() {


    class HomeViewHolder(val itemBinding:LayoutRoomBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(room:Room){
            itemBinding.room= room
        }
    }

    var onItemClick:OnItemClick?=null
    interface OnItemClick{
        fun onClickItem(position:Int,room:Room)
    }
    fun changeData(roomsList:List<Room>){
        this.roomsList=roomsList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
       val itemBinding:LayoutRoomBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
           R.layout.layout_room,parent,false)
        return HomeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
       val room = roomsList[position]
        holder.bind(room)
        if (onItemClick!=null){
            holder.itemView.setOnClickListener {
                onItemClick?.onClickItem(position,room)
            }
        }
    }

    override fun getItemCount() = roomsList.size
}