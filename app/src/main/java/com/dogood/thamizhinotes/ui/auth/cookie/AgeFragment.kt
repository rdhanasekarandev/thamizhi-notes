package com.dogood.thamizhinotes.ui.auth.cookie

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.dogood.thamizhinotes.BR
import com.dogood.thamizhinotes.R
import com.dogood.thamizhinotes.databinding.FragmentAgeBinding
import com.dogood.thamizhinotes.ui.auth.AuthViewModel
import com.dogood.thamizhinotes.ui.base.BaseFragment
import com.dogood.thamizhinotes.utils.AuthUiEvent
import com.dogood.thamizhinotes.utils.RxBus
import com.dogood.thamizhinotes.utils.onClick
import javax.inject.Inject

class AgeFragment : BaseFragment<FragmentAgeBinding, AgeViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_age
    override val viewModel: AgeViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(AgeViewModel::class.java)
    private lateinit var mBinding: FragmentAgeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding!!
        initView()
        viewModel.age.set("")
    }

    private fun initView(){
        mBinding.authFinish.onClick {
            when {
                viewModel.birthday.get()!!.trim().isEmpty() -> {
                    showSnackBar(getString(R.string.invalid),getString(R.string.please_enter_your_birthday),"")
                }
                else -> {
                    mBinding.authFinish.visibility = View.GONE
                    mBinding.progressBar.visibility = View.VISIBLE
                    RxBus.publish(AuthUiEvent.Navigate(AuthViewModel.Screen.AGE,viewModel.birthday.get()!!.trim(),viewModel.age.get()!!.trim()))
                }
            }
        }
    }
}