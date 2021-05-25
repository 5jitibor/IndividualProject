package es.usj.androidapps.alu100495.individualproject.classData

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Movie(@PrimaryKey var id: Int, val title: String, val genres : List<Int>, val description : String, val director : String, val actors: List<Int>, val year: Int, val runtime: Int, val rating: Float, val votes: Int, val revenue: Float,
                 var like: Boolean = false): Serializable

