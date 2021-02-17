package com.example.lesson03

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.example.lesson03.recyclerMy.FilmsItem
import com.example.lesson03.viewmodel.RepoListFilmsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, FilmsFragment.OnFilmLikeClickListener{ //,

    var list = ArrayList<FilmsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        id_navigation.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.id_films -> clickFilms()
            R.id.id_favorites -> Toast.makeText(this, "2", Toast.LENGTH_SHORT).show()
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

    fun clickFilms(){
        RepoListFilmsViewModel(this.application).openFilmLis()
    }


}