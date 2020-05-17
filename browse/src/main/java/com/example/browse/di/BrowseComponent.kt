package com.example.browse.di

import com.example.browse.ui.BrowseFragment
import com.example.core.di.scopes.FeatureScope
import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [BrowseModule::class])
interface BrowseComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): BrowseComponent
    }

    fun inject(browseFragment: BrowseFragment)
}