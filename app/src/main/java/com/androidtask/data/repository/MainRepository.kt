package com.androidtask.data.repository

import com.androidtask.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getCountryList() = apiHelper.getCountry()
    suspend fun getRegionList() = apiHelper.getRegion()
}