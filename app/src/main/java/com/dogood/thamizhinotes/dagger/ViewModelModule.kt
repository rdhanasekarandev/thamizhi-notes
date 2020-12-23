package com.dogood.thamizhinotes.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dogood.thamizhinotes.AppViewModelFactory
import com.dogood.thamizhinotes.ui.auth.AuthViewModel
import com.dogood.thamizhinotes.ui.auth.cookie.AgeViewModel
import com.dogood.thamizhinotes.ui.auth.cookie.NameViewModel
import com.dogood.thamizhinotes.ui.auth.create.CreateViewModel
import com.dogood.thamizhinotes.ui.auth.login.LoginViewModel
import com.dogood.thamizhinotes.ui.auth.otp.CreateOtpViewModel
import com.dogood.thamizhinotes.ui.auth.otp.LoginOtpViewModel
import com.dogood.thamizhinotes.ui.auth.password.CreatePasswordViewModel
import com.dogood.thamizhinotes.ui.auth.password.LoginPasswordViewModel
import com.dogood.thamizhinotes.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginPasswordViewModel::class)
    abstract fun bindLoginPasswordViewModel(loginPasswordViewModel: LoginPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginOtpViewModel::class)
    abstract fun bindLoginOtpViewModel(loginOtpViewModel: LoginOtpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateViewModel::class)
    abstract fun bindCreateViewModel(createViewModel: CreateViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreatePasswordViewModel::class)
    abstract fun bindCreatePasswordViewModel(createPasswordViewModel: CreatePasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateOtpViewModel::class)
    abstract fun bindCreateOtpViewModel(createOtpViewModel: CreateOtpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AgeViewModel::class)
    abstract fun bindAgeViewModel(ageViewModel: AgeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NameViewModel::class)
    abstract fun bindNameViewModel(nameViewModel: NameViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}