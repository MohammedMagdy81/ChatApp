package com.example.chat.login

import androidx.databinding.ObservableField
import com.example.chat.Data
import com.example.chat.base.BaseViewModel
import com.example.chat.database.model.User
import com.example.chat.database.dao.UserDao
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : BaseViewModel<LoginNavigator>() {

    val email= ObservableField<String>()
    val password= ObservableField<String>()
    val emailError= ObservableField<Boolean>(false)
    val passwordError= ObservableField<Boolean>(false)
     val firebaseAuth = Firebase.auth

    fun login(){
        // validate inputs
        // show Errors or hide it
        if(!validate()) return
        showLoad.value=true
        firebaseAuth.signInWithEmailAndPassword(email.get()!!,password.get()!!)
            .addOnCompleteListener { task->
                showLoad.value= false
                if (task.isSuccessful){
                    //retrieve user from DB
                   getUserData(firebaseAuth.currentUser!!.uid)
                }else{
                    messageLiveData.value= task.exception?.localizedMessage
                }
            }

    }

    fun getUserData(userId:String){
        UserDao.getUserdata(userId) { task->
            showLoad.value=false
            if (task.isSuccessful){
                val user= task.result.toObject(User::class.java)
                Data.user= user
                goToHome()
            }else{
                messageLiveData.value=task.exception?.localizedMessage
            }
        }
    }

    fun validate():Boolean{
        var isValid= true

        if (email.get().isNullOrBlank()){
            //show email error
            emailError.set(true)
            isValid= false
        }else{
            // hide email error
        }
        if (password.get().isNullOrBlank()){
            // show password error
            passwordError.set(true)
            isValid= false
        }else{
            //hide password error
        }
        return isValid
    }

    fun goToRegister(){
        navigator?.goToRegister()
    }
    fun goToHome(){
        navigator?.goToHome()
    }
}