package com.kivancgungor.covid19trackerapp.network

import com.kivancgungor.covid19trackerapp.models.CountryDetailResponse
import com.kivancgungor.covid19trackerapp.models.responses.CountryResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("countries")
    fun getAllCountries(): Call<CountryResponse>

    @GET("statistics")
    fun getCountryDetail(): Call<CountryDetailResponse>



}