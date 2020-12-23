package com.dogood.thamizhinotes.utils

import com.dogood.thamizhinotes.ui.auth.AuthViewModel

sealed class AuthUiEvent {
    class ShowSnackBar(val title: String, val msg: String, val action: String? = null): AuthUiEvent()
    class HideSnackBar(val nothing: Unit): AuthUiEvent()
    class Navigate(val screen: AuthViewModel.Screen, vararg val params: String?): AuthUiEvent()
}

data class AuthUiEventWrapper (val authUiEvent: AuthUiEvent)
