package es.usj.androidapps.alu100495.individualproject.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonActors
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonGenres
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonMovies
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.fragments.ActorsFragment
import es.usj.androidapps.alu100495.individualproject.fragments.GenresFragment
import es.usj.androidapps.alu100495.individualproject.fragments.MoviesFragment
import es.usj.androidapps.alu100495.individualproject.fragments.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_home.*

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
        setUpTabs()
    }

    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MoviesFragment(),"Movies")
        adapter.addFragment(ActorsFragment(),"Actors")
        adapter.addFragment(GenresFragment(),"Genres")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

    }
}