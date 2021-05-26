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
import kotlinx.android.synthetic.main.item_select_layout.view.*
import java.util.*

class GenreAdapterSelect(genreListSelect: ArrayList<Genre> ): RecyclerView.Adapter<GenreAdapterSelect.ActorHolder>(),Filterable {
    var genreList: ArrayList<Genre>
    var genreListSelected: ArrayList<Genre>
    init {
        genreList = arrayListOf()
        this.genreListSelected = arrayListOf()
        genreList.addAll(SingletonGenres.list)
        this.genreListSelected.addAll(genreListSelect)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ActorHolder(layoutInflater.inflate(R.layout.item_select_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ActorHolder, position: Int) {
        holder.render(genreList[position])
    }

    override fun getItemCount(): Int = genreList.size


   inner class ActorHolder(private val view:View):RecyclerView.ViewHolder(view){
        fun render(genre: Genre){
            view.tvName.text = genre.name
            view.select_button_actor.isSelected = true
            view.select_button_actor.isChecked= checkGenre(genre)
            view.select_button_actor.setOnClickListener {
                if(checkGenre(genre)){
                    genreListSelected.remove(genre)
                }
                else{
                    genreListSelected.add(genre)
                }
            }

        }


    }

    fun checkGenre(genre:Genre): Boolean{
        for(genreAux in genreListSelected){
            if(genre.id == genreAux.id){
                return true
            }
        }
        return false
    }

    override fun getFilter(): Filter {
        return FilterAdapterGenre()
    }
    inner class FilterAdapterGenre : Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            var listFiltered: ArrayList<Genre> = arrayListOf()

            if(constraint.toString().isNotEmpty()){
                listFiltered = SingletonGenres.list.filter { it.name.toLowerCase(Locale.ROOT).startsWith(constraint.toString().toLowerCase(Locale.ROOT)) } as ArrayList<Genre>
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