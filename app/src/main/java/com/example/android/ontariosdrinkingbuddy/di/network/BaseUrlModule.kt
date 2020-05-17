package com.example.android.ontariosdrinkingbuddy.di.network

import com.example.android.ontariosdrinkingbuddy.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class BaseUrlModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return BuildConfig.baseUrl
    }

}