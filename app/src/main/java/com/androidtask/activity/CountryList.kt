package com.androidtask.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidtask.R
import com.androidtask.adapter.CountryListAdapter
import com.androidtask.api.ApiHelper
import com.androidtask.api.RetrofitBuilder
import com.androidtask.data.model.CountryModel
import com.androidtask.data.model.CountryModelItem
import com.androidtask.ui.ViewModelFactory
import com.androidtask.utils.Status
import com.androidtask.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_country_list.*
import java.util.Collections.addAll

class CountryList : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: CountryListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_list)

        setUpViewModel()
        setupObservers()
//        setupUI()
    }


    fun setUpViewModel() {

        viewModel =
            ViewModelProviders.of(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService,"all")))
                .get(MainViewModel::class.java)

    }

//    private fun setupUI() {
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        adapter = CountryListAdapter(arrayListOf())
//        recyclerView.addItemDecoration(
//            DividerItemDecoration(
//                recyclerView.context,
//                (recyclerView.layoutManager as LinearLayoutManager).orientation
//            )
//        )
//        recyclerView.adapter = adapter
//    }

    fun setupObservers() {
        viewModel.getCountries().observe(this, Observer {

            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        adapter= resource.data?.let { it1 -> CountryListAdapter(it1) }!!
                        recyclerView.adapter=adapter
//                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        it.message?.let { it1 -> Log.e("error", it1) }
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })

    }

//    fun retrieveList(countries: List<CountryModelItem>) {
//        adapter.apply {
//            addAll(countries)
//            notifyDataSetChanged()
//        }
//
//
//    }


}