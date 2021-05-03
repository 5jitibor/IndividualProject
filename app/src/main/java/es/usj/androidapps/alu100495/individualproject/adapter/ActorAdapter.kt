package es.usj.androidapps.alu100495.individualproject.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.activity.homeContext
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonActors
import kotlinx.android.synthetic.main.actor_item_layout.view.*

class ActorAdapter: RecyclerView.Adapter<ActorAdapter.ActorHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorHolder {
        val layoutInflater = LayoutInflater.from(homeContext)
        return ActorHolder(layoutInflater.inflate(R.layout.actor_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ActorHolder, position: Int) {
        holder.render(SingletonActors.list[position])
    }

    override fun getItemCount(): Int = SingletonActors.list.size


    class ActorHolder(val view:View):RecyclerView.ViewHolder(view){
        fun render(actor: Actor){
            view.tvName.text = actor.name

        }
    }
}