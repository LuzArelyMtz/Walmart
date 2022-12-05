package com.example.walmartassetment.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.walmartassetment.api.model.Country

class CountryDiffCallback : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(oldItem: Country, newItem: Country) = oldItem.code == newItem.code

    override fun areContentsTheSame(oldItem: Country, newItem: Country) = oldItem == newItem
}