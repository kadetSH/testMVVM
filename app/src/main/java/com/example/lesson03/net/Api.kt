package com.example.lesson03.net

import com.example.lesson03.jsonMy.FilmModel
import com.example.lesson03.jsonMy.Themoviedb
import com.example.lesson03.recyclerMy.FilmsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

//    @GET("100?")  //api_key=576c50d1b23a4e5c26962aa1196de8f3
//    fun getFilms(@Query("api_key")api_key : String): Call<List<Themoviedb>>

    @GET("100?api_key=576c50d1b23a4e5c26962aa1196de8f3")  //api_key=576c50d1b23a4e5c26962aa1196de8f3
    fun getFilms(): Call<Themoviedb>

}