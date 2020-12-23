package com.dogood.thamizhinotes.ui.auth

interface AuthNavigator {

    fun navigateToScreen(screen: AuthViewModel.Screen,vararg params:String?)

}