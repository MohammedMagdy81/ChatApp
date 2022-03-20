package com.example.chat.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chat.R
import com.example.chat.base.BaseFragment
import com.example.chat.databinding.ActivityLoginBinding
import com.example.chat.home.HomeActivity
import com.example.chat.register.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment:BaseFragment<LoginViewModel,ActivityLoginBinding>() , LoginNavigator {
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firebaseAuth = Firebase.auth
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        // todo go to Register Fragment
//        val intent= Intent(this, RegisterActivity::class.java)
//        startActivity(intent)
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    override fun goToHome() {
        // todo go to Home Fragment
//        val intent= Intent(this, HomeActivity::class.java)
//        startActivity(intent)
//        finish()
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

}