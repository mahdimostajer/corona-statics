package com.example.corona.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.corona.R
import com.example.corona.network.Country
import com.example.corona.utils.formatNumber

class CountryAdapter : ListAdapter<Country, CountryAdapter.ViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_card_country, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = getItem(position)
        holder.bind(country, position + 1)
    }


    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val countryName: TextView = view.findViewById(R.id.country_title)
        private val flag: ImageView = view.findViewById(R.id.country_flag)
        private val cases: TextView = view.findViewById(R.id.cases)
        private val todayCases: TextView = view.findViewById(R.id.today_cases)
        private val deaths: TextView = view.findViewById(R.id.deaths)
        private val todayDeaths: TextView = view.findViewById(R.id.today_deaths)
        private val countryPosition: TextView = view.findViewById(R.id.country_position)

        fun bind(country: Country, position: Int) {
            countryName.text = country.country
            val imgUri = country.countryInfo.flag.toUri().buildUpon().scheme("https").build()
            flag.load(imgUri) {
                placeholder(R.drawable.ic_blur)
                error(R.drawable.ic_error)
            }
            cases.text = itemView.context.getString(R.string.cases, formatNumber(country.cases))
            todayCases.text = itemView.context.getString(
                R.string.today_cases,
                formatNumber(country.todayCases)
            )
            deaths.text = itemView.context.getString(
                R.string.deaths,
                formatNumber(country.deaths)
            )
            todayDeaths.text =
                itemView.context.getString(R.string.today_deaths, formatNumber(country.todayDeaths))
            countryPosition.text = position.toString()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.country == newItem.country
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.countryInfo.flag == newItem.countryInfo.flag
        }

    }
}