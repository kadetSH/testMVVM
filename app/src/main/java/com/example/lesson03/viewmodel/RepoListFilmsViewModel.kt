package com.example.lesson03.viewmodel

import android.app.Application
import android.content.res.TypedArray
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lesson03.R
import com.example.lesson03.recyclerMy.FilmsItem


class RepoListFilmsViewModel(application: Application) : AndroidViewModel(application) {
    init {
        startTime()
    }

    val reposLiveData = MutableLiveData<ArrayList<FilmsItem>>()

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

    fun startTime(){
        object : CountDownTimer(20000, 2000){
            override fun onTick(millisUntilFinished: Long) {
                var we = list
                list.clear()
                list = we
            }

            override fun onFinish() {
                TODO("Not yet implemented")
            }

        }
    }


}