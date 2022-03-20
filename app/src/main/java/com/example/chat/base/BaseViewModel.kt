package com.example.chat.base;

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<N> : ViewModel(){
    var navigator: N?=null
    var messageLiveData= MutableLiveData<String>()
    var showLoad= MutableLiveData<Boolean>()
}
