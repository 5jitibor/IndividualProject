package es.usj.androidapps.alu100495.individualproject.database

import androidx.lifecycle.LiveData
import androidx.room.*
import es.usj.androidapps.alu100495.individualproject.classData.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM $TABLE_MOVIES")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM $TABLE_MOVIES WHERE id = :id ")
    fun getByIdActors(id:Int): Movie

    @Update
    fun update(movie: Movie)

    @Insert
    fun insert(movie: ArrayList<Movie>)

    @Delete
    fun delete(movie: Movie)

}