package com.example.walmartassetment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.walmartassetment.repository.IRepository


class CountryViewModelFactory(
    private val repository: IRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountryViewModel(repository) as T
    }
}