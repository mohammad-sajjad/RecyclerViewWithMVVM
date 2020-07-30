package com.androidtask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidtask.api.ApiHelper
import com.androidtask.data.repository.MainRepository
import com.androidtask.viewmodels.MainViewModel
import kotlin.concurrent.thread

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)){

            return MainViewModel(MainRepository(apiHelper)) as T
        }
    throw  IllegalArgumentException("Unknown class exception")
    }
}