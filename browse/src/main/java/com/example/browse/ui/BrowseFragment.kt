package com.example.browse.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.browse.R
import com.example.browse.databinding.FragmentBrowseBinding
import com.example.browse.di.BrowseComponent
import com.example.browse.di.BrowseComponentProvider
import com.example.browse.ui.adapters.LCBOItemAdapter
import com.example.core.annotations.OpenForTesting
import com.example.core.vo.Status
import javax.inject.Inject

@OpenForTesting
class BrowseFragment : Fragment() {

    lateinit var browseComponent: BrowseComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewmodel: BrowseViewModel

    lateinit var binding: FragmentBrowseBinding

    private lateinit var lcboItemAdapter: LCBOItemAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        browseComponent = (activity?.applicationContext as BrowseComponentProvider)
            .provideBrowseComponent()
        browseComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewmodel = ViewModelProvider(this, viewModelFactory)
            .get(BrowseViewModel::class.java)

        // Inflate the layout for this fragment
        binding = inflate(
            inflater,
            R.layout.fragment_browse,
            container,
            false
        )
        binding.browseViewModel = viewmodel
        lcboItemAdapter = LCBOItemAdapter()
        binding.browseSearchResultsList.adapter = lcboItemAdapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeSearchResults()
    }

    private fun observeSearchResults() {
        viewmodel.searchResults.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    lcboItemAdapter.submitList(it.data)
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                }
            }
        })
    }
}