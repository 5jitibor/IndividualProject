package es.usj.androidapps.alu100495.individualproject.database


import androidx.room.*
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Movie

@Dao
interface ActorDao {

    @Query("SELECT * FROM $TABLE_ACTORS")
    suspend fun getAll(): Array<Actor>

    @Query("SELECT * FROM $TABLE_ACTORS WHERE id = :id ")
    suspend fun getById(id:Int): Actor

    @Update
    suspend fun update(actor: Actor)


    @Query("UPDATE $TABLE_ACTORS SET name =:title WHERE id =:id")
    fun update( id: Int, title: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(actor: Array<Actor>)

    @Delete
    suspend fun delete(actor:Actor)

    @Query("SELECT MAX (id) FROM $TABLE_ACTORS ")
    fun getMax(): Int

    @Query("SELECT COUNT(*) FROM $TABLE_ACTORS WHERE id= :id")
    fun exist(id:Int): Int


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNoSuspend(actor:Actor)

    @Query("SELECT * FROM $TABLE_ACTORS")
    fun getAllNoSuspend(): Array<Actor>
}