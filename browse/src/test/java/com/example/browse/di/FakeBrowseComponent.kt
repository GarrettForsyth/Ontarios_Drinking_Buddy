package com.example.browse.di

import com.example.browse.ui.BrowseFragment

class FakeBrowseComponent : BrowseComponent {
    override fun inject(browseFragment: BrowseFragment) {
        // No op -- no injection for unit tests.
    }
}