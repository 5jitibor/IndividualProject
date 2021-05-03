package es.usj.androidapps.alu100495.individualproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.activity.homeContext
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonGenres
import kotlinx.android.synthetic.main.genre_item_layout.view.*


class GenreAdapter: RecyclerView.Adapter<GenreAdapter.GenreHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHolder {
        val layoutInflater = LayoutInflater.from(homeContext)
        return GenreHolder(layoutInflater.inflate(R.layout.genre_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: GenreHolder, position: Int) {
        holder.render(SingletonGenres.list[position])
    }

    override fun getItemCount(): Int = SingletonGenres.list.size


    class GenreHolder(val view: View): RecyclerView.ViewHolder(view){
        fun render(genre: Genre){
            view.tvType.text = genre.name

        }
    }
}