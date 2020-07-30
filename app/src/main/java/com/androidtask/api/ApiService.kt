package com.androidtask.api

import com.androidtask.data.model.CountryModel
import com.androidtask.data.model.CountryModelItem
import com.androidtask.data.model.regionata.RegionModelItem
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {


    @GET()
    suspend fun getCountry(@Url string: String ): List<CountryModelItem>
    @GET()
    suspend fun getRegion(@Url string: String): List<RegionModelItem>

}
