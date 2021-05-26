package es.usj.androidapps.alu100495.individualproject.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.adapter.ActorAdapterEditMovie
import es.usj.androidapps.alu100495.individualproject.adapter.GenreAdapterEditMovie
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonActors
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonGenres
import kotlinx.android.synthetic.main.activity_edit_movie.*

class EditMovie : AppCompatActivity() {
    lateinit var adapterGenre: GenreAdapterEditMovie
    lateinit var adapterActor: ActorAdapterEditMovie
    var type: Int = CODENEWMOVIE
    lateinit var movie: Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie)
        type = intent.getSerializableExtra("code") as Int
        if(type== CODEEDITMOVIE){
            insertData()
            tvType.text = "Edit Movie"
        }
        else{
            tvType.text = "New Movie"
        }

        createRecycler()


        btnActors.setOnClickListener{
            val intent =Intent(this,SelectActors::class.java)
            intent.putExtra("actor",adapterActor.actorList)
            startActivityForResult(intent,0)
        }



        btnGenres.setOnClickListener{
            val intent =Intent(this,SelectGenres::class.java)
            intent.putExtra("genre",adapterGenre.genreList)
            startActivityForResult(intent,0)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == CODEACTORS){
            var list = data?.extras?.get("list") as ArrayList<Actor>
            adapterActor.actorList.clear()
            adapterActor.actorList.addAll(list)
            adapterActor.notifyDataSetChanged()
        }
        else if(resultCode == CODEGENRES){
            var list = data?.extras?.get("list") as ArrayList<Genre>
            adapterGenre.genreList.clear()
            adapterGenre.genreList.addAll(list)
            adapterGenre.notifyDataSetChanged()
        }
    }

    fun check():Boolean{
        var check = true
        if(etRating.text.toString().equals("")){
            etRating.error = "Introduce the number"
            check = false
        }
        else if(etRating.text.toString().toFloat()>10.0){
            etRating.error = "The number has to be less or equals than 10"
            check = false
        }

        if(etVotes.text.toString().equals("")){
            etVotes.error = "Introduce the number"
            check = false
        }
        if(etLength.text.toString().equals("")){
            etLength.error = "Introduce the number"
            check = false
        }
        if(etRevenue.text.toString().equals("")){
            etRevenue.error = "Introduce the number"
            check = false
        }
        if(etYear.text.toString().equals("")){
            etYear.error = "Introduce the number"
            check = false
        }
        else if(etYear.text.toString().toInt()<=1900){
            etYear.error = "The number has to be more  than 1900"
            check = false
        }
        if(etTitle.text.toString().equals("")){
            etTitle.error = "Introduce the title"
            check = false
        }
        if(etDirector.text.toString().equals("")){
            etDirector.error = "Introduce the director"
            check = false
        }

        return check
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bar_menu_filter, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_finish ->{
                if(check()){
                    var like = false
                    if(type == CODEEDITMOVIE){
                        like = movie.like
                    }
                    var movie = Movie(-1,etTitle.text.toString(),generateListGenres(),etDescripcion.text.toString(),etDirector.text.toString(),generateListActors(),etYear.text.toString().toInt(),etLength.text.toString().toInt(),etRating.text.toString().toFloat(),etVotes.text.toString().toInt(),etRevenue.text.toString().toFloat(),like)
                    intent.putExtra("movie",movie)
                    setResult(Activity.RESULT_OK,intent)
                    finish()
                }
                else{
                    Toast.makeText(this,"There are fields that are incomplete or incorrect",Toast.LENGTH_LONG)
                }
            }

        }
        return super.onOptionsItemSelected(item)
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

    private fun obtainGenres(movie:Movie):ArrayList<Genre>{
        val list: ArrayList<Genre> = arrayListOf()
        for(genre in SingletonGenres.list){
            if(movie.genres.contains(genre.id)){
                list.add(genre)
            }
        }

        return list
    }
    private fun createRecycler(){
        val linealActor = LinearLayoutManager(this)
        linealActor.orientation = LinearLayoutManager.HORIZONTAL
        rvActorsEdit.layoutManager = linealActor

        val linealGenre = LinearLayoutManager(this)
        linealGenre.orientation = LinearLayoutManager.HORIZONTAL
        rvGenresEdit.layoutManager = linealGenre


        if(type==CODEEDITMOVIE){
            adapterActor = ActorAdapterEditMovie(obtainActors(movie))
            adapterGenre = GenreAdapterEditMovie(obtainGenres(movie))
        }
        else{
            adapterActor = ActorAdapterEditMovie(arrayListOf())
            adapterGenre = GenreAdapterEditMovie(arrayListOf())
        }

        rvActorsEdit.adapter = adapterActor
        rvGenresEdit.adapter = adapterGenre
    }
    private fun generateListGenres(): ArrayList<Int>{
        val list: ArrayList<Int> = arrayListOf()
        for(genre in adapterGenre.genreList){
            list.add(genre.id)
        }
        return list
    }

    private fun generateListActors(): ArrayList<Int>{
        val list: ArrayList<Int> = arrayListOf()
        for(genre in adapterActor.actorList){
            list.add(genre.id)
        }
        return list
    }

    private fun insertData(){
        movie = intent.getSerializableExtra("movie") as Movie
        etTitle.append(movie.title)
        etDescripcion.append(movie.description)
        etDirector.append(movie.director)
        etRevenue.append(movie.revenue.toString())
        etYear.append(movie.year.toString())
        etLength.append(movie.runtime.toString())
        etRating.append(movie.rating.toString())
        etVotes.append(movie.votes.toString())
    }



}