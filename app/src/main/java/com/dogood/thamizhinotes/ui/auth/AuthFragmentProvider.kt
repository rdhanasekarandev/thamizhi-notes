package com.dogood.thamizhinotes.ui.auth

import com.dogood.thamizhinotes.ui.auth.cookie.AgeFragment
import com.dogood.thamizhinotes.ui.auth.cookie.NameFragment
import com.dogood.thamizhinotes.ui.auth.create.CreateFragment
import com.dogood.thamizhinotes.ui.auth.login.LoginFragment
import com.dogood.thamizhinotes.ui.auth.otp.CreateOtpFragment
import com.dogood.thamizhinotes.ui.auth.otp.LoginOtpFragment
import com.dogood.thamizhinotes.ui.auth.password.CreatePasswordFragment
import com.dogood.thamizhinotes.ui.auth.password.LoginPasswordFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class AuthFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideLoginFragmentFactory(): LoginFragment

    @ContributesAndroidInjector
    abstract fun provideLoginPasswordFragmentFactory(): LoginPasswordFragment

    @ContributesAndroidInjector
    abstract fun provideLoginOtpFragmentFactory(): LoginOtpFragment

    @ContributesAndroidInjector
    abstract fun provideCreateFragmentFactory(): CreateFragment

    @ContributesAndroidInjector
    abstract fun provideCreateOtpFactory(): CreateOtpFragment

    @ContributesAndroidInjector
    abstract fun provideCreatePasswordFragmentFactory(): CreatePasswordFragment

    @ContributesAndroidInjector
    abstract fun provideNameFragmentFactory(): NameFragment

    @ContributesAndroidInjector
    abstract fun provideAgeFragmentFactory(): AgeFragment
}