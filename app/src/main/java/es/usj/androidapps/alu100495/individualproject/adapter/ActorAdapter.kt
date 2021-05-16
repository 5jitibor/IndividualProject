package es.usj.androidapps.alu100495.individualproject.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.activity.ViewActor
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonActors
import kotlinx.android.synthetic.main.actor_item_layout.view.*
import java.util.*
import kotlin.collections.ArrayList

class ActorAdapter(list:ArrayList<Actor>): RecyclerView.Adapter<ActorAdapter.ActorHolder>(),Filterable {
    var actorList: ArrayList<Actor> = arrayListOf()
    init {
        actorList.addAll(list)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ActorHolder(layoutInflater.inflate(R.layout.actor_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ActorHolder, position: Int) {
        holder.render(actorList[position])
    }

    override fun getItemCount(): Int = actorList.size


    class ActorHolder(private val view:View):RecyclerView.ViewHolder(view){
        fun render(actor: Actor){
            view.tvName.text = actor.name
            view.like_button_actor.isLiked = actor.like
            view.like_button_actor.setOnClickListener{
                view.like_button_actor.isLiked = !view.like_button_actor.isLiked
                actor.like = view.like_button_actor.isLiked
            }
            view.cvActor.setOnClickListener{
                val intent = Intent(view.context, ViewActor::class.java)
                intent.putExtra("actor", actor)
                ContextCompat.startActivity(view.context, intent, null)
            }


        }
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