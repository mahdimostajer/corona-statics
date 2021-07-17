package com.example.corona.ui.chart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.corona.network.CoronaApi
import com.example.corona.network.History
import com.example.corona.ui.home.ApiStatus
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChartViewModel @Inject constructor(private val coronaApi: CoronaApi) : ViewModel() {
    private val _history = MutableLiveData<History?>()
    val history: MutableLiveData<History?> = _history

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    init {
        getHistory()
    }

    private fun getHistory() {
        viewModelScope.launch {
            _status.value = ApiStatus.PENDING
            try {
                val result = coronaApi.getHistory()
                _history.value = result
                _status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                _history.value = null
                _status.value = ApiStatus.FAILED
            }
        }
    }
}