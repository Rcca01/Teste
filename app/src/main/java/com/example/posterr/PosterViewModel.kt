package com.example.posterr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.posterr.models.Poster

class PosterViewModel: ViewModel() {
    private val posters: MutableList<Poster> = mutableListOf()
    private val listPoster: MutableLiveData<MutableList<Poster>> = MutableLiveData()

    fun listPosters(): LiveData<MutableList<Poster>> = listPoster

    private val itemList: MutableLiveData<Int> = MutableLiveData()
    fun reloadItemList(): LiveData<Int> = itemList

    fun addNewPoster(poster: Poster) {
        posters.add(0, poster)
        listPoster.postValue(posters)
    }

    fun addNewComment(position:Int, text: String){
        posters[position].list.add(text)
        itemList.postValue(position)
    }

    fun getListPosters(): MutableList<Poster> {
        return posters
    }
}