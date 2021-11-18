package com.kelm.booklist.api

import com.kelm.booklist.ApiData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("texts?")
    fun getBookList(
        @Query("_quantity")  quantity: String,
        @Query("_characters") characters: String,
        @Query("_seed") seed: String,
        @Query("_locale") locale: String
    ): Call<ApiData>


}