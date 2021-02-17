package com.example.lesson03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView

import com.example.lesson03.recyclerMy.FilmsAdapter
import com.example.lesson03.recyclerMy.FilmsItem
import com.example.lesson03.viewmodel.RepoListFilmsViewModel



class FilmsFragment : Fragment() {

//    companion object {
//
//        fun newInstance(list: ArrayList<FilmsItem>): FilmsFragment {
//            val args = Bundle()
//            args.putSerializable("spisok", list)
//
//            val fragment = FilmsFragment()
//            fragment.arguments = args
//            return fragment
//        }
//
//
//        var adapter: FilmsAdapter? = null
//    }

    var list = ArrayList<FilmsItem>()
    var filmP: String = ""
    var favoriteName: ArrayList<String> = ArrayList()
//    private var viewModel : RepoListFilmsViewModel? = null


    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(RepoListFilmsViewModel::class.java)
    }

    private var recyclerView: RecyclerView? = null
    private val adapter by lazy {
        FilmsAdapter(
            LayoutInflater.from(requireContext()),
            list
        ) { filmsItem: FilmsItem, position: Int, note: String ->
            (activity as? OnFilmLikeClickListener)?.onFilmLikeClick(filmsItem, position, note)
        }
    }



    val rcv by lazy {
        view?.findViewById<RecyclerView>(R.id.id_recyclerView)
    }






    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.fragment_films_list, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initRecycler()
        viewModel.repos.observe(viewLifecycleOwner, Observer<ArrayList<FilmsItem>> {
            println("")
        })


    }

    private fun initRecycler() {

        recyclerView = view!!.findViewById(R.id.id_recyclerView)
        recyclerView!!.adapter = adapter
    }

    interface OnFilmLikeClickListener{
        fun onFilmLikeClick(filmsItem: FilmsItem, position: Int, note : String)
    }


}