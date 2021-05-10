package es.usj.androidapps.alu100495.individualproject.classData

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Genre(@PrimaryKey val id: Int, val name: String, var like: Boolean = false): Serializable