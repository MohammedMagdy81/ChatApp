package com.example.chat.addRoom

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.databinding.ActivityAddRoomBinding
import com.example.todoapp.base.BaseActivity

class AddRoomActivity : BaseActivity<AddRoomViewModel,ActivityAddRoomBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm= viewModel
        setUpViews()
        observeYoLiveData()

    }

    private fun setUpViews() {
        viewBinding.txtInputRoomName.editText?.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                viewBinding.txtInputRoomName.error=null
            }

        })
        viewBinding.txtInputRoomDesc.editText?.addTextChangedListener(object :TextWatcher{
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
        viewModel.roomNameError.observe(this) { message ->
            viewBinding.txtInputRoomName.error= message
        }
        viewModel.roomDescError.observe(this) {message->
            viewBinding.txtInputRoomDesc.error=message
        }
        viewModel.roomAdded.observe(this) { roomAdded ->
            if (roomAdded){
                showDialog(message = "Room Added Successfully", posActionName = "Ok",
                posAction = { dialog, i ->
                    dialog.dismiss()
                    finish()
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