package com.example.walmartassetment.api

import com.example.walmartassetment.api.model.CountryList
import retrofit2.http.GET
import retrofit2.http.Query

interface ICountryAPI {
    @GET("peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getResponse(): CountryList
}