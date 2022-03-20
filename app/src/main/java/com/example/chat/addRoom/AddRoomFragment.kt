package com.example.chat.addRoom

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.base.BaseFragment
import com.example.chat.databinding.ActivityAddRoomBinding

class AddRoomFragment :BaseFragment<AddRoomViewModel,ActivityAddRoomBinding>()  {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.vm= viewModel
        setUpViews()
        observeYoLiveData()
    }
    private fun setUpViews() {
        viewBinding.txtInputRoomName.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                viewBinding.txtInputRoomName.error=null
            }

        })
        viewBinding.txtInputRoomDesc.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                viewBinding.txtInputRoomDesc.error=null
            }

        })

    }
    private fun observeYoLiveData() {
        viewModel.roomNameError.observe(viewLifecycleOwner) { message ->
            viewBinding.txtInputRoomName.error= message
        }
        viewModel.roomDescError.observe(viewLifecycleOwner) {message->
            viewBinding.txtInputRoomDesc.error=message
        }
        viewModel.roomAdded.observe(viewLifecycleOwner) { roomAdded ->
            if (roomAdded){
                showDialog(message = "Room Added Successfully", posActionName = "Ok",
                    posAction = { dialog, i ->
                        dialog.dismiss()
                        //finish()
                    },isCancelable = false)
            }
        }
    }

    override fun initializeViewMode(): AddRoomViewModel {
        return ViewModelProvider(this).get(AddRoomViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_add_room
    }
}