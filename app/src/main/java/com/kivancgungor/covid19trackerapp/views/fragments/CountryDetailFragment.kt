package com.kivancgungor.covid19trackerapp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kivancgungor.covid19trackerapp.base.BaseFragment
import com.kivancgungor.covid19trackerapp.databinding.FragmentCountryDetailBinding
import com.kivancgungor.covid19trackerapp.extensions.showToastMsg
import com.kivancgungor.covid19trackerapp.models.CountryDetailViewModel
import com.kivancgungor.covid19trackerapp.utils.Constants.COUNTRY_NAME

class CountryDetailFragment : BaseFragment(), CountryDetailViewModel.View {

    private lateinit var binding: FragmentCountryDetailBinding
    private val countryDetailViewModel: CountryDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCountryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        val country = bundle?.getString(COUNTRY_NAME)

        binding.tvCountry.text = country.toString()

        countryDetailViewModel.let {
            it.attachView(this)
            it.getCountryDetail(country.toString())
        }

        onObserveCountryDetail()

        countryDetailViewModel.getCountryDetail(country.toString())
    }

    //once we get the data from repo, populate it with the help of the adapter, NewsAdapter()
    private fun onObserveCountryDetail() {
        countryDetailViewModel.countryDetail.observe(viewLifecycleOwner) {
            binding.tvContinent.text = it.continent
            binding.tvPopulation.text = it.population
            binding.tvNewCases.text = it.cases.new
            binding.tvActiveCases.text = it.cases.active
            binding.tvCriticalCases.text = it.cases.critical
            binding.tvRecoveredCases.text = it.cases.recovered
        }
    }

    override fun onUpdateResponse(message: String) {
        showToastMsg(message)
    }

    override fun showProgressBar() {
        //progressDialog.show()
    }

    override fun dismissProgressBar() {
        //progressDialog.dismiss()
    }
}