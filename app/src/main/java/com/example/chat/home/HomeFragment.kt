package com.example.chat.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chat.R
import com.example.chat.addRoom.AddRoomActivity
import com.example.chat.base.BaseFragment
import com.example.chat.database.model.Room
import com.example.chat.databinding.ActivityHomeBinding
import com.example.chat.roomDetails.RoomDetailsActivity
import com.example.chat.roomDetails.RoomDetailsFragment
import com.example.chat.roomDetails.RoomDetailsFragmentArgs

class HomeFragment: BaseFragment<HomeViewModel,ActivityHomeBinding>() ,HomeNavigator {
    lateinit var adapter : HomeRoomsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator= this
        viewBinding.vm= viewModel
        setUpViews()
        observeToLivedata()
    }
    private fun setUpViews() {
        adapter= HomeRoomsAdapter(listOf())
        adapter.onItemClick=object :HomeRoomsAdapter.OnItemClick{
            override fun onClickItem(position: Int, room: Room) {
                // todo go to RoomDetailsFragment
//                val intent= Intent(this@HomeActivity, RoomDetailsActivity::class.java)
//                intent.putExtra("room",room)
//                startActivity(intent)
                val action =HomeFragmentDirections.actionHomeFragmentToRoomDetailsFragment(room)
                findNavController().navigate(action)
            }

        }
        viewBinding.recyclerRooms.adapter=adapter
    }
    private fun observeToLivedata()  {
        viewModel.roomsLiveData.observe(viewLifecycleOwner) { roomsList ->
            adapter.changeData(roomsList)
        }
    }
    override fun initializeViewMode(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }
    override fun goToAddRoom() {
        findNavController()
            .navigate(R.id.action_homeFragment_to_addRoomFragment)
        // todo go To Add Room Fragment
//        val intent= Intent(this, AddRoomActivity::class.java)
//        startActivity(intent)
    }
    override fun onStart() {
        super.onStart()
        viewModel.getRoomsList()
    }

}