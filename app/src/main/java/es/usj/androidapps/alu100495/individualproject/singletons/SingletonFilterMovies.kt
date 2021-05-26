package es.usj.androidapps.alu100495.individualproject.singletons

import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.classData.Movie

object SingletonFilterMovies {
    var like :Boolean
    var genre: Boolean
    var listGenre : ArrayList<Genre>
    var actors: Boolean
    var listActor : ArrayList<Actor>
    init {
        like = false
        genre =false
        listGenre = arrayListOf()
        actors =false
        listActor = arrayListOf()
    }
    fun filterMovies(movieList: ArrayList<Movie>): ArrayList<Movie>{
        var list:ArrayList<Movie> = arrayListOf()
        list.addAll(movieList)
        var aux: ArrayList<Movie> = arrayListOf()
        if(like){
            for(movie in list){
                if(movie.like){
                    aux.add(movie)
                }
            }
            list.clear()
            list.addAll(aux)
        }
        aux.clear()
        if(genre){
            for(movie in list){
                if(checkAllGenre(movie)){
                    aux.add(movie)
                }
            }
            list.clear()
            list.addAll(aux)
        }
        aux.clear()
        if(actors){
            for(movie in list){
                if(checkAllActor(movie)){
                    aux.add(movie)
                }
            }
            list.clear()
            list.addAll(aux)
        }
        aux.clear()

        return list

    }

    private fun checkAllGenre(movie:Movie):Boolean{
        for(genre in listGenre){
            if(!movie.genres.contains(genre.id)){
                return false
            }
        }
        return true
    }

    private fun checkAllActor(movie:Movie):Boolean{
        for(actor in listActor){
            if(!movie.actors.contains(actor.id)){
                return false
            }
        }
        return true
    }
}

