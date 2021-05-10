package es.usj.androidapps.alu100495.individualproject.database

import androidx.lifecycle.LiveData
import androidx.room.*
import es.usj.androidapps.alu100495.individualproject.classData.Genre

@Dao
interface GenreDao {
    @Query("SELECT * FROM $TABLE_GENRES")
    fun getAllGenres(): LiveData<List<Genre>>

    @Query("SELECT * FROM $TABLE_GENRES WHERE id = :id ")
    fun getByIdGenres(id:Int): Genre

    @Update
    fun update(actor: Genre)

    @Insert
    fun insert(actor: ArrayList<Genre>)

    @Delete
    fun delete(actor: Genre)

}