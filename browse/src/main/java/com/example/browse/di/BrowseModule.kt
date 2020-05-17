package com.example.browse.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.browse.ui.BrowseFragment
import com.example.browse.ui.BrowseViewModel
import com.example.core.di.keys.FragmentKey
import com.example.core.di.keys.ViewModelKey
import com.example.core.di.scopes.FeatureScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class BrowseModule {
    @FeatureScope
    @Binds
    @IntoMap
    @FragmentKey(BrowseFragment::class)
    abstract fun bindBrowseFragment(browseFragment: BrowseFragment): Fragment

    @FeatureScope
    @Binds
    @IntoMap
    @ViewModelKey(BrowseViewModel::class)
    abstract fun bindBrowseViewModel(browseViewModel: BrowseViewModel): ViewModel
}