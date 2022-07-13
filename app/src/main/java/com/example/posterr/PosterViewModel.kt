package com.example.posterr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.posterr.models.Poster

class PosterViewModel: ViewModel() {
    private val posters: MutableList<Poster> = mutableListOf()
    private val listPoster: MutableLiveData<MutableList<Poster>> = MutableLiveData()

    fun listPosters(): LiveData<MutableList<Poster>> = listPoster

    fun addNewPoster(poster: Poster) {
        posters.add(0, poster)
        listPoster.postValue(posters)
    }

    fun getListPosters(): MutableList<Poster> {
        return posters
    }
}