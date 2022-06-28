package com.kivancgungor.covid19trackerapp.models.responses

data class CountryResponse(
    val errors: List<String>,
    val results: Int,
    val response: List<String>
)