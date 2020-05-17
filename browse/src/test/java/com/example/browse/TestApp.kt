package com.example.browse

import android.app.Application
import com.example.browse.di.BrowseComponentProvider
import com.example.browse.di.FakeBrowseComponent

class TestApp : Application(), BrowseComponentProvider {
    override fun provideBrowseComponent() = FakeBrowseComponent()
}