package com.example.android.ontariosdrinkingbuddy.di

import androidx.lifecycle.ViewModelProvider
import com.example.core.ui.ODBViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule{
    @Binds
    abstract fun bindViewModelFactory(factory: ODBViewModelFactory): ViewModelProvider.Factory
}