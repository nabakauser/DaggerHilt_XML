package com.example.daggerhilt_xml.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerhilt_xml.model.User
import com.example.daggerhilt_xml.repo.HomeRepository
import com.example.daggerhilt_xml.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    private val homeRepository: HomeRepository,
) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<User>>>()
    val users: LiveData<Resource<List<User>>>
        get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            homeRepository.getUsers().let {
                if (it.isSuccessful) {
                    _users.postValue(Resource.success(it.body()))
                } else _users.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}