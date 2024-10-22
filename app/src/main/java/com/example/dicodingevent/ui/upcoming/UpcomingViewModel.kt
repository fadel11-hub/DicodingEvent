package com.example.dicodingevent.ui.upcoming

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dicodingevent.data.response.DetailEventRespons
import com.example.dicodingevent.data.response.DetailEventResponse
import com.example.dicodingevent.data.response.Event
import com.example.dicodingevent.data.response.ListEventsItem
import com.example.dicodingevent.data.response.UpComingResponse
import com.example.dicodingevent.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingViewModel {
    private val _upcoming = MutableLiveData<List<ListEventsItem>>()
    val upcoming: LiveData<List<ListEventsItem>> = _upcoming

    private val _detailUpcoming = MutableLiveData<Event>()
    val detailUpcoming: LiveData<Event> = _detailUpcoming

    private val _isLoading =MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    companion object {
        private const val TAG = "UpcomingViewModel"
        private const val EVENT_ID = 1
    }

    init {
        listUpcoming()
    }


    private fun listUpcoming() {
        val client = ApiConfig.getApiService().getListEvent(EVENT_ID)
        client.enqueue(object : Callback<UpComingResponse> {
            override fun onResponse(
                call: Call<UpComingResponse>,
                response: Response<UpComingResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _isLoading.value = false
                        _upcoming.value = it.listEvents as List<ListEventsItem>?
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                    _error.value = true
                    _message.value = "Error: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<UpComingResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                _isLoading.value = false
                _error.value = true
                if (t is java.net.UnknownHostException) {
                    _message.value = "Tidak ada koneksi Internet"
                } else {
                    _message.value = "Error: ${t.message.toString()}"
                }
            }
        })
    }

    fun detailUpcomingEvent(id: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailEvent(id)
        client.enqueue(object : Callback<DetailEventResponse<Event>> {
            override fun onResponse(
                call: Call<DetailEventResponse<Event>>,
                response: Response<DetailEventResponse<Event>>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    _detailUpcoming.value = response.body()?.event
                } else {
                    _isLoading.value = false
                    _error.value = true
                    _message.value = "Error: ${response.message()}"
                    Log.e(
                        TAG,
                        "onFailure: ${response.message()}"
                    )
                }
            }
            override fun onFailure(call: Call<DetailEventResponse<Event>>, t: Throwable) {
                _isLoading.value = false
                _error.value = true
                _message.value = "Error: ${t.message}"
                Log.e(
                    TAG,
                    "onFailure: ${t.message}"
                )
            }
        })
    }
}