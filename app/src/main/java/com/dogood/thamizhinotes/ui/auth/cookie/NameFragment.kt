package com.dogood.thamizhinotes.ui.auth.cookie

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.dogood.thamizhinotes.BR
import com.dogood.thamizhinotes.R
import com.dogood.thamizhinotes.databinding.FragmentNameBinding
import com.dogood.thamizhinotes.ui.auth.AuthViewModel
import com.dogood.thamizhinotes.ui.base.BaseFragment
import com.dogood.thamizhinotes.utils.AuthUiEvent
import com.dogood.thamizhinotes.utils.RxBus
import com.dogood.thamizhinotes.utils.onClick
import javax.inject.Inject

class NameFragment : BaseFragment<FragmentNameBinding, NameViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_name
    override val viewModel: NameViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(NameViewModel::class.java)
    private lateinit var mBinding:FragmentNameBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        initView()
        viewModel.firstName.set("")
        viewModel.lastName.set("")
    }

    private fun initView(){
        mBinding.authFinish.onClick {
            when {
                viewModel.firstName.get()!!.trim().isEmpty() -> {
                    showSnackBar(getString(R.string.invalid),getString(R.string.firstname_should_not_be_empty),"")
                }
                viewModel.lastName.get()!!.trim().isEmpty() -> {
                    showSnackBar(getString(R.string.invalid),getString(R.string.lastname_should_not_be_empty),"")
                }
                else -> {
                    mBinding.authFinish.visibility = View.GONE
                    mBinding.progressBar.visibility = View.VISIBLE
                    RxBus.publish(AuthUiEvent.Navigate(AuthViewModel.Screen.AGE,viewModel.firstName.get()!!.trim(),viewModel.lastName.get()!!.trim()))
                }
            }
        }
    }
}