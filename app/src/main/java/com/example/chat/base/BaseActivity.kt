package com.example.todoapp.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.example.chat.base.BaseViewModel

abstract class BaseActivity<VM:BaseViewModel<*>,DB:ViewDataBinding> :AppCompatActivity() {

    lateinit var viewBinding:DB
    lateinit var viewModel:VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=DataBindingUtil.setContentView(this,getLayoutId())
        viewModel= initializeViewMode()

        viewModel.messageLiveData.observe(this) { message ->
            showDialog(
                message = message,
                posActionName = "Ok",
                posAction = { dialog, i ->
                dialog.dismiss()
                })
        }
        viewModel.showLoad.observe(this) { show ->
            if (show) {
                showProgressDialog("Load . . .")
            }
            else {
                hideProgressDialog()
            }

        }
    }

    abstract fun initializeViewMode(): VM
    abstract fun getLayoutId(): Int


    fun showDialog(title :String?=null,
                   message :String?=null,
                   posActionName :String?=null,
                   negActionName :String?=null,
                   posAction :DialogInterface.OnClickListener?=null,
                   negAction :DialogInterface.OnClickListener?=null,
    isCancelable:Boolean = true){
        val builder= AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(isCancelable)
        builder.setPositiveButton(posActionName,posAction)
        builder.setNegativeButton(negActionName,negAction)
        builder.show()
    }
    fun makeToast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
    var dialog : ProgressDialog?=null
    fun showProgressDialog(message:String){
        dialog= ProgressDialog(this)
        dialog?.setCancelable(false)
        dialog?.setMessage(message)
        dialog?.show()
    }
    fun hideProgressDialog(){
        dialog?.dismiss()
    }
}