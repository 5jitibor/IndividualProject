package es.usj.androidapps.alu100495.individualproject.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.activity.ViewGenre
import es.usj.androidapps.alu100495.individualproject.activity.homeContext
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.singletons.*
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


class GenreAdapter(list:ArrayList<Genre>): RecyclerView.Adapter<GenreAdapter.GenreHolder>(),Filterable {
    var genreList: ArrayList<Genre> = arrayListOf()
    init {
        genreList.addAll(list)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GenreHolder(layoutInflater.inflate(R.layout.item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: GenreHolder, position: Int) {
        holder.render(genreList[position])
    }

    override fun getItemCount(): Int = genreList.size


    class GenreHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun render(genre: Genre){
            view.tvName.text = genre.name
            view.like_button_actor_genre.isLiked = genre.like
            view.like_button_actor_genre.setOnClickListener{
                view.like_button_actor_genre.isLiked = !view.like_button_actor_genre.isLiked
                homeContext.fragmentG.onResume()
                genre.like = view.like_button_actor_genre.isLiked
                homeContext.lifecycleScope.launch {
                    SingletonDatabase.db.room.GenreDao().update(genre)
                }

            }
            view.cvActorGenre.setOnClickListener{
                val intent = Intent(view.context, ViewGenre::class.java)
                intent.putExtra("genre", genre)
                ContextCompat.startActivity(view.context, intent, null)
            }

        }
    }

    override fun getFilter(): Filter {
        return FilterAdapterGenre()
    }
    inner class FilterAdapterGenre : Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            var listFiltered: ArrayList<Genre> = arrayListOf()

            if(constraint.toString().isNotEmpty()){
                listFiltered = SingletonFilterGenres.filterGenres(SingletonGenres.list).filter { it.name.toLowerCase(Locale.ROOT).startsWith(constraint.toString().toLowerCase(Locale.ROOT)) } as ArrayList<Genre>
            }
            else{
                listFiltered.addAll(SingletonFilterGenres.filterGenres(SingletonGenres.list))
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