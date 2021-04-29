package es.usj.androidapps.alu100495.individualproject.Singletons

import es.usj.androidapps.alu100495.individualproject.classData.Actor


class SingletonActors(var dataActors: Array<Actor>?) {

    val map: MutableMap<Int, Actor> = mutableMapOf()

    init{
        fillWithContentFromAPI()
    }

    private fun fillWithContentFromAPI(){
        for(actor in dataActors!!){
            map.putIfAbsent(actor.id, actor)
        }
    }
}