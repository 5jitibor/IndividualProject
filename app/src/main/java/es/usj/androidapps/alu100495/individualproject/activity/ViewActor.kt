package es.usj.androidapps.alu100495.individualproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.adapter.MovieAdapter
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonActors
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonDatabase
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonMovies
import kotlinx.android.synthetic.main.activity_view_actor_genre.*
import kotlinx.coroutines.launch

class ViewActor : AppCompatActivity() {
    lateinit var actor :Actor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_actor_genre)
        val actor : Actor = intent.getSerializableExtra("actor") as Actor
        this.actor = actor
        tvTitleView.text = actor.name
        val adapter = MovieAdapter(filterMovies(actor))
        rvView.layoutManager = LinearLayoutManager(this)
        rvView.adapter = adapter
        like_button_view.isLiked = actor.like
        like_button_view.setOnClickListener {
            like_button_view.isLiked = !like_button_view.isLiked
            actor.like = like_button_view.isLiked
            changeLikeActor(actor)
            lifecycleScope.launch {
                SingletonDatabase.db.room.ActorDao().update(actor)
            }

        }
    }

    override fun onResume() {
        super.onResume()
        (rvView.adapter as MovieAdapter).movieList = filterMovies(actor)
        (rvView.adapter as MovieAdapter).notifyDataSetChanged()


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

    private fun changeLikeActor(actor: Actor){
        for(actorCompare in SingletonActors.list){
            if(actorCompare.id==actor.id){
                actorCompare.like=actor.like
                return
            }
        }
    }
}