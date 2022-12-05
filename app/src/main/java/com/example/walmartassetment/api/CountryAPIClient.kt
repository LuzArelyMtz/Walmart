package com.example.walmartassetment.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CountryAPIClient {
    private var URLBASE = "https://gist.githubusercontent.com/"

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URLBASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}