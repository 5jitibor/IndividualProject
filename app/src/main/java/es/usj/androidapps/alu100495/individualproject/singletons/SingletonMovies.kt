package es.usj.androidapps.alu100495.individualproject.singletons


import es.usj.androidapps.alu100495.individualproject.classData.Movie

object SingletonMovies {

    val list: ArrayList<Movie> = arrayListOf()


    fun fillWithContentFromAPI(dataActors: Array<Movie>){
        list.addAll(dataActors)
    }
}