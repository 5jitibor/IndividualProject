package es.usj.androidapps.alu100495.individualproject.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.adapter.MovieAdapter
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonDatabase
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonGenres
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonMovies
import kotlinx.android.synthetic.main.activity_view_actor_genre.*
import kotlinx.coroutines.launch

class ViewGenre : AppCompatActivity() {
    lateinit var genre:Genre
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_actor_genre)
        val genre : Genre = intent.getSerializableExtra("genre") as Genre
        this.genre = genre
        tvTitleView.text = genre.name
        val adapter = MovieAdapter(filterMovies(genre))
        rvView.layoutManager = LinearLayoutManager(this)
        rvView.adapter = adapter
        like_button_view.isLiked = genre.like
        like_button_view.setOnClickListener {
            like_button_view.isLiked = !like_button_view.isLiked
            genre.like = like_button_view.isLiked
            changeLikeGenre(genre)
            lifecycleScope.launch {
                SingletonDatabase.db.room.GenreDao().update(genre)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (rvView.adapter as MovieAdapter).movieList = filterMovies(genre)
        (rvView.adapter as MovieAdapter).notifyDataSetChanged()


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
    private fun changeLikeGenre(genre: Genre){
        for(genreCompare in SingletonGenres.list){
            if(genreCompare.id==genre.id){
                genreCompare.like=genre.like
                return
            }
        }
    }


}