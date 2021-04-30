package es.usj.androidapps.alu100495.individualproject.singletons

import es.usj.androidapps.alu100495.individualproject.classData.Genre


class SingletonGenres(var dataGenres: Array<Genre>?) {

    val map: MutableMap<Int, Genre> = mutableMapOf()

    init{
        fillWithContentFromAPI()
    }

    private fun fillWithContentFromAPI(){
        for(genre in dataGenres!!){
            map.putIfAbsent(genre.id, genre)
        }
    }
}