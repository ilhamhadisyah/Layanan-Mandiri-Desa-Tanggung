package com.pemdestanggung.layananmandiridesatanggung.data.datasource.network

import com.pemdestanggung.layananmandiridesatanggung.data.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/artikel")
    suspend fun getArticles(): Response
}