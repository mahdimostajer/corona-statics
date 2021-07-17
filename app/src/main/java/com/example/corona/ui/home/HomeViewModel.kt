package com.example.corona.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.corona.network.CoronaApi
import com.example.corona.network.Country
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ApiStatus {
    PENDING, FAILED, SUCCESS
}

class HomeViewModel @Inject constructor(private val coronaApi: CoronaApi) : ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _sort = MutableLiveData<String>("cases")
    val sort: LiveData<String> get() = _sort

    init {
        getCountries(sort.value.toString())
    }

    fun setSortType(type: String) {
        Log.d("home viewModel", type)
        _sort.value = type
        getCountries(type)
    }

    private fun getCountries(sort: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.PENDING
            try {
                val result=coronaApi.getCountries(sort, true)
                _countries.value = result
                _status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                _countries.value = listOf()
                _status.value = ApiStatus.FAILED
            }
        }
    }
}