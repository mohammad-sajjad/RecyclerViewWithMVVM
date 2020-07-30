package com.androidtask.adapter

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidtask.R
import com.androidtask.activity.CountryList
import com.androidtask.activity.RegionList
import com.androidtask.data.model.CountryModel
import com.androidtask.data.model.CountryModelItem
import kotlinx.android.synthetic.main.country_list_adapter.view.*

class CountryListAdapter (private val countries:List<CountryModelItem>):RecyclerView.Adapter<CountryListAdapter.DataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.country_list_adapter, parent, false)
        )

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(countries.get(position))
    }


    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(countryitem: CountryModelItem) {
            itemView.apply {

                countryName.text ="Country :"+ countryitem.name
                countryLatlag.text = "Latlng : "+countryitem.latlng.toString()
                linearLayout.setOnClickListener {
                    val intent:Intent= Intent(context,RegionList::class.java)
                    intent.putExtra("region",countryitem.region)
                    context.startActivity(intent)
                }
            }
        }


    }
//    fun addAll(countryitem: List<CountryModelItem>) {
//        this.countries.apply {
//            clear()
//            addAll(countryitem)
//        }
//    }
}