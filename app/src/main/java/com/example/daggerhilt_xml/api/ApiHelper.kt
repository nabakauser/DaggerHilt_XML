package com.example.daggerhilt_xml.api

import com.example.daggerhilt_xml.model.User
import retrofit2.Response

interface ApiHelper {

    suspend fun getUsers(): Response<List<User>>
}