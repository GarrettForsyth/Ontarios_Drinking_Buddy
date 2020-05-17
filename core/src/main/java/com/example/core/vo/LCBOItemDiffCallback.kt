package com.example.core.vo

import androidx.recyclerview.widget.DiffUtil

object LCBOItemDiffCallback {
    val callback = object : DiffUtil.ItemCallback<LCBOItem>() {
        override fun areItemsTheSame(oldItem: LCBOItem, newItem: LCBOItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LCBOItem, newItem: LCBOItem): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
