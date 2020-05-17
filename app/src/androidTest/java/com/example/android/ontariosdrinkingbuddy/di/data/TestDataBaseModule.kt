package com.example.android.ontariosdrinkingbuddy.di.data

import android.app.Application
import androidx.room.Room
import com.example.core.data.ODBDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataModule::class])
class TestDataBaseModule {

    @Singleton
    @Provides
    fun provideDb(
        app: Application
    ): ODBDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            ODBDatabase::class.java
        ).allowMainThreadQueries().build()
    }

}