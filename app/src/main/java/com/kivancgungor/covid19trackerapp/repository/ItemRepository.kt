package com.kivancgungor.covid19trackerapp.repository

import com.kivancgungor.covid19trackerapp.base.BaseRepository
import com.kivancgungor.covid19trackerapp.network.RetrofitClient
import com.kivancgungor.covid19trackerapp.utils.Constants.BASE_URL

class ItemRepository : BaseRepository() {

    ////API End pints
    fun getCountryList() =
        RetrofitClient.getInterfaceService(
            BASE_URL
        ).getAllCountries()

    fun getCountryDetail() = RetrofitClient.getInterfaceService(
        BASE_URL
    ).getCountryDetail()

    companion object {
        private var instance: ItemRepository? = null
        fun getInstance(): ItemRepository {
            if (instance == null)
                instance =
                    ItemRepository()
            return instance!!
        }
    }
}