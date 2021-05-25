package es.usj.androidapps.alu100495.individualproject.database


import androidx.room.*
import es.usj.androidapps.alu100495.individualproject.classData.Actor

@Dao
interface ActorDao {

    @Query("SELECT * FROM $TABLE_ACTORS")
    suspend fun getAll(): Array<Actor>

    @Query("SELECT * FROM $TABLE_ACTORS WHERE id = :id ")
    suspend fun getById(id:Int): Actor

    @Update
    suspend fun update(actor: Actor)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(actor: Array<Actor>)

    @Delete
    suspend fun delete(actor:Actor)

    @Query("SELECT MAX (id) FROM $TABLE_ACTORS ")
    fun getMax(): Int





}