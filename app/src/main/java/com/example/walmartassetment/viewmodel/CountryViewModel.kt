package com.example.walmartassetment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmartassetment.api.model.Country
import com.example.walmartassetment.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryViewModel (private val repository : IRepository) : ViewModel() {
    private var _livedataCountries = MutableLiveData<List<Country>>()
    var livedataCountries: LiveData<List<Country>> = _livedataCountries

    private var _searchProgress = MutableLiveData<Boolean>()
    val searchProgress: LiveData<Boolean> = _searchProgress

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getCountryList() {

        viewModelScope.launch(Dispatchers.IO) {
            _searchProgress.postValue(true)

            try {
                val response = repository.countryList()
                if (response != null) {
                    _livedataCountries.postValue(response)
                } else {
                    _error.postValue("Not results found")
                }
                _searchProgress.postValue(false)

            } catch (e: Exception) {
                e.printStackTrace()
                _error.postValue("Network Error")
            }
            _searchProgress.postValue(false)
        }
    }
}