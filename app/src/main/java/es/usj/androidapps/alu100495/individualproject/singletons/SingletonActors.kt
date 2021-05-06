package es.usj.androidapps.alu100495.individualproject.singletons

import es.usj.androidapps.alu100495.individualproject.classData.Actor
import java.util.ArrayList


object SingletonActors{

    val list: ArrayList<Actor> = arrayListOf()


    fun fillWithContentFromAPI(dataActors: Array<Actor>){
        list.addAll(dataActors)
    }
}