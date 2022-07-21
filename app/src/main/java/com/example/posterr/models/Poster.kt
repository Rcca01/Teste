package com.example.posterr.models

data class Poster(
    val text: String,
    val list: MutableList<String>,
    var numRepost: Int = 0,
    val idUser: Int = 1
)
