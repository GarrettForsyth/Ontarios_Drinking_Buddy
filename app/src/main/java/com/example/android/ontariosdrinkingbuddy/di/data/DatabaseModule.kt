package com.example.android.ontariosdrinkingbuddy.di.data

import android.app.Application
import androidx.room.Room
import com.example.android.ontariosdrinkingbuddy.BuildConfig
import com.example.core.data.ODBDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataModule::class])
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDb(
        app: Application
    ): ODBDatabase {
        return Room.databaseBuilder(
            app,
            ODBDatabase::class.java,
            BuildConfig.databaseName
        )
            .fallbackToDestructiveMigration() // TODO: pick a nondestructive migration
            .build()
    }

}
