package es.usj.androidapps.alu100495.individualproject.activity

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.adapter.ActorAdapter
import es.usj.androidapps.alu100495.individualproject.adapter.GenreAdapter
import es.usj.androidapps.alu100495.individualproject.api.ADDMOVIE
import es.usj.androidapps.alu100495.individualproject.api.APIMovieAddAsyncTask
import es.usj.androidapps.alu100495.individualproject.api.REMOVEMOVIE
import es.usj.androidapps.alu100495.individualproject.api.UPDATEMOVIE
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonActors
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonDatabase
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonGenres
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonMovies
import kotlinx.android.synthetic.main.activity_view_movie.*
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream

class ViewMovie : AppCompatActivity() {

    lateinit var movie:Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_movie)

        movie  = intent.getSerializableExtra("movie") as Movie
        generateRecyclerViews()
        loadData()
        obtainImage(movie)


        like_button_view_movie.isLiked = movie.like
        like_button_view_movie.setOnClickListener {
            like_button_view_movie.isLiked = !like_button_view_movie.isLiked
            movie.like = like_button_view_movie.isLiked
            changeLikeMovie(movie)
            lifecycleScope.launch {
                SingletonDatabase.db.room.MovieDao().update(movie)
            }
        }

    }

    fun loadData(){
        tvtitle.text = movie.title
        tvTime.text=calculateTime(movie.runtime)
        tvDirector.text = "Director: " + movie.director

        tvDescription.text = movie.description

        tvRenevue.text = "Revenue: "+movie.revenue.toString() +"$"

        tvVotes.text = "Votes: "+ movie.votes
        tvNumberRating.text = "Punctuation:"+movie.rating.toString()
        ratingBarMovie.rating = movie.rating/2

        rvMovieGenres.adapter = GenreAdapter(obtainGenres(movie))
        rvMovieActors.adapter = ActorAdapter(obtainActors(movie))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bar_menu_view, menu)

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_edit -> {
                val intent = Intent(this, EditMovie::class.java)
                intent.putExtra("movie",movie)
                intent.putExtra("code", CODEEDITMOVIE)
                startActivityForResult(intent,0)
            }
            R.id.action_remove -> {
                SingletonMovies.list.remove(movie)
                lifecycleScope.launch {
                    SingletonDatabase.db.room.MovieDao().delete(movie)
                    APIMovieAddAsyncTask(movie, REMOVEMOVIE).execute()
                }
                finish()

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun calculateTime(time:Int):String{
        var hours = 0
        var minutes = time

        while(minutes>=60){
            hours++
            minutes -= 60
        }
        if(hours == 0){
            return "Duration: $minutes"+"min"
        }

            return "Duration: $hours"+"h"+" $minutes"+"min"

    }

    private fun obtainGenres(movie:Movie):ArrayList<Genre>{
        val list: ArrayList<Genre> = arrayListOf()
        for(genre in SingletonGenres.list){
            if(movie.genres.contains(genre.id)){
                list.add(genre)
            }
        }

        return list
    }

    private fun obtainActors(movie:Movie):ArrayList<Actor>{
        val list: ArrayList<Actor> = arrayListOf()
        for(actor in SingletonActors.list){
            if(movie.actors.contains(actor.id)){
                list.add(actor)
            }
        }

        return list
    }

    private fun changeMovie() {
        var i = 0
        for(movieSearch in SingletonMovies.list){
            if(movieSearch.id == movie.id){
                SingletonMovies.list.remove(movieSearch )
                SingletonMovies.list.add(i,movie)
                return
            }
            i++
        }
    }

    private fun obtainImage(movie:Movie){
        val cw = ContextWrapper(this)
        val directory =  cw.getDir("images", Context.MODE_PRIVATE)
        val nameImage = movie.id.toString() + ".jpg"
        val imagePath = File(directory, nameImage)
        if(imagePath.exists()){
            val bitmap = BitmapFactory.decodeStream(FileInputStream(imagePath))
            imageViewMovie.setImageBitmap(bitmap)
        }
    }

    private fun generateRecyclerViews(){
        val linealGenre = LinearLayoutManager(this)
        linealGenre.orientation = LinearLayoutManager.HORIZONTAL
        rvMovieGenres.layoutManager = linealGenre

        val linealMovie = LinearLayoutManager(this)
        linealMovie.orientation = LinearLayoutManager.HORIZONTAL
        rvMovieActors.layoutManager = linealMovie

    }

    private fun changeLikeMovie(movie:Movie){
        for(movieCompare in SingletonMovies.list){
            if(movieCompare.id==movie.id){
                movieCompare.like=movie.like
                return
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            val movieAux = data?.extras?.get("movie") as Movie
            movieAux.id = movie.id
            movie = movieAux
            lifecycleScope.launch {
                SingletonDatabase.db.room.MovieDao().update(movie)
                APIMovieAddAsyncTask(movie, UPDATEMOVIE).execute()
            }
            changeMovie()
            loadData()

        }
    }
}