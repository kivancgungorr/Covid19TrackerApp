package com.kivancgungor.covid19trackerapp.views.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.kivancgungor.covid19trackerapp.adapter.CountryAdapter
import com.kivancgungor.covid19trackerapp.base.BaseFragment
import com.kivancgungor.covid19trackerapp.databinding.FragmentHomeBinding
import com.kivancgungor.covid19trackerapp.extensions.gone
import com.kivancgungor.covid19trackerapp.extensions.replaceFragment
import com.kivancgungor.covid19trackerapp.extensions.showToastMsg
import com.kivancgungor.covid19trackerapp.extensions.visible
import com.kivancgungor.covid19trackerapp.models.CountryListViewModel
import com.kivancgungor.covid19trackerapp.utils.Constants.COUNTRY_NAME

class CountryListFragment : BaseFragment(), CountryAdapter.OnCountryItemClickListener,
    CountryListViewModel.View{

    private lateinit var binding: FragmentHomeBinding
    private var countryAdapter: CountryAdapter? = null
    private val countryListViewModel: CountryListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countryAdapter = CountryAdapter(this)

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                countryListViewModel.onSearchContact(s.toString())
            }
        })

        countryListViewModel.let {
            it.attachView(this)
            it.getCountryItemList()
        }

        onObserveCountryList()

        countryListViewModel.getCountryItemList()
        countryAdapter.let {
            binding.rvCountries.apply {
                itemAnimator = DefaultItemAnimator()
                adapter = it
            }
        }

        binding.rvCountries.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    //Scrolling down
                    binding.searchCard.visible()
                } else if (dy < 0) {
                    //Scrolling up
                    binding.searchCard.gone()
                }
            }
        })
    }

    //once we get the data from repo, populate it with the help of the adapter, NewsAdapter()
    private fun onObserveCountryList() {
        countryListViewModel.countryList.observe(viewLifecycleOwner) {
            it?.let {
                countryAdapter?.setItems(it)
            }
        }

        countryListViewModel.countryListData.observe(requireActivity()){
            it.let {
                countryAdapter?.setItems(it)
            }
        }
    }

    override fun clickListener(country: String) {
        binding.etSearch.text.clear()
        val bundle = Bundle()
        bundle.putString(COUNTRY_NAME, country)
        replaceFragment(CountryDetailFragment(), bundle = bundle)
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