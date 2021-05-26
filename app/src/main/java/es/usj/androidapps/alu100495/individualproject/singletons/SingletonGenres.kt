package es.usj.androidapps.alu100495.individualproject.singletons

import es.usj.androidapps.alu100495.individualproject.classData.Genre


object SingletonGenres {

    val list: ArrayList<Genre> = arrayListOf()


    fun fillWithContentFromDatabase(){
       list.addAll(SingletonDatabase.db.room.GenreDao().getAllNoSuspend())
    }

    suspend fun fillWithContentFromDatabaseCoroutine(){
        list.addAll(SingletonDatabase.db.room.GenreDao().getAll())
    }
}