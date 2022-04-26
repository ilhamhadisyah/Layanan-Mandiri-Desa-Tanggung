package com.pemdestanggung.layananmandiridesatanggung.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pemdestanggung.layananmandiridesatanggung.data.DataRepository
import com.pemdestanggung.layananmandiridesatanggung.data.datasource.network.ApiHelper
import com.pemdestanggung.layananmandiridesatanggung.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(DataRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unkown Class")
    }
}