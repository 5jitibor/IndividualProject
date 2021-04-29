package es.usj.androidapps.alu100495.individualproject.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.Singletons.SingletonActors
import es.usj.androidapps.alu100495.individualproject.Singletons.SingletonGenres
import es.usj.androidapps.alu100495.individualproject.Singletons.SingletonMovies
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.classData.Movie

class Home : AppCompatActivity() {

    lateinit var sMovies : SingletonMovies
    lateinit var sGenres : SingletonGenres
    lateinit var sActors : SingletonActors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        sMovies = SingletonMovies(intent.extras?.get("movies") as Array<Movie>)
        sGenres = SingletonGenres(intent.extras?.get("genres") as Array<Genre>)
        sActors = SingletonActors(intent.extras?.get("actors") as Array<Actor>)

    }
}