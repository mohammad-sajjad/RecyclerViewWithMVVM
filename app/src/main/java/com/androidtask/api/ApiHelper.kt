package com.androidtask.api

class ApiHelper(private val apiService: ApiService,private  val string: String) {

    suspend fun getCountry() = apiService.getCountry(string)
    suspend fun getRegion() = apiService.getRegion("region/"+string)
}