package com.dogood.thamizhinotes.ui.auth.otp

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dogood.thamizhinotes.Constants
import com.dogood.thamizhinotes.data.AppPreferenceHelper
import com.dogood.thamizhinotes.ui.auth.AuthNavigator
import com.dogood.thamizhinotes.ui.base.BaseViewModel
import com.dogood.thamizhinotes.utils.resource.ResourceProvider
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class LoginOtpViewModel @Inject constructor(context: Context, val resourceProvider: ResourceProvider): BaseViewModel<AuthNavigator>() {

    val otp = ObservableField("")
    val auth = Firebase.auth

    private val pref: SharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    var codeSent = pref.getString(AppPreferenceHelper.PREF_KEY_CODE_SENT,"123456")

    private val mResponse= MutableLiveData<String>()
    val response: LiveData<String>
        get() = mResponse

    fun verifySignInCode(activity: Activity){
        if(otp.get()!!.isNotEmpty()) {
            val credential = PhoneAuthProvider.getCredential(codeSent!!, otp.get()!!)
            signInWithPhoneAuthCredential(activity,credential)
        }else{
            mResponse.value="error"
        }
    }

    private fun signInWithPhoneAuthCredential(activity: Activity, credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
//                    val user = task.result?.user
                    mResponse.value="success"
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        mResponse.value="incorrect"
                    }
                }
            }
    }
}