package es.usj.androidapps.alu100495.individualproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonGenres
import kotlinx.android.synthetic.main.actor_item_layout.view.*
import kotlinx.android.synthetic.main.genre_item_layout.view.*


class GenreAdapter: RecyclerView.Adapter<GenreAdapter.GenreHolder>(),Filterable {
    var genreList: ArrayList<Genre> = arrayListOf()
    init {
        genreList.addAll(SingletonGenres.list)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GenreHolder(layoutInflater.inflate(R.layout.genre_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: GenreHolder, position: Int) {
        holder.render(genreList[position])
    }

    override fun getItemCount(): Int = genreList.size


    class GenreHolder(val view: View): RecyclerView.ViewHolder(view){
        fun render(genre: Genre){
            view.tvType.text = genre.name
            view.like_button_genre.isLiked = genre.like
            view.like_button_genre.setOnClickListener{
                view.like_button_genre.isLiked = !view.like_button_genre.isLiked
                genre.like = view.like_button_genre.isLiked


            }

        }
    }

    override fun getFilter(): Filter {
        val filter  = FilterAdapterGenre()
        return filter
    }
    inner class FilterAdapterGenre : Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            var listFiltered: ArrayList<Genre> = arrayListOf()

            if(!constraint.toString().isEmpty()){
                listFiltered = SingletonGenres.list.filter { it.name.toLowerCase().startsWith(constraint.toString().toLowerCase()) } as ArrayList<Genre>
            }
            else{
                listFiltered.addAll(SingletonGenres.list)
            }
            val filterResult  = FilterResults()
            filterResult.values = listFiltered
            return filterResult
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            genreList.clear()
            if (results != null) {
                genreList.addAll(results.values as Collection<Genre>)
                notifyDataSetChanged()
            }
        }

    }
}