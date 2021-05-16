package es.usj.androidapps.alu100495.individualproject.database

import androidx.lifecycle.LiveData
import androidx.room.*
import es.usj.androidapps.alu100495.individualproject.classData.Genre

@Dao
interface GenreDao {
    @Query("SELECT * FROM $TABLE_GENRES")
    suspend fun getAll(): Array<Genre>

    @Query("SELECT * FROM $TABLE_GENRES WHERE id = :id ")
    suspend fun getById(id:Int): Genre

    @Update
    suspend fun update(actor: Genre)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(actor: Array<Genre>)

    @Delete
    suspend fun delete(actor: Genre)

}