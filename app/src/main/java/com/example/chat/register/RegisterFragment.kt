package com.example.chat.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chat.R
import com.example.chat.base.BaseFragment
import com.example.chat.databinding.ActivityRegisterBinding
import com.example.chat.home.HomeActivity

class RegisterFragment:BaseFragment<RegisterViewModel, ActivityRegisterBinding>(),RegisterNavigator {
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
        observerToLiveData()
    }
    private fun setUpViews() {
        viewBinding.txtInputName.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                viewBinding.txtInputName.error= null
            }

        })
        viewBinding.txtInputEmail.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                viewBinding.txtInputEmail.error= null
            }

        })
        viewBinding.txtInputUsername.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                viewBinding.txtInputUsername.error= null
            }

        })
        viewBinding.txtInputPassword.editText?.addTextChangedListener(object : TextWatcher {
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
        viewModel.nameError.observe(viewLifecycleOwner) {
            viewBinding.txtInputName.error = it
        }
        viewModel.emailError.observe(viewLifecycleOwner) {
            viewBinding.txtInputEmail.error = it
        }
        viewModel.userNameError.observe(viewLifecycleOwner) {
            viewBinding.txtInputUsername.error = it
        }
        viewModel.passwordError.observe(viewLifecycleOwner) {
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
        // todo go to Home Fragment
//        val intent= Intent(this, HomeActivity::class.java)
//        startActivity(intent)
        findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
    }

}