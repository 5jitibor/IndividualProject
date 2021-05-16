package es.usj.androidapps.alu100495.individualproject.database

import androidx.room.*
import es.usj.androidapps.alu100495.individualproject.classData.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM $TABLE_MOVIES")
    suspend fun getAll(): Array<Movie>

    @Query("SELECT * FROM $TABLE_MOVIES WHERE id = :id ")
    suspend fun getById(id: Int): Movie

    @Update
    suspend fun update(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Array<Movie>)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT COUNT(*) FROM $TABLE_MOVIES")
    fun count(): Int

}