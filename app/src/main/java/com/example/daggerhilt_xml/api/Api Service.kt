package com.example.daggerhilt_xml.api

import com.example.daggerhilt_xml.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

}