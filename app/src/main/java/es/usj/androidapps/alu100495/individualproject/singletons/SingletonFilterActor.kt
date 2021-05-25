package es.usj.androidapps.alu100495.individualproject.singletons

import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Movie

object SingletonFilterActor {
    var like = false


    fun filterMovies(actorList: ArrayList<Actor>): ArrayList<Actor>{
        var list = actorList
        var aux: ArrayList<Actor> = arrayListOf()
        if(like){
            aux = arrayListOf()
            for(actor in list){
                if(actor.like){
                    aux.add(actor)
                }
            }
            list=aux
        }
        aux.clear()
        return list

    }
}

