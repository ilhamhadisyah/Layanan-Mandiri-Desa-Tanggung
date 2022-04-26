package com.pemdestanggung.layananmandiridesatanggung.data

import com.pemdestanggung.layananmandiridesatanggung.data.datasource.network.ApiHelper

class DataRepository(private val apiHelper: ApiHelper) {
    suspend fun getArticles() = apiHelper.getArticles()
}