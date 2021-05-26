package es.usj.androidapps.alu100495.individualproject.singletons

import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Movie

object SingletonFilterMovies {
    var like = false


    fun filterMovies(movieList: ArrayList<Movie>): ArrayList<Movie>{
        var list:ArrayList<Movie> = arrayListOf()
        list.addAll(movieList)
        var aux: ArrayList<Movie> = arrayListOf()
        if(like){
            aux = arrayListOf()
            for(movie in list){
                if(movie.like){
                    aux.add(movie)
                }
            }
            list.clear()
            list.addAll(aux)
        }
        aux.clear()
        return list

    }
}

