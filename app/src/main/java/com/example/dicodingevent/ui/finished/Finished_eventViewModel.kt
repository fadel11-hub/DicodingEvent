package com.example.dicodingevent.ui.finished

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingevent.data.response.DetailEventRespons
import com.example.dicodingevent.data.response.DetailEventResponse
import com.example.dicodingevent.data.response.Event
import com.example.dicodingevent.data.response.ListEventsItem
import com.example.dicodingevent.data.response.UpComingResponse
import com.example.dicodingevent.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Finished_eventViewModel : ViewModel() {
    private val _finished= MutableLiveData<List<ListEventsItem>?>()
    val finished: LiveData<List<ListEventsItem>?> = _finished

    private val _detailFinished = MutableLiveData<Event?>()
    val detailFinished: LiveData<Event?> = _detailFinished

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _error

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    companion object{
        private const val TAG = "FinishedViewModel"
        private const val EVENT_ID = 0
    }
    init {
        listFinishedEvents()
    }
    private fun listFinishedEvents() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getListEvent(EVENT_ID)
        client.enqueue(object : Callback<UpComingResponse> {
            override fun onResponse(call: Call<UpComingResponse>, response: Response<UpComingResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _isLoading.value = false
                        _finished.value = it.listEvents as List<ListEventsItem>?
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                    _error.value = true
                    _message.value = "Error : ${response.message()}"
                }
            }
            override fun onFailure(call: Call<UpComingResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                _error.value = true
                _message.value = "Error : ${t.message.toString()}"
                if (t is java.net.UnknownHostException) {
                    _message.value = "Tidak ada koneksi Internet"
                } else{
                    _message.value = "Error ${t.message}"
                }
            }
        })
            }

    fun detailFinishedEvent(id: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailEvent(EVENT_ID)
        client.enqueue(object : Callback<DetailEventResponse> {
            override fun onResponse(call: Call<DetailEventResponse>, response: Response<DetailEventResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _isLoading.value = false
                        _detailFinished.value = response.body()?.event
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                    _error.value = true
                    _message.value = "Error : ${response.message()}"
                }
            }
            override fun onFailure(call: Call<DetailEventResponse>, t: Throwable) {
                _isLoading.value = false
                _error.value = true
                _message.value = "Error : ${t.message.toString()}"
                Log.e(TAG, "onFailure: ${t.message.toString()}")

            }
        })
    }


        }
