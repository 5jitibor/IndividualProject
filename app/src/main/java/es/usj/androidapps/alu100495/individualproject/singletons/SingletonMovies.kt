package es.usj.androidapps.alu100495.individualproject.singletons


import es.usj.androidapps.alu100495.individualproject.classData.Movie

object SingletonMovies {

    val list: ArrayList<Movie> = arrayListOf()


    fun fillWithContentFromDatabase(){
        list.addAll(SingletonDatabase.db.room.MovieDao().getAllNoSuspend())
    }

    suspend fun fillWithContentFromDatabaseCoroutine(){
        list.addAll(SingletonDatabase.db.room.MovieDao().getAll())
    }
}