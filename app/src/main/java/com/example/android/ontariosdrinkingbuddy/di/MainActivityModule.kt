package com.example.android.ontariosdrinkingbuddy.di

import com.example.android.ontariosdrinkingbuddy.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}