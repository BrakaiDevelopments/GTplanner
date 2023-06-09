package com.gtplanner.ui.standard.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gtplanner.ui.mvi.MVIAdapter
import com.gtplanner.viewmodel.dto.StandardStateListItem

abstract class StandardAdapter<I : StandardStateListItem> : MVIAdapter<StandardHolder<I>>() {

    open var items = listOf<I>()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(StandardDiffCallback(field, value))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onBindViewHolder(holder: StandardHolder<I>, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size
}