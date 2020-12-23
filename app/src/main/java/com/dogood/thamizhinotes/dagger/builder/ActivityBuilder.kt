package com.dogood.thamizhinotes.dagger.builder

import com.dogood.thamizhinotes.ui.auth.AuthActivity
import com.dogood.thamizhinotes.ui.auth.AuthFragmentProvider
import com.dogood.thamizhinotes.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module(includes = [AuthFragmentProvider::class])
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity() : SplashActivity

    @ContributesAndroidInjector
    abstract fun bindAuthActivity() : AuthActivity
}