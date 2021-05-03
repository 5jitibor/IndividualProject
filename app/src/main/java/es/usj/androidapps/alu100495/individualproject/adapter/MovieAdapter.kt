package es.usj.androidapps.alu100495.individualproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.activity.homeContext
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonActors
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonMovies
import kotlinx.android.synthetic.main.genre_item_layout.view.*
import kotlinx.android.synthetic.main.movie_item_layout.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val layoutInflater = LayoutInflater.from(homeContext)
        return MovieHolder(layoutInflater.inflate(R.layout.movie_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.render(SingletonMovies.list[position])
    }

    override fun getItemCount(): Int = SingletonMovies.list.size


    class MovieHolder(val view: View): RecyclerView.ViewHolder(view){
        fun render(movie: Movie){
            view.tvTitle.text = movie.title
            view.stars.rating = movie.rating/2
            view.movie_photo.setImageResource(R.drawable.icon)
        }
    }
}