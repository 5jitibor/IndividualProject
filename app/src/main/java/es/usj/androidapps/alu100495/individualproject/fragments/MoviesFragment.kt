package es.usj.androidapps.alu100495.individualproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.activity.adapterA
import es.usj.androidapps.alu100495.individualproject.activity.adapterM
import es.usj.androidapps.alu100495.individualproject.activity.searchView
import es.usj.androidapps.alu100495.individualproject.adapter.MovieAdapter
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonFilterMovies
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonMovies
import kotlinx.android.synthetic.main.fragment_movies.*


class MoviesFragment  : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onStart() {
        super.onStart()

        var list : ArrayList<Movie> = arrayListOf()
        list.addAll(SingletonFilterMovies.filterMovies(SingletonMovies.list))
        adapterM.movieList= list
        if(searchView != null){
            adapterM.filter.filter(searchView!!.query)
        }
        adapterM.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()

        if(searchView != null){
            adapterM.filter.filter(searchView!!.query)
            adapterM.notifyDataSetChanged()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }


    private fun initRecycler(){
        rvMovies.layoutManager = LinearLayoutManager(context)
        rvMovies.adapter = adapterM

    }

}