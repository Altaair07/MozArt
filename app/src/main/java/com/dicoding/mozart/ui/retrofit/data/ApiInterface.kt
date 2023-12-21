package com.dicoding.mozart.ui.retrofit.data

import com.dicoding.mozart.ui.model.Data
import com.dicoding.mozart.ui.model.Museum
import com.dicoding.mozart.ui.model.detail.Detail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("museums")
    suspend fun getMuseumList() : Response<Museum>

    @GET("museums/{category}/{nama}")
    suspend fun getMuseumDetail(
        @Path("nama") nama: String,
        @Path("category") category: String
    ): Response<Detail>
}