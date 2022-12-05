package com.example.walmartassetment.repository

import com.example.walmartassetment.api.model.Country


interface IRepository {
    suspend fun countryList(): ArrayList<Country>
}