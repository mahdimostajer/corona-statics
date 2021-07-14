package com.example.corona.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.corona.network.Country
import com.example.corona.network.GitApiService
import com.example.corona.ui.home.ApiStatus
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val _country = MutableLiveData<Country?>()
    val country: MutableLiveData<Country?> = _country

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    fun getCountry(countryName: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.PENDING
            try {
                val result = GitApiService.retrofitService.getCountry(countryName)
                _country.value = result
                _status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                _country.value = null
                _status.value = ApiStatus.FAILED
            }
        }
    }
}