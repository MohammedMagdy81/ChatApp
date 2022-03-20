package com.example.chat.register

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chat.Data
import com.example.chat.base.BaseViewModel
import com.example.chat.database.model.User
import com.example.chat.database.dao.UserDao
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel : BaseViewModel<RegisterNavigator>() {
    val name = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val userName = ObservableField<String>()

    val nameError= MutableLiveData<String>()
    val passwordError= MutableLiveData<String>()
    val emailError= MutableLiveData<String>()
    val userNameError= MutableLiveData<String>()
    val firebaseAuth= Firebase.auth

    fun register(){
        if(!valid()) return
        showLoad.value=true
        firebaseAuth.createUserWithEmailAndPassword(email.get().toString(),password.get().toString())
            .addOnCompleteListener { task->
                showLoad.value= false
                if (task.isSuccessful){
                    messageLiveData.value="Register Successfully . . . "
                    val user = User(firebaseAuth.currentUser?.uid,name.get(),userName.get(),email.get())
                    addUserToDB(user)
                }else{
                    messageLiveData.value = task.exception?.localizedMessage
                }
            }
    }
fun addUserToDB(user: User){
    UserDao.addUser(user, OnCompleteListener {
        showLoad.value=false
        if (it.isSuccessful){
            Data.user= user
            goToHomeActivity()
            messageLiveData.value= "User Added in Db"
        }else{
            messageLiveData.value =it.exception?.localizedMessage
        }
    })
}

    private fun goToHomeActivity() {
        navigator?.goToHomeActivity()
    }


    private fun valid(): Boolean {
        var isValid = true
        if (name.get().isNullOrBlank()){
            isValid= false
            nameError.value= "Please Enter Name !"
        }
        if (userName.get().isNullOrBlank()){
            isValid= false
            userNameError.value= "Please Enter UserName !"
        }
        if (email.get().isNullOrBlank()){
            isValid= false
            emailError.value= "Please Enter E-mail !"
        }
        if (password.get().isNullOrBlank()){
            isValid= false
            passwordError.value="Please Enter Password !"
        }
        return isValid
    }
}