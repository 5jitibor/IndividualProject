package es.usj.androidapps.alu100495.individualproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.adapter.MovieAdapter
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonMovies
import kotlinx.android.synthetic.main.activity_view_actor.*

class ViewActor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_actor)
        val actor : Actor = intent.getSerializableExtra("actor") as Actor
        tvActor.text = actor.name
        val adapter = MovieAdapter(filterMovies(actor))
        rvMoviesActor.layoutManager = LinearLayoutManager(this)
        rvMoviesActor.adapter = adapter
    }


    private fun filterMovies(actor: Actor): ArrayList<Movie>{
        val list: ArrayList<Movie> = arrayListOf()
        for(movie in SingletonMovies.list){
            if(movie.actors.contains(actor.id)){
                list.add(movie)
            }
        }
        return list

    }
}