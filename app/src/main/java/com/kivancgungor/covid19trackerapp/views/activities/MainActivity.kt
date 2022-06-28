package com.kivancgungor.covid19trackerapp.views.activities

import android.os.Bundle
import com.kivancgungor.covid19trackerapp.base.BaseActivity
import com.kivancgungor.covid19trackerapp.databinding.ActivityMainBinding
import com.kivancgungor.covid19trackerapp.extensions.replaceFragmentSafely
import com.kivancgungor.covid19trackerapp.views.fragments.CountryListFragment

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        loadLocate()
        replaceFragmentSafely(CountryListFragment())

    }
}