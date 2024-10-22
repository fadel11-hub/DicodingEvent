package com.example.dicodingevent.data.retrofit

import com.example.dicodingevent.data.response.DetailEventResponse
import com.example.dicodingevent.data.response.UpComingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("detail/{id}")
    fun getListEvent(
        @Path("id") id: Int
    ): Call<UpComingResponse>

    @GET("events/{id}")
    fun getDetailEvent(
        @Path("id") id: Int
    ): Call<DetailEventResponse>
}

