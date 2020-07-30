package com.androidtask.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.androidtask.R
import com.androidtask.adapter.CountryListAdapter
import com.androidtask.adapter.RegionListAdapter
import com.androidtask.api.ApiHelper
import com.androidtask.api.ApiService
import com.androidtask.api.RetrofitBuilder
import com.androidtask.ui.ViewModelFactory
import com.androidtask.utils.Status
import com.androidtask.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_country_list.*
import kotlinx.android.synthetic.main.activity_region_list.*

class RegionList : AppCompatActivity() {
    private  lateinit var viewModel: MainViewModel
    private  lateinit var adapter: RegionListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_region_list)



        setUpViewModel()
        setupObservers()
    }

    private fun setUpViewModel() {
        viewModel =
            ViewModelProviders.of(this, ViewModelFactory(intent.getStringExtra("region")?.let {
                ApiHelper(RetrofitBuilder.apiService,
                    it
                )
            }!!))
                .get(MainViewModel::class.java)    }

    private fun setupObservers(){
        viewModel.getRegions().observe(this, Observer {

            it?.let { resource ->

                when (resource.status) {
                    Status.SUCCESS -> {
                        regionRecycler.visibility = View.VISIBLE
                        pb.visibility = View.GONE
                        adapter = resource.data?.let { it1 -> RegionListAdapter(it1) }!!
                        regionRecycler.adapter = adapter
//                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        regionRecycler.visibility = View.VISIBLE
                        pb.visibility = View.GONE
                        it.message?.let { it1 -> Log.e("error", it1) }
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        pb.visibility = View.VISIBLE
                        regionRecycler.visibility = View.GONE
                    }
                }
            }
        })

    }
}
