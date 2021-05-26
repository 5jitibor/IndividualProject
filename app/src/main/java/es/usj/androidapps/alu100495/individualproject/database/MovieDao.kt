package es.usj.androidapps.alu100495.individualproject.database

import androidx.room.*
import es.usj.androidapps.alu100495.individualproject.classData.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM $TABLE_MOVIES")
    suspend fun getAll(): Array<Movie>

    @Query("SELECT * FROM $TABLE_MOVIES")
     fun getAllNoSuspend(): Array<Movie>

    @Query("SELECT * FROM $TABLE_MOVIES WHERE id = :id ")
    suspend fun getById(id: Int): Movie

    @Query("UPDATE $TABLE_MOVIES SET title =:title,genres = :genres, description = :description, director  = :director, actors = :actors,  year = :year, runtime =:runtime, rating =:rating, votes =:votes, revenue =:revenue WHERE id =:id")
    fun update( id: Int, title: String,genres : List<Int>, description : String, director : String, actors: List<Int>,  year: Int, runtime: Int, rating: Float, votes: Int, revenue: Float)

    @Update
    suspend fun update(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Array<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNoSuspend(movie: Movie)


    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT COUNT(*) FROM $TABLE_MOVIES")
    fun count(): Int

    @Query("SELECT MAX (id) FROM $TABLE_MOVIES ")
    suspend fun getMax(): Int

    @Query("SELECT COUNT(*) FROM $TABLE_MOVIES WHERE id= :id")
    fun exist(id:Int): Int

}