package com.example.browse.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.browse.R
import com.example.core.databinding.LcboItemListItemBinding
import com.example.core.ui.DataBoundListAdapter
import com.example.core.vo.LCBOItem
import com.example.core.vo.LCBOItemDiffCallback

/**
 * A RecyclerView adapter for [LCBOItem] class.
 */
class LCBOItemAdapter(
) : DataBoundListAdapter<LCBOItem, LcboItemListItemBinding>(
    diffCallback = LCBOItemDiffCallback.callback
) {

    override fun createBinding(parent: ViewGroup): LcboItemListItemBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.lcbo_item_list_item,
            parent,
            false
        )
    }

    override fun bind(binding: LcboItemListItemBinding, item: LCBOItem) {
        binding.lcboItem = item
    }
}
