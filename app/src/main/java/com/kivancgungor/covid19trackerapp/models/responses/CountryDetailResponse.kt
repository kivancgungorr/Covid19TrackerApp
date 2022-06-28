package com.kivancgungor.covid19trackerapp.models

data class CountryDetailResponse(
    val errors: List<String>,
    val results: Int,
    val response: List<CountryDetails>
)

data class CountryDetails(
    val continent: String,
    val country: String,
    val population: String,
    val cases: CovidCases
)

data class CovidCases(
    val new: String,
    val active: String,
    val critical: String,
    val recovered:String
)