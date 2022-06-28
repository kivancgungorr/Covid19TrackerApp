package com.kivancgungor.covid19trackerapp.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kivancgungor.covid19trackerapp.views.activities.MainActivity

abstract class BaseFragment : Fragment() {

    protected lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
    }
}