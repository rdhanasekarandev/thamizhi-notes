package com.dogood.thamizhinotes.ui.auth.password

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.dogood.thamizhinotes.BR
import com.dogood.thamizhinotes.R
import com.dogood.thamizhinotes.databinding.FragmentCreatePasswordBinding
import com.dogood.thamizhinotes.ui.auth.AuthViewModel
import com.dogood.thamizhinotes.ui.base.BaseFragment
import com.dogood.thamizhinotes.utils.AuthUiEvent
import com.dogood.thamizhinotes.utils.RxBus
import com.dogood.thamizhinotes.utils.onClick
import javax.inject.Inject

class CreatePasswordFragment : BaseFragment<FragmentCreatePasswordBinding, CreatePasswordViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_create_password
    override val viewModel: CreatePasswordViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(CreatePasswordViewModel::class.java)
    private lateinit var mBinding: FragmentCreatePasswordBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        initView()
    }

    private fun initView(){
        mBinding.authNext.onClick {
            hideKeyboard()
            when {
                viewModel.isValidPassword()==0 -> {
                    showSnackBar(getString(R.string.invalid),getString(R.string.password_length_7_letters),"")
                }
                viewModel.isValidPassword()>0 -> {
                    RxBus.publish(AuthUiEvent.Navigate(AuthViewModel.Screen.NAME,viewModel.password.get()!!.trim()))
                }
            }
        }
    }
}