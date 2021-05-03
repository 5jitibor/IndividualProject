package es.usj.androidapps.alu100495.individualproject.singletons

import es.usj.androidapps.alu100495.individualproject.classData.Actor


object SingletonActors{

    val list: ArrayList<Actor> = arrayListOf()


    fun fillWithContentFromAPI(dataActors: Array<Actor>){
        list.addAll(dataActors)
    }
}