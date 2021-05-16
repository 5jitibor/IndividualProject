package es.usj.androidapps.alu100495.individualproject.activity

import android.content.Context
import android.content.ContextWrapper
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.adapter.ActorAdapter
import es.usj.androidapps.alu100495.individualproject.adapter.GenreAdapter
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonActors
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonGenres
import kotlinx.android.synthetic.main.activity_view_movie.*
import java.io.File
import java.io.FileInputStream

class ViewMovie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_movie)

        val movie : Movie = intent.getSerializableExtra("movie") as Movie
        tvtitle.text = movie.title

        obtainImage(movie)

        tvTime.text=calculateTime(movie.runtime)
        tvDirector.text = "Director: " + movie.director

        tvDescription.text = movie.description

        tvRenevue.text = "Revenue: "+movie.revenue.toString() +"$"

        generateRecyclerViews(movie)

        tvVotes.text = "Votes: "+ movie.votes
        tvNumberRating.text = "Punctuation:"+movie.rating.toString()
        ratingBarMovie.rating = movie.rating/2

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

    private fun generateRecyclerViews(movie:Movie){
        val linealGenre = LinearLayoutManager(this)
        linealGenre.orientation = LinearLayoutManager.HORIZONTAL
        rvMovieGenres.layoutManager = linealGenre
        rvMovieGenres.adapter = GenreAdapter(obtainGenres(movie))
        val linealMovie = LinearLayoutManager(this)
        linealMovie.orientation = LinearLayoutManager.HORIZONTAL
        rvMovieActors.layoutManager = linealMovie
        rvMovieActors.adapter = ActorAdapter(obtainActors(movie))
    }
}