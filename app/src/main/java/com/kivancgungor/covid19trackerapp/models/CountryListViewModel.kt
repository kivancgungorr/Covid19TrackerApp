package com.kivancgungor.covid19trackerapp.models

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.kivancgungor.covid19trackerapp.base.BaseViewModel
import com.kivancgungor.covid19trackerapp.models.responses.CountryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CountryListViewModel : BaseViewModel<CountryListViewModel.View>(){

    val countryList = MutableLiveData<List<String>>()

    //  start search List
    private val _query = MutableLiveData<String>()
    private val filteredData = Transformations.switchMap(_query) { filterable ->
        Transformations.map(countryList) { list ->
            if (filterable.isNotBlank()) {
                list.filter {
                    it.lowercase(Locale.getDefault()).contains(filterable)
                }
            } else
                list
        }
    }

    val countryListData = MediatorLiveData<List<String>>().apply {
        addSource(countryList) { value -> this.setValue(value) }
        addSource(filteredData) { value -> this.setValue(value) }
    }

    fun onSearchContact(query: String) {
        if (query.length >= QUERY_THRESHOLD || query.isEmpty()) {
            _query.value = query
        }
    }
    //    end search List

    fun getCountryItemList() {
        getView().showProgressBar()
        itemRepository.getCountryList()
            .enqueue(object : Callback<CountryResponse> {
                override fun onResponse(
                    call: Call<CountryResponse>,
                    response: Response<CountryResponse>
                ) {
                    getView().dismissProgressBar()
                    response.run {
                        if (isSuccessful) {
                            body()?.response?.let {
                                countryList.value = it
                            } ?: getView().onUpdateResponse("Something went wrong")
                        }
                    }
                }

                override fun onFailure(call: Call<CountryResponse>, t: Throwable) {
                    getView().dismissProgressBar()
                    getView().onUpdateResponse(t.message.toString())
                }
            })
    }

    companion object {
        const val QUERY_THRESHOLD = 2
    }

    interface View {
        fun onUpdateResponse(message: String)
        fun showProgressBar()
        fun dismissProgressBar()
    }
}