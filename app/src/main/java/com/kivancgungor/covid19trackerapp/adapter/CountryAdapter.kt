package com.kivancgungor.covid19trackerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kivancgungor.covid19trackerapp.R

class CountryAdapter(private  val onCountryItemClickListener: OnCountryItemClickListener) : RecyclerView.Adapter<CountryAdapter.MyViewHolder>() {

    private val countryList: ArrayList<String> = ArrayList()

    fun setItems(list: List<String>) {
        countryList.clear()
        countryList.addAll(list)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var country = view.findViewById(R.id.layout_country) as LinearLayout
        var tvCountry = view.findViewById(R.id.tv_country) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.country_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val countryName = countryList[position]
        holder.tvCountry.text = countryName.toString()
        holder.country.setOnClickListener {
            onCountryItemClickListener.clickListener(countryName)
        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    interface OnCountryItemClickListener {
        fun clickListener(country: String)
    }
}