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
        // Don't access MockWebServer from the main thread
        lateinit var baseUrl: String
        val thread = Thread(Runnable {
            baseUrl = mockWebServer.url("/").toString()
        })
        thread.start()
        thread.join()
        return baseUrl
    }

    @Provides
    @Singleton
    fun provideMockWebServer() = MockWebServer()
}
