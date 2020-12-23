package com.dogood.thamizhinotes.ui.auth.password

import androidx.databinding.ObservableField
import com.dogood.thamizhinotes.ui.auth.AuthNavigator
import com.dogood.thamizhinotes.ui.base.BaseViewModel
import com.dogood.thamizhinotes.utils.resource.ResourceProvider
import javax.inject.Inject

class LoginPasswordViewModel @Inject constructor(resourceProvider: ResourceProvider): BaseViewModel<AuthNavigator>(){

    val password = ObservableField("")
    val resource = resourceProvider

    fun isValidPassword():Int{
        return if(password.get()!!.trim().length<7){
            0
        }else{
            1
        }
    }

}