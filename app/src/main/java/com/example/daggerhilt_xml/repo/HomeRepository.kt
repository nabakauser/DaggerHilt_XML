package com.example.daggerhilt_xml.repo

import com.example.daggerhilt_xml.api.ApiHelper
import javax.inject.Inject

class HomeRepository
    @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getUsers() = apiHelper.getUsers()
}