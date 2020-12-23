package com.dogood.thamizhinotes.ui.base

interface BaseNavigator{

    //  fun navigateScreen(screen: AuthViewModel.PHScreen, vararg params: String?)

    fun hideSnackBar()

    fun hideKeyboard()

    fun showSnackBar(title: String, msg: String, action: String?)

}