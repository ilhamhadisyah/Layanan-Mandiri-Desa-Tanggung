package com.pemdestanggung.layananmandiridesatanggung.data.datasource.network

class ApiHelper(private val apiService: ApiService) {
    suspend fun getArticles() = apiService.getArticles()
}