package com.example.lesson03.viewmodel

import android.app.Application
import android.content.res.TypedArray
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson03.FilmsFragment
import com.example.lesson03.R
import com.example.lesson03.recyclerMy.FilmsItem
import com.otus.otusaacpart1.data.entity.Repo

class RepoListFilmsViewModel(application: Application) : AndroidViewModel(application) {

    private val reposLiveData = MutableLiveData<ArrayList<FilmsItem>>()

    var list = ArrayList<FilmsItem>()
    var filmP: String = ""
    var favoriteName: ArrayList<String> = ArrayList()
    val repos: LiveData<ArrayList<FilmsItem>>
        get() = reposLiveData

    fun openFilmLis(){
        list.addAll(
            fillArrays(
                getApplication<Application>().resources.getStringArray(R.array.film),
                getImageId(R.array.film_image),
                getApplication<Application>().resources.getStringArray(
                    R.array.film_description
                )
            )
        )

        reposLiveData.postValue(list)

    }

    fun fillArrays(
        titleArray: Array<String>,
        filmImageArray: IntArray,
        descriptionArray: Array<String>,
    ): List<FilmsItem> {
        var list = ArrayList<FilmsItem>()
        for (i in 0..titleArray.size - 1) {
            var shortDescription = descriptionArray[i]
            var proverka = ""
            if (titleArray[i].equals(filmP)) {
                proverka = filmP
            }
            var idxFav = favoriteName.indexOf(titleArray[i])
            var boolFavorite: Boolean

            if (idxFav == -1) {
                boolFavorite = false
            } else {
                boolFavorite = true
            }
            var spisokItem = FilmsItem(
                titleArray[i], filmImageArray[i], shortDescription.substring(
                    0,
                    120
                ) + "...", proverka, boolFavorite
            )
            list.add(spisokItem)
        }
        return list
    }

    fun getImageId(filmImageArrayId: Int): IntArray {
        var iArray: TypedArray = getApplication<Application>().resources.obtainTypedArray(filmImageArrayId)
        val count = iArray.length()
        val ids = IntArray(count)
        for (i in ids.indices) {
            ids[i] = iArray.getResourceId(i, 0)
        }
        iArray.recycle()
        return ids
    }


}