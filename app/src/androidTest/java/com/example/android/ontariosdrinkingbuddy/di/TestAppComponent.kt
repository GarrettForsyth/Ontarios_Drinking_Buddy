package com.example.android.ontariosdrinkingbuddy.di

import android.app.Application
import com.example.android.ontariosdrinkingbuddy.di.data.TestDataBaseModule
import com.example.android.ontariosdrinkingbuddy.di.network.TestBaseUrlModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainActivityModule::class,
        TestDataBaseModule::class,
        TestBaseUrlModule::class,
        ViewModelFactoryModule::class,
        SubcomponentsModule::class
    ]
)
interface TestAppComponent :
    AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): TestAppComponent
    }

    fun getMockWebServer(): MockWebServer
}

