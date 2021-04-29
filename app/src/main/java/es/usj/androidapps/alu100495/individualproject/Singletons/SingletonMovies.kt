package es.usj.androidapps.alu100495.individualproject.Singletons

import es.usj.androidapps.alu100495.individualproject.classData.Movie

class SingletonMovies(var dataMovies: Array<Movie>?) {

    val map: MutableMap<Int,Movie> = mutableMapOf()

    init{
        fillWithContentFromAPI()
    }

    private fun fillWithContentFromAPI(){
        for(movie in dataMovies!!){
            map.putIfAbsent(movie.id, movie)
        }
    }
}