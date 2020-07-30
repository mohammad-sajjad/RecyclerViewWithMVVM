package com.androidtask.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.androidtask.data.repository.MainRepository
import com.androidtask.utils.Resource
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {

    fun getCountries()=liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {

            emit(Resource.success(data = mainRepository.getCountryList()))
        }
        catch (e:Exception){
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }
    fun getRegions()=liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {

            emit(Resource.success(data = mainRepository.getRegionList()))
        }
        catch (e:Exception){
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }
}