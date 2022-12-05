package com.example.walmartassetment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.walmartassetment.api.model.Country
import com.example.walmartassetment.databinding.CountryItemCardvBinding

class CountryAdapter : ListAdapter<Country, CountryViewHolder>(CountryDiffCallback()) {
    var term: String = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val definitionItemCardvBinding =
            CountryItemCardvBinding.inflate(layoutInflater, parent, false)

        return CountryViewHolder(definitionItemCardvBinding)
    }

    override fun onBindViewHolder(viewHolder: CountryViewHolder, position: Int) {
        viewHolder.bindView(getItem(position))
    }

}