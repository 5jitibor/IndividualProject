package es.usj.androidapps.alu100495.individualproject.database


import androidx.lifecycle.LiveData

import androidx.room.*
import es.usj.androidapps.alu100495.individualproject.classData.Actor

@Dao
interface ActorDao {

    @Query("SELECT * FROM $TABLE_ACTORS")
    fun getAllActors(): LiveData<List<Actor>>

    @Query("SELECT * FROM $TABLE_ACTORS WHERE id = :id ")
    fun getByIdActors(id:Int): Actor

    @Update
    fun update(actor: Actor)

    @Insert
    fun insert(actor: ArrayList<Actor>)

    @Delete
    fun delete(actor:Actor)







}