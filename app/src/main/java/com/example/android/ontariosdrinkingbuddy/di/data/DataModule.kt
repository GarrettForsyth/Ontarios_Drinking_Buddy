package com.example.android.ontariosdrinkingbuddy.di.data

import com.example.core.data.ODBDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataModule {

    @Provides
    @Singleton
    fun provideLCBOItemDao(db: ODBDatabase) = db.lcboItemDao()

}