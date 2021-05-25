package es.usj.androidapps.alu100495.individualproject.singletons

import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.classData.Movie

object SingletonFilterGenres {
    var like = false


    fun filterMovies(genreList: ArrayList<Genre>): ArrayList<Genre>{
        var list = genreList
        var aux: ArrayList<Genre> = arrayListOf()
        if(like){
            aux = arrayListOf()
            for(genre in list){
                if(genre.like){
                    aux.add(genre)
                }
            }
            list=aux
        }
        aux.clear()
        return list

    }
}

