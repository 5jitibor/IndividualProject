package es.usj.androidapps.alu100495.individualproject.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.activity.ViewMovie
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonMovies
import kotlinx.android.synthetic.main.movie_item_layout.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieHolder>(),Filterable {
    var movieList: ArrayList<Movie> = arrayListOf()

    init {
        movieList.addAll(SingletonMovies.list)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieHolder(layoutInflater.inflate(R.layout.movie_item_layout,parent,false))

    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.render(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size


    class MovieHolder(val view: View): RecyclerView.ViewHolder(view){
        fun render(movie: Movie){
            view.tvTitle.text = movie.title
            view.stars.rating = movie.rating/2
            view.movie_photo.setImageResource(R.drawable.icon)
            view.cv.setOnClickListener{
                val intent = Intent(view.context, ViewMovie::class.java)
                intent.putExtra("movie",movie)
                startActivity(view.context,intent,null)
            }
        }


    }

    override fun getFilter(): Filter {
        var filter  = FilterAdapterMovie()
        return filter
    }
    inner class FilterAdapterMovie : Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults? {
            var listFiltered: ArrayList<Movie> = arrayListOf()

            if(!constraint.toString().isEmpty()){
               listFiltered = SingletonMovies.list.filter { it.title.toLowerCase().startsWith(constraint.toString().toLowerCase()) } as ArrayList<Movie>
            }
            else{
                listFiltered.addAll(SingletonMovies.list)
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