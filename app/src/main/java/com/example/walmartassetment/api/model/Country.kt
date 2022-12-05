package com.example.walmartassetment.api.model

data class Country(
    val capital: String,
    val code: String,
    val currency: Currency,
    val demonym: String,
    val flag: String,
    val language: Language,
    val name: String,
    val region: String
)