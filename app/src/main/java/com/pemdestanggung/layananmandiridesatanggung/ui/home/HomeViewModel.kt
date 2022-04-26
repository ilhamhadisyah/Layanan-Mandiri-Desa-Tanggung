package com.pemdestanggung.layananmandiridesatanggung.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.pemdestanggung.layananmandiridesatanggung.data.DataRepository
import com.pemdestanggung.layananmandiridesatanggung.utils.Resources
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class HomeViewModel(private val dataRepository: DataRepository) : ViewModel() {
    fun getArticles() = liveData(Dispatchers.IO) {
        emit(Resources.loading(data = null))
        try {
            emit(Resources.success(data = dataRepository.getArticles()))
        } catch (e: Exception) {
            emit(Resources.error(data = null, message = e.message))
        }
    }
}