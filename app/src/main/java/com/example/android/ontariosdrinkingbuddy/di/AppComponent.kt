package com.example.android.ontariosdrinkingbuddy.di

import android.app.Application
import com.example.android.ontariosdrinkingbuddy.ODBApp
import com.example.android.ontariosdrinkingbuddy.di.data.DataModule
import com.example.android.ontariosdrinkingbuddy.di.data.DatabaseModule
import com.example.android.ontariosdrinkingbuddy.di.network.BaseUrlModule
import com.example.browse.di.BrowseComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainActivityModule::class,
        DatabaseModule::class,
        BaseUrlModule::class,
        ViewModelFactoryModule::class,
        SubcomponentsModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: ODBApp)
    fun browseComponent(): BrowseComponent.Factory
}
