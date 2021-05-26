package es.usj.androidapps.alu100495.individualproject.singletons

import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Movie

object SingletonFilterActor {
    var like = false


    fun filterActor(actorList: ArrayList<Actor>): ArrayList<Actor>{
        var list:ArrayList<Actor> = arrayListOf()
        list.addAll(actorList)
        var aux: ArrayList<Actor> = arrayListOf()
        if(like){
            aux = arrayListOf()
            for(actor in list){
                if(actor.like){
                    aux.add(actor)
                }
            }
            list.clear()
            list.addAll(aux)
        }
        aux.clear()
        return list

    }
}

