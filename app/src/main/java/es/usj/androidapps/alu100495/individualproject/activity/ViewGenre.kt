package es.usj.androidapps.alu100495.individualproject.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.adapter.MovieAdapter
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonMovies
import kotlinx.android.synthetic.main.activity_view_genre.*

class ViewGenre : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_genre)
        val genre : Genre = intent.getSerializableExtra("genre") as Genre
        tvGenreMovie.text = genre.name
        val adapter = MovieAdapter(filterMovies(genre))
        rvGenreMovie.layoutManager = LinearLayoutManager(this)
        rvGenreMovie.adapter = adapter
    }

    private fun filterMovies(genre: Genre): ArrayList<Movie>{
        val list: ArrayList<Movie> = arrayListOf()
        for(movie in SingletonMovies.list){
            if(movie.genres.contains(genre.id)){
                list.add(movie)
            }
        }
        return list

    }


}