package com.example.lesson03.recyclerMy

import java.io.Serializable

data class FilmsItem(
    var nameFilm : String,
    var imageFilm : Int,
    var shortDescription : String,
    var proverka : String,
    var star : Boolean
) : Serializable