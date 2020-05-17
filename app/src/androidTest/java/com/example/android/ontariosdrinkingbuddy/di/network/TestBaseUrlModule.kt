package com.example.android.ontariosdrinkingbuddy.di.network

import androidx.test.core.app.ApplicationProvider
import com.example.android.ontariosdrinkingbuddy.ODBTestApp
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class TestBaseUrlModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(mockWebServer: MockWebServer): String {
        return mockWebServer.url("/").toString()
//        val app = ApplicationProvider.getApplicationContext<ODBTestApp>()
//        return app.baseUrl
    }

    @Provides
    @Singleton
    fun provideMockWebServer() = MockWebServer()
}
