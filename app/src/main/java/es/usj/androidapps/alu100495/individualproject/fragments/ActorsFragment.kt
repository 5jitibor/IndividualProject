package es.usj.androidapps.alu100495.individualproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.activity.*
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonActors
import kotlinx.android.synthetic.main.fragment_actors.*


class ActorsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(R.layout.fragment_actors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    override fun onStart() {
        super.onStart()

        var list : ArrayList<Actor> = arrayListOf()
        list.addAll(SingletonActors.list)
        adapterA.actorList= list
        if(searchView != null) {
            adapterA.filter.filter(searchView!!.query)
        }
        adapterA.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()

        if(searchView != null) {
            adapterA.filter.filter(searchView!!.query)
            adapterA.notifyDataSetChanged()
        }

    }

    private fun initRecycler(){
        rvGenresEdit.layoutManager = LinearLayoutManager(context)
        rvGenresEdit.adapter = adapterA
    }
}