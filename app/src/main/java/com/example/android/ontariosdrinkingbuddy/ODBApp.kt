package com.example.android.ontariosdrinkingbuddy

import android.app.Application
import com.example.android.ontariosdrinkingbuddy.di.DaggerAppComponent
import com.example.browse.di.BrowseComponent
import com.example.browse.di.BrowseComponentProvider
import com.example.core.annotations.OpenForTesting
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

@OpenForTesting
class ODBApp : Application(), HasAndroidInjector, BrowseComponentProvider {

    /**
     * This class is marked [OpenForTesting] allowing it to be extended
     * in debug builds for test purposes. Here, [ODBApp] is injected
     * into [DaggerAppComponent] potentially leaking its context. This
     * should not be a problem since [ODBApp] is the lifespan of the
     * app and therefore will not cause memory leaks.
     */
    @Suppress("LeakingThis")
    val appComponent = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        appComponent.inject(this)
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun provideBrowseComponent(): BrowseComponent {
        return appComponent.browseComponent().create()
    }
}