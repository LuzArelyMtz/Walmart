package com.example.walmartassetment.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.walmartassetment.api.model.Country
import com.example.walmartassetment.databinding.CountryItemCardvBinding

class CountryViewHolder(private val definitionItemCardvBinding: CountryItemCardvBinding) :
    RecyclerView.ViewHolder(definitionItemCardvBinding.root) {

    fun bindView(country: Country) {
        definitionItemCardvBinding.countryCardvBinding = country
    }
}