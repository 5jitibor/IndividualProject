package es.usj.androidapps.alu100495.individualproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.activity.adapterA
import es.usj.androidapps.alu100495.individualproject.activity.adapterG
import es.usj.androidapps.alu100495.individualproject.activity.searchView
import es.usj.androidapps.alu100495.individualproject.adapter.GenreAdapter
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonGenres
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonMovies
import kotlinx.android.synthetic.main.fragment_genres.*


class GenresFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_genres, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    override fun onStart() {
        super.onStart()

        var list : ArrayList<Genre> = arrayListOf()
        list.addAll(SingletonGenres.list)
        adapterG.genreList= list
        if(searchView != null) {
            adapterG.filter.filter(searchView!!.query)
        }
        adapterG.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()

        if(searchView != null) {
            adapterG.filter.filter(searchView!!.query)
            adapterG.notifyDataSetChanged()
        }

    }

    private fun initRecycler(){
        rvGenres.layoutManager = LinearLayoutManager(context)
        rvGenres.adapter = adapterG
    }


}