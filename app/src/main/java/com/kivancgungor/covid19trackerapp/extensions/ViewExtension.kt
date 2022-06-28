package com.kivancgungor.covid19trackerapp.extensions

import android.view.View
import android.widget.Toast
import com.kivancgungor.covid19trackerapp.App

fun Any.showToastMsg(message: String) {
    Toast.makeText(App.getAppContext(), message, Toast.LENGTH_SHORT).show()
}

/**
 * An Extension to make view Gone
 * @return void
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * An Extension to make view Visible
 * @return void
 */
fun View.visible() {
    visibility = View.VISIBLE
}