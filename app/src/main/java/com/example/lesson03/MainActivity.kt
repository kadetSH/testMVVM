package com.example.lesson03

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.lesson03.FilmsDescriptionFragment.Companion.adapter
import com.example.lesson03.jsonMy.FilmModel
import com.example.lesson03.jsonMy.FilmsJS
import com.example.lesson03.jsonMy.Themoviedb
import com.example.lesson03.net.App
import com.example.lesson03.recyclerMy.FilmsItem
import com.example.lesson03.viewmodel.RepoListFilmsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    FilmsFragment.OnFilmLikeClickListener { //,

    private val viewModel by lazy {
        ViewModelProvider(this).get(RepoListFilmsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        id_navigation.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.id_films -> clickFilms()
            R.id.id_favorites -> appTest()
            R.id.id_films_add -> Toast.makeText(this, "3", Toast.LENGTH_SHORT).show()
            R.id.id_films_dell -> Toast.makeText(this, "4", Toast.LENGTH_SHORT).show()
            R.id.id_invite -> Toast.makeText(this, "Нажали пригласить", Toast.LENGTH_SHORT).show()
        }
        id_drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onFilmLikeClick(filmsItem: FilmsItem, position: Int, note: String) {
        TODO("Not yet implemented")
    }

    fun clickFilms() {
        viewModel.openFilmLis()
    }

    fun appTest() {

        val items = mutableListOf<FilmsJS>()

        App.instance.api.getFilms()
            .enqueue(object : Callback<Themoviedb> {
                override fun onFailure(call: Call<Themoviedb>, t: Throwable) {
                    println("")
                }

                override fun onResponse(
                    call: Call<Themoviedb>,
                    response: Response<Themoviedb>,
                ) {
                    items.clear()
                    if (response.isSuccessful) {

                        var res = response.body()

                        res?.let {
                            it.title?.let { it1 ->
                                it.backdropPath?.let { it2 ->
                                    it.overview?.let { it3 ->
                                        FilmsJS(
                                            it1,
                                            it2,
                                            0,
                                            it3
                                        )
                                    }
                                }
                            }?.let { it2 ->
                                items.add(
                                    it2
                                )
                            }
                        }


                    }
                    adapter?.notifyDataSetChanged()
                }
            })
    }


}