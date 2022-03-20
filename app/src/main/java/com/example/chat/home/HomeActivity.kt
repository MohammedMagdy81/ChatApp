package com.example.chat.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.addRoom.AddRoomActivity
import com.example.chat.database.model.Room
import com.example.chat.databinding.ActivityHomeBinding
import com.example.chat.roomDetails.RoomDetailsActivity
import com.example.todoapp.base.BaseActivity

class HomeActivity : BaseActivity<HomeViewModel,ActivityHomeBinding>() ,HomeNavigator{
    lateinit var adapter : HomeRoomsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator= this
        viewBinding.vm= viewModel
        setUpViews()
        observeToLivedata()
    }

    private fun observeToLivedata()  {
        viewModel.roomsLiveData.observe(this) { roomsList ->
            adapter.changeData(roomsList)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getRoomsList()
    }

    private fun setUpViews() {
        adapter= HomeRoomsAdapter(listOf())
        adapter.onItemClick=object :HomeRoomsAdapter.OnItemClick{
            override fun onClickItem(position: Int, room: Room) {
                val intent= Intent(this@HomeActivity,RoomDetailsActivity::class.java)
                intent.putExtra("room",room)
                startActivity(intent)
            }

        }
        viewBinding.recyclerRooms.adapter=adapter
    }

    override fun initializeViewMode(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun goToAddRoom() {
        val intent= Intent(this,AddRoomActivity::class.java)
        startActivity(intent)
    }

}