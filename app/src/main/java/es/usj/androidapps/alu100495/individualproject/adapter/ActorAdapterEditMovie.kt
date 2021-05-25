package es.usj.androidapps.alu100495.individualproject.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import kotlinx.android.synthetic.main.actor_item_select_layout.view.*

class ActorAdapterEditMovie(var  actorList: ArrayList<Actor> ): RecyclerView.Adapter<ActorAdapterEditMovie.ActorHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ActorHolder(layoutInflater.inflate(R.layout.actor_item_edit_movie_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ActorHolder, position: Int) {
        holder.render(actorList[position])
    }

    override fun getItemCount(): Int = actorList.size


   inner class ActorHolder(private val view:View):RecyclerView.ViewHolder(view) {
       fun render(actor: Actor) {
           view.tvName.text = actor.name
       }
   }

}