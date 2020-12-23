package com.dogood.thamizhinotes.ui.auth

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dogood.thamizhinotes.Constants
import com.dogood.thamizhinotes.ui.base.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class AuthViewModel @Inject constructor(context: Context):BaseViewModel<AuthNavigator>() {

    val currentScreen = MutableLiveData<Screen>()

    private val pref: SharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = pref.edit()

    private var firebaseAuth = Firebase.auth
    private lateinit var user: FirebaseUser

    val email = ObservableField("")
    val phoneNo = ObservableField("")
    val firstName = ObservableField("")
    val lastName = ObservableField("")
    val password = ObservableField("")

    enum class Screen{
        LOGIN_SCREEN,
        SIGN_UP_SCREEN,
        LOGIN_PASSWORD_SCREEN,
        SIGN_UP_PASSWORD_SCREEN,
        LOGIN_OTP_SCREEN,
        SIGN_UP_OTP_SCREEN,
        NAME,
        OTP_NAME,
        HOME,
        OTP_HOME,
        AGE,
        GOOGLE_SIGN_IN
    }

    private val mResponse= MutableLiveData<String>()
    val response: LiveData<String>
        get() = mResponse

    fun createUserWithEmailAndPassword(){
        firebaseAuth.createUserWithEmailAndPassword(email.get()!!.trim(),password.get()!!.trim())
            .addOnCompleteListener {
                if(it.isSuccessful){
                    user = firebaseAuth.currentUser!!
                    mResponse.value = "success"
                }else{
                    mResponse.value = "failure"
                }
            }
    }


    fun signInWithEmailAndPassword():Int{
        var result = 0
        firebaseAuth.signInWithEmailAndPassword(email.get()!!.trim(),password.get()!!.trim())
            .addOnCompleteListener {
                if(it.isSuccessful){
                    user = firebaseAuth.currentUser!!
                }else{
                    result = 1
                }
            }
            .addOnFailureListener {
                Log.d("login",it.message.toString())
            }
        return result
    }

    fun handleGSinResult(task: Task<GoogleSignInAccount>){
        try {
            val acc=task.result
            firebaseGoogleAuth(acc!!)
        }catch (e: ApiException){

        }
    }

    private fun firebaseGoogleAuth(acct: GoogleSignInAccount){
        try {
            val auth = FirebaseAuth.getInstance()
            val authCredentials = GoogleAuthProvider.getCredential(acct.idToken, null)
            auth.signInWithCredential(authCredentials).addOnCompleteListener { p0 ->
                if (p0.isSuccessful) {
//                    var user = auth.currentUser
                    createProfileWithGoogle(acct)
                }
            }
        }catch (e:Exception){

        }
    }

    private fun createProfileWithGoogle(acct: GoogleSignInAccount){
        try {
            firstName.set(acct.displayName.toString())
            email.set(acct.email.toString())
            mResponse.value = "gsiSuccess"
        }catch (e:Exception){
            mResponse.value="gsiFailure"
        }
    }
}