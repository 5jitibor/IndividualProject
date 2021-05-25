package es.usj.androidapps.alu100495.individualproject.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonActors
import kotlinx.android.synthetic.main.actor_item_select_layout.view.*
import java.util.*

class ActorAdapterSelect(actorListSelect: ArrayList<Actor> ): RecyclerView.Adapter<ActorAdapterSelect.ActorHolder>(),Filterable {
    var actorList: ArrayList<Actor>
    var actorListSelected: ArrayList<Actor>
    init {
        actorList = arrayListOf()
        this.actorListSelected = arrayListOf()
        actorList.addAll(SingletonActors.list)
        this.actorListSelected.addAll(actorListSelect)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ActorHolder(layoutInflater.inflate(R.layout.actor_item_select_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ActorHolder, position: Int) {
        holder.render(actorList[position])
    }

    override fun getItemCount(): Int = actorList.size


   inner class ActorHolder(private val view:View):RecyclerView.ViewHolder(view){
        fun render(actor: Actor){
            view.tvName.text = actor.name
            view.select_button_actor.isSelected = true
            view.select_button_actor.isChecked= checkActor(actor)
            view.select_button_actor.setOnClickListener {
                if(checkActor(actor)){
                    actorListSelected.remove(actor)
                }
                else{
                    actorListSelected.add(actor)
                }
            }

        }


    }

    fun checkActor(actor:Actor): Boolean{
        for(actorAux in actorListSelected){
            if(actor.id == actorAux.id){
                return true
            }
        }
        return false
    }

    override fun getFilter(): Filter {
        return FilterAdapterActor()
    }
    inner class FilterAdapterActor : Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            var listFiltered: ArrayList<Actor> = arrayListOf()

            if(constraint.toString().isNotEmpty()){
                listFiltered = SingletonActors.list.filter { it.name.toLowerCase(Locale.ROOT).startsWith(constraint.toString().toLowerCase(Locale.ROOT)) } as ArrayList<Actor>
            }
            else{
                listFiltered.addAll(SingletonActors.list)
            }
            val filterResult  = FilterResults()
            filterResult.values = listFiltered
            return filterResult
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            actorList.clear()
            if (results != null) {
                actorList.addAll(results.values as Collection<Actor>)
                notifyDataSetChanged()
            }
        }

    }



}