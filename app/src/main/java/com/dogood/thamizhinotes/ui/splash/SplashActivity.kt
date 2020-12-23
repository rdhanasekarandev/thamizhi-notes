package com.dogood.thamizhinotes.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.dogood.thamizhinotes.BR
import com.dogood.thamizhinotes.R
import com.dogood.thamizhinotes.databinding.ActivitySplashBinding
import com.dogood.thamizhinotes.ui.auth.AuthActivity
import com.dogood.thamizhinotes.ui.base.BaseActivity
import com.google.firebase.crashlytics.FirebaseCrashlytics
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(),SplashNavigator {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_splash
    override val viewModel: SplashViewModel
        get() = ViewModelProvider(this,viewModelFactory).get(SplashViewModel::class.java)
    private lateinit var mBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        mBinding = viewDataBinding!!
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        Handler().postDelayed({
            viewModel.defaultSettingsInCache()
        },2000)
    }

    override fun openLoginActivity() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

    override fun openHomeActivity() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}