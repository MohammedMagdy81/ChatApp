package com.example.chat.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.databinding.ActivityLoginBinding
import com.example.chat.home.HomeActivity
import com.example.chat.register.RegisterActivity
import com.example.chat.register.RegisterViewModel
import com.example.todoapp.base.BaseActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : BaseActivity<LoginViewModel,ActivityLoginBinding>() , LoginNavigator{

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = Firebase.auth

        viewBinding.vm= viewModel
        viewModel.navigator =this

    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser?.uid!=null){
            goToHome()
        }
    }

    override fun initializeViewMode(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun goToRegister() {
        val intent= Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun goToHome() {
        val intent= Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}