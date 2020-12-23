package com.dogood.thamizhinotes.dagger.module

import android.app.Application
import android.content.Context
import com.dogood.thamizhinotes.dagger.ViewModelModule
import com.dogood.thamizhinotes.utils.resource.BaseResourceProvider
import com.dogood.thamizhinotes.utils.resource.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideResource(resourceProvider: ResourceProvider): BaseResourceProvider {
        return resourceProvider
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

}