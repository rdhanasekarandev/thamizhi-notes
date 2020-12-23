package com.dogood.thamizhinotes.dagger.component

import android.app.Application
import com.dogood.thamizhinotes.App
import com.dogood.thamizhinotes.dagger.builder.ActivityBuilder
import com.dogood.thamizhinotes.dagger.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,ActivityBuilder::class,AppModule::class])
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application):Builder

        fun build(): AppComponent
    }

}