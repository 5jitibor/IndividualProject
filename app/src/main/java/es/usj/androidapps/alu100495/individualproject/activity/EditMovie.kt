package es.usj.androidapps.alu100495.individualproject.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bar_menu_filter, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_finish ->{
                var like = false
                if(type == CODEEDITMOVIE){
                    like = movie.like
                }
                var movie = Movie(-1,etTitle.text.toString(),generateListGenres(),etDescripcion.text.toString(),etDirector.text.toString(),generateListActors(),etYear.text.toString().toInt(),etLength.text.toString().toInt(),etRating.text.toString().toFloat(),etVotes.text.toString().toInt(),etRevenue.text.toString().toFloat(),like)
                intent.putExtra("movie",movie)
                setResult(Activity.RESULT_OK,intent)
                finish()
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
        var linealActor = LinearLayoutManager(this)
        linealActor.orientation = LinearLayoutManager.HORIZONTAL
        rvActorsEdit.layoutManager = linealActor

        var linealGenre = LinearLayoutManager(this)
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
        var list: ArrayList<Int> = arrayListOf()
        for(genre in adapterGenre.genreList){
            list.add(genre.id)
        }
        return list
    }

    private fun generateListActors(): ArrayList<Int>{
        var list: ArrayList<Int> = arrayListOf()
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