package es.usj.androidapps.alu100495.individualproject.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.adapter.ActorAdapterEditMovie
import es.usj.androidapps.alu100495.individualproject.adapter.GenreAdapterEditMovie
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonActors
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonDatabase
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonFilterMovies
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonMovies
import kotlinx.android.synthetic.main.activity_edit_movie.*
import kotlinx.android.synthetic.main.activity_filter_movies.*
import kotlinx.android.synthetic.main.activity_filter_movies.view.*
import kotlinx.android.synthetic.main.movie_item_layout.view.*
import kotlinx.coroutines.launch

class FilterMovies : AppCompatActivity() {
    lateinit var adapterGenre: GenreAdapterEditMovie
    lateinit var adapterActor: ActorAdapterEditMovie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_movies)
        switchLike.isChecked = SingletonFilterMovies.like
        switchActor.isChecked = SingletonFilterMovies.actors
        switchGenre.isChecked = SingletonFilterMovies.genre
        createRecycler()

        btnActorFilter.setOnClickListener{
            if(switchActor.isChecked){
                val intent =Intent(this,SelectActors::class.java)
                intent.putExtra("actor",adapterActor.actorList)
                startActivityForResult(intent,0)
            }

        }



        btnGenresFilter.setOnClickListener{
            if(switchGenre.isChecked){
                val intent =Intent(this,SelectGenres::class.java)
                intent.putExtra("genre",adapterGenre.genreList)
                startActivityForResult(intent,0)
            }
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
            R.id.action_finish -> {
                SingletonFilterMovies.like = switchLike.isChecked
                SingletonFilterMovies.actors = switchActor.isChecked
                SingletonFilterMovies.genre = switchGenre.isChecked
                SingletonFilterMovies.listGenre.clear()
                SingletonFilterMovies.listGenre.addAll(adapterGenre.genreList)
                SingletonFilterMovies.listActor.clear()
                SingletonFilterMovies.listActor.addAll(adapterActor.actorList)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun createRecycler(){
        val linealActor = LinearLayoutManager(this)
        linealActor.orientation = LinearLayoutManager.HORIZONTAL
        rvActorFilter.layoutManager = linealActor

        val linealGenre = LinearLayoutManager(this)
        linealGenre.orientation = LinearLayoutManager.HORIZONTAL
        rvGenreFilter.layoutManager = linealGenre



        val listActor : ArrayList<Actor> = arrayListOf()
        listActor.addAll(SingletonFilterMovies.listActor)
        adapterActor = ActorAdapterEditMovie(listActor)
        val listGenre : ArrayList<Genre> = arrayListOf()
        listGenre.addAll(SingletonFilterMovies.listGenre)
        adapterGenre = GenreAdapterEditMovie(listGenre)



        rvActorFilter.adapter = adapterActor
        rvGenreFilter.adapter = adapterGenre
    }
}