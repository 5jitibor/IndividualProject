package es.usj.androidapps.alu100495.individualproject.adapter

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.activity.ViewMovie
import es.usj.androidapps.alu100495.individualproject.activity.homeContext
import es.usj.androidapps.alu100495.individualproject.api.APIPosterAsyncTask
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonDatabase
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonFilterMovies
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonMovies
import kotlinx.android.synthetic.main.movie_item_layout.view.*
import kotlinx.android.synthetic.main.movie_item_layout.view.cvMovie
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.util.*
import kotlin.collections.ArrayList


class MovieAdapter(list:ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieHolder>(),Filterable {
    var movieList: ArrayList<Movie> = arrayListOf()

    init {
        movieList.addAll(list)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieHolder(layoutInflater.inflate(R.layout.movie_item_layout, parent, false))

    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.render(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    class MovieHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun render(movie: Movie){
            view.tvTitle.text = movie.title
            view.stars.rating = movie.rating/2
            showImage(movie, view)
            view.like_button_movie.isLiked = movie.like
            view.like_button_movie.setOnClickListener{
                view.like_button_movie.isLiked = !view.like_button_movie.isLiked
                movie.like = view.like_button_movie.isLiked
                homeContext.fragmentM.onResume()
                homeContext.lifecycleScope.launch {
                    SingletonDatabase.db.room.MovieDao().update(movie)
                }

            }
            view.cvMovie.setOnClickListener{
                val intent = Intent(view.context, ViewMovie::class.java)
                intent.putExtra("movie", movie)
                startActivity(view.context, intent, null)
            }
        }

        private fun showImage(movie: Movie, view: View){
            view.movie_photo.setImageResource(R.drawable.icon)
            val cw = ContextWrapper(view.context)
            val directory =  cw.getDir("images", Context.MODE_PRIVATE)
            val nameImage = movie.id.toString() + ".jpg"
            val imagePath = File(directory, nameImage)
            if(imagePath.exists()){
                val bitmap = BitmapFactory.decodeStream(FileInputStream(imagePath))
                view.movie_photo.setImageBitmap(bitmap)
            }
            else{
                APIPosterAsyncTask(view, movie).execute()
            }


        }
    }


    override fun getFilter(): Filter {
        return FilterAdapterMovie()
    }



    inner class FilterAdapterMovie : Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            var listFiltered: ArrayList<Movie> = arrayListOf()

            if(constraint.toString().isNotEmpty()){

                listFiltered = SingletonFilterMovies.filterMovies(SingletonMovies.list).filter { it.title.toLowerCase(Locale.ROOT).startsWith(constraint.toString().toLowerCase(Locale.ROOT)) } as ArrayList<Movie>
            }
            else{
                listFiltered.addAll(SingletonFilterMovies.filterMovies(SingletonMovies.list))
            }
            val filterResult  = FilterResults()
            filterResult.values = listFiltered
            return filterResult
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            movieList.clear()
            if (results != null) {
                movieList.addAll(results.values as Collection<Movie>)
                notifyDataSetChanged()
            }
        }

    }

}