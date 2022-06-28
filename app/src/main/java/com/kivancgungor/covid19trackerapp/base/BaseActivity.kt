package com.kivancgungor.covid19trackerapp.base

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import com.kivancgungor.covid19trackerapp.utils.InternetMonitor
import java.util.*

abstract class BaseActivity : AppCompatActivity() {

    //internet monitor, to monitor the internet state to to update the app
    private val internetMonitor = InternetMonitor(object :
        InternetMonitor.OnInternetConnectivityListener {
        override fun onInternetAvailable() {
            //getting the data in Background thread and showing it to the view on UI thread
            runOnUiThread {
//                top_connection_shower?.gone()
            }
        }

        override fun onInternetLost() {
            //onInternetLost, getting the data in Background thread and showing it to the view on UI thread
            runOnUiThread {
//                top_connection_shower?.visible()
            }
        }
    })

    //registering the internet monitor on resume
    override fun onResume() {
        super.onResume()
        internetMonitor.register(this)
    }

    //unregistering the internet monitor on pause
    override fun onPause() {
        super.onPause()
        internetMonitor.unregister(this)
    }

    fun setLocate(Lang: String){
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale

        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    fun loadLocate(){
        var preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        var localizedLanguage = preferences.getString("My_Lang", "")
        setLocate(localizedLanguage!!)
    }
}