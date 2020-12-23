package com.dogood.thamizhinotes.ui.auth.login

import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dogood.thamizhinotes.ui.auth.AuthNavigator
import com.dogood.thamizhinotes.ui.base.BaseViewModel
import com.dogood.thamizhinotes.utils.resource.ResourceProvider
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import javax.inject.Inject

class LoginViewModel @Inject constructor(resourceProvider: ResourceProvider):BaseViewModel<AuthNavigator>() {

    val emailOrPhoneNo = ObservableField("")
    val codeSent = ObservableField("")
    val resource = resourceProvider

    fun isEmailOrPhoneNo():Int{
        return if(emailOrPhoneNo.get()!!.trim().matches(Regex("^[0-9]*\$")) && emailOrPhoneNo.get()!!.length>4){
            if(verifyPhoneNo(emailOrPhoneNo.get()!!.trim())){
                0
            }else{
                1
            }
        }else{
            if(verifyEmailAddress(emailOrPhoneNo.get()!!.trim())){
                2
            }else{
                3
            }
        }
    }

    private val mResponse= MutableLiveData<String>()
    val response: LiveData<String>
        get() = mResponse

    var callbacks=object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            mResponse.value="not"
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            codeSent.set(p0.toString())
            mResponse.value="fail"
        }

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(p0, p1)
            codeSent.set(p0)
            mResponse.value="ok"
        }
    }

    private fun verifyEmailAddress(email:String):Boolean{
        return (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }

    private fun verifyPhoneNo(phoneNumber:String):Boolean{
        return (phoneNumber.isNotEmpty() && phoneNumber.length==10)
    }

}