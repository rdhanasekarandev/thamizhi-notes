package com.dogood.thamizhinotes.ui.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.dogood.thamizhinotes.BR
import com.dogood.thamizhinotes.R
import com.dogood.thamizhinotes.data.AppPreferenceHelper
import com.dogood.thamizhinotes.databinding.ActivityAuthBinding
import com.dogood.thamizhinotes.ui.auth.cookie.AgeFragment
import com.dogood.thamizhinotes.ui.auth.cookie.NameFragment
import com.dogood.thamizhinotes.ui.auth.create.CreateFragment
import com.dogood.thamizhinotes.ui.auth.login.LoginFragment
import com.dogood.thamizhinotes.ui.auth.otp.CreateOtpFragment
import com.dogood.thamizhinotes.ui.auth.otp.LoginOtpFragment
import com.dogood.thamizhinotes.ui.auth.password.CreatePasswordFragment
import com.dogood.thamizhinotes.ui.auth.password.LoginPasswordFragment
import com.dogood.thamizhinotes.ui.base.BaseActivity
import com.dogood.thamizhinotes.utils.AuthUiEvent
import com.dogood.thamizhinotes.utils.RxBus
import com.dogood.thamizhinotes.utils.addFragmentToActivity
import com.dogood.thamizhinotes.utils.replaceFragmentInActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthActivity : BaseActivity<ActivityAuthBinding,AuthViewModel>(),AuthNavigator {

    @Inject lateinit var viewModelFactory:ViewModelProvider.Factory
    private var eventCompositeDisposal = CompositeDisposable()
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_auth
    override val viewModel: AuthViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(AuthViewModel::class.java)
    private lateinit var mBinding: ActivityAuthBinding
    private val googleSignIn = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding!!
        initRxBusListener()
        entryPoint(savedInstanceState)
        subscribeToLiveData()
    }

    private fun entryPoint(savedInstanceState: Bundle?){
        if(savedInstanceState==null){
            addFragmentToActivity(mBinding.flAuth.id, LoginFragment(),"Login")
        }
    }

    private fun subscribeToLiveData(){
        viewModel.response.observe(this,{
            when (it) {
                "gsiSuccess" -> {
                    showSnackBar("success","success","")
                }
                "success" -> {
                    replaceFragmentInActivity(mBinding.flAuth.id,AgeFragment(),"Age")
                }
                "failure" -> {
                    showSnackBar(getString(R.string.error),getString(R.string.error_while_creating_the_account),"")
                }
                else -> {
                    showSnackBar("Failure","success","")
                }
            }
        })
    }

    private fun initRxBusListener() {
        eventCompositeDisposal.add(
            RxBus.listen(AuthUiEvent.Navigate::class.java)
                .debounce(200, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .doOnNext { hideSnackBar() }
                .subscribe { navigateToScreen(it.screen, *it.params) })
    }

    override fun navigateToScreen(screen: AuthViewModel.Screen, vararg params: String?) {
        try{
            viewModel.currentScreen.value = screen
            when(screen){
                AuthViewModel.Screen.LOGIN_SCREEN -> { moveToLoginScreen() }
                AuthViewModel.Screen.LOGIN_PASSWORD_SCREEN -> { moveToLoginPasswordScreen(params[0]!!) }
                AuthViewModel.Screen.SIGN_UP_SCREEN -> { moveToSignUpScreen() }
                AuthViewModel.Screen.SIGN_UP_PASSWORD_SCREEN -> { moveToSignUpPasswordScreen(params[0]!!) }
                AuthViewModel.Screen.LOGIN_OTP_SCREEN -> { moveToLoginOtpScreen(params[0]!!,params[1]!!) }
                AuthViewModel.Screen.SIGN_UP_OTP_SCREEN -> { moveToSignUpOtpScreen(params[0]!!,params[1]!!) }
                AuthViewModel.Screen.NAME -> { moveToNameScreen(params[0]!!) }
                AuthViewModel.Screen.OTP_NAME -> { moveToNameScreen() }
                AuthViewModel.Screen.AGE -> { moveToAgeScreen(params[0]!!,params[1]!!) }
                AuthViewModel.Screen.HOME -> { moveToHomeActivity(params[0]!!) }
                AuthViewModel.Screen.OTP_HOME -> { moveToHomeActivity() }
                AuthViewModel.Screen.GOOGLE_SIGN_IN -> { gsi() }
            }
        }catch (e: Exception){
            print(e.stackTrace)
        }
    }

    private fun moveToLoginScreen(){
        addFragmentToActivity(mBinding.flAuth.id,LoginFragment(),"Login")
    }

    private fun moveToLoginPasswordScreen(email:String){
        viewModel.email.set(email)
        replaceFragmentInActivity(mBinding.flAuth.id,LoginPasswordFragment(),"LoginPassword")
    }

    private fun moveToLoginOtpScreen(phoneNo:String,codeSent:String){
        viewModel.phoneNo.set(phoneNo)
        viewModel.editor.putString(AppPreferenceHelper.PREF_KEY_CODE_SENT,codeSent)
        viewModel.editor.commit()
        viewModel.editor.apply()
        replaceFragmentInActivity(mBinding.flAuth.id,LoginOtpFragment(),"otp")
    }

    private fun moveToSignUpScreen(){
        addFragmentToActivity(mBinding.flAuth.id,CreateFragment(),"Create")
    }

    private fun moveToSignUpPasswordScreen(email:String){
        viewModel.email.set(email)
        replaceFragmentInActivity(mBinding.flAuth.id,CreatePasswordFragment(),"CreatePassword")
    }

    private fun moveToSignUpOtpScreen(phoneNo:String,codeSent:String){
        viewModel.phoneNo.set(phoneNo)
        viewModel.editor.putString(AppPreferenceHelper.PREF_KEY_CODE_SENT,codeSent)
        viewModel.editor.commit()
        viewModel.editor.apply()
        replaceFragmentInActivity(mBinding.flAuth.id,CreateOtpFragment(),"otp")
    }

    private fun moveToNameScreen(password:String){
        viewModel.password.set(password)
        replaceFragmentInActivity(mBinding.flAuth.id,NameFragment(),"Name")
    }

    private fun moveToNameScreen(){
        replaceFragmentInActivity(mBinding.flAuth.id,NameFragment(),"Name")
    }

    private fun moveToAgeScreen(firstName:String,lastName:String){
        viewModel.firstName.set(firstName)
        viewModel.lastName.set(lastName)
        viewModel.createUserWithEmailAndPassword()
    }

    private fun moveToHomeActivity(password: String){
        viewModel.password.set(password)
        val result = viewModel.signInWithEmailAndPassword()
        if(result==0){
//            startActivity(Intent(this,HomeActivity::class.java))
//            finish()
        }else if(result==1){
            showSnackBar(getString(R.string.invalid),getString(R.string.invalid_email_and_password),"")
            addFragmentToActivity(mBinding.flAuth.id,LoginFragment(),"Login")
        }
    }

    private fun moveToHomeActivity(){
//            startActivity(Intent(this,HomeActivity::class.java))
//            finish()
    }

    private fun gsi(){
        val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
        val gsc= GoogleSignIn.getClient(this,gso)
        val i=gsc.signInIntent
        startActivityForResult(i,googleSignIn)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == googleSignIn && resultCode== Activity.RESULT_OK && data!=null){
            val task= GoogleSignIn.getSignedInAccountFromIntent(data)
            viewModel.handleGSinResult(task)
        }
    }
}