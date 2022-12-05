package com.example.walmartassetment.repository

import com.example.walmartassetment.api.CountryAPIClient
import com.example.walmartassetment.api.ICountryAPI
import com.example.walmartassetment.api.model.Country
import com.example.walmartassetment.api.model.CountryList

class RepositoryImpl (private val api: ICountryAPI) : IRepository {
    override suspend fun countryList(): ArrayList<Country> {
        return api.getResponse()

        //provideRetrofit().create(CountryList::class.java)
    }
}