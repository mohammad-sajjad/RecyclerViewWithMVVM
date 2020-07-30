package com.androidtask.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidtask.R
import com.androidtask.activity.RegionList
import com.androidtask.data.model.CountryModelItem
import com.androidtask.data.model.regionata.RegionModelItem
import kotlinx.android.synthetic.main.country_list_adapter.view.*

class RegionListAdapter (private val regions:List<RegionModelItem>):RecyclerView.Adapter<RegionListAdapter.DataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.country_list_adapter, parent, false)
        )

    override fun getItemCount(): Int {
        return regions.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(regions.get(position))
    }


    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(regionItem: RegionModelItem) {
            itemView.apply {

                countryName.text = "Country :" + regionItem.name
                countryLatlag.text = "Latlng : " + regionItem.latlng.toString()
            }
        }
    }
    }
