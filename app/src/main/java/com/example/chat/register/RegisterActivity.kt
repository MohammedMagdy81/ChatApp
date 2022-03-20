package com.example.chat.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.databinding.ActivityRegisterBinding
import com.example.chat.home.HomeActivity
import com.example.todoapp.base.BaseActivity

class RegisterActivity : BaseActivity<RegisterViewModel,ActivityRegisterBinding>(),RegisterNavigator{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm= viewModel
        setUpViews()
        observerToLiveData()

    }

    private fun setUpViews() {
       viewBinding.txtInputName.editText?.addTextChangedListener(object :TextWatcher{
           override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
           }

           override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
           }
           override fun afterTextChanged(p0: Editable?) {
               viewBinding.txtInputName.error= null
           }

       })
        viewBinding.txtInputEmail.editText?.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                viewBinding.txtInputEmail.error= null
            }

        })
        viewBinding.txtInputUsername.editText?.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                viewBinding.txtInputUsername.error= null
            }

        })
        viewBinding.txtInputPassword.editText?.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                viewBinding.txtInputPassword.error= null
            }

        })
     }

    private fun observerToLiveData() {
        viewModel.nameError.observe(this) {
            viewBinding.txtInputName.error = it
        }
        viewModel.emailError.observe(this) {
            viewBinding.txtInputEmail.error = it
        }
        viewModel.userNameError.observe(this) {
            viewBinding.txtInputUsername.error = it
        }
        viewModel.passwordError.observe(this) {
            viewBinding.txtInputPassword.error = it
        }
    }

    override fun initializeViewMode(): RegisterViewModel {
        return ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun goToHomeActivity() {
        val intent= Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }

}