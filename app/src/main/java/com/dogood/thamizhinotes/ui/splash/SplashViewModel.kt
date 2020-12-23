package com.dogood.thamizhinotes.ui.splash

import com.dogood.thamizhinotes.R
import com.dogood.thamizhinotes.ui.base.BaseViewModel
import com.dogood.thamizhinotes.utils.resource.BaseResourceProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class SplashViewModel  @Inject constructor(resourceProvider: BaseResourceProvider) :
    BaseViewModel<SplashNavigator>() {

    private var auth = Firebase.auth.currentUser
    private var resource = resourceProvider

    fun defaultSettingsInCache(){
        resource.getString(R.string.app_name)
        loginStatus()
    }

    private fun loginStatus(){
        if(auth==null){
            decideNextActivity(false)
        }else{
            decideNextActivity(true)
        }

    }

    private fun decideNextActivity(type:Boolean){
        if(type){
            navigator.openHomeActivity()
        }else{
            navigator.openLoginActivity()
        }
    }
}