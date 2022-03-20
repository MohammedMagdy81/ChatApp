package com.example.chat.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VM:BaseViewModel<*>,DB: ViewDataBinding>: Fragment() {


    lateinit var viewBinding:DB
    lateinit var viewModel:VM


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding= DataBindingUtil.inflate(inflater,getLayoutId(),container,false)
        viewModel= initializeViewMode()

        viewModel.messageLiveData.observe(viewLifecycleOwner) { message ->
            showDialog(
                message = message,
                posActionName = "Ok",
                posAction = { dialog, i ->
                    dialog.dismiss()
                })
        }
        viewModel.showLoad.observe(viewLifecycleOwner) { show ->
            if (show) {
                showProgressDialog("Load . . .")
            }
            else {
                hideProgressDialog()
            }

        }

        return viewBinding.root
    }

    abstract fun initializeViewMode(): VM
    abstract fun getLayoutId(): Int

    fun showDialog(title :String?=null,
                   message :String?=null,
                   posActionName :String?=null,
                   negActionName :String?=null,
                   posAction : DialogInterface.OnClickListener?=null,
                   negAction : DialogInterface.OnClickListener?=null,
                   isCancelable:Boolean = true){
        val builder= AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(isCancelable)
        builder.setPositiveButton(posActionName,posAction)
        builder.setNegativeButton(negActionName,negAction)
        builder.show()
    }
    fun makeToast(message:String){
        Toast.makeText(requireContext(),message, Toast.LENGTH_LONG).show()
    }
    var dialog : ProgressDialog?=null
    fun showProgressDialog(message:String){
        dialog= ProgressDialog(requireContext())
        dialog?.setCancelable(false)
        dialog?.setMessage(message)
        dialog?.show()
    }
    fun hideProgressDialog(){
        dialog?.dismiss()
    }
}