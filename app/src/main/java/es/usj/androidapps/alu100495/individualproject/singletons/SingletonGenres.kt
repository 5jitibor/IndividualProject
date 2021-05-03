package es.usj.androidapps.alu100495.individualproject.singletons

import es.usj.androidapps.alu100495.individualproject.classData.Genre


object SingletonGenres {

    val list: ArrayList<Genre> = arrayListOf()


    fun fillWithContentFromAPI(dataActors: Array<Genre>){
        list.addAll(dataActors)
    }
}