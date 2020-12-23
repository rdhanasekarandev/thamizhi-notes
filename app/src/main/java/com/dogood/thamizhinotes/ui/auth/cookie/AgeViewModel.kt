package com.dogood.thamizhinotes.ui.auth.cookie

import androidx.databinding.ObservableField
import com.dogood.thamizhinotes.ui.auth.AuthNavigator
import com.dogood.thamizhinotes.ui.base.BaseViewModel
import com.dogood.thamizhinotes.utils.resource.ResourceProvider
import javax.inject.Inject

class AgeViewModel  @Inject constructor(val resourceProvider: ResourceProvider):
    BaseViewModel<AuthNavigator>(){

    val age = ObservableField("")

    val birthday = ObservableField("")
}