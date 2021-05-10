package es.usj.androidapps.alu100495.individualproject.database


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.classData.Movie

@Database( entities = [Actor::class,Movie::class,Genre::class], version =  1)
@TypeConverters(ArrayListTypeConverter::class)
abstract class AppDB: RoomDatabase() {
    abstract fun ActorDao():ActorDao
    abstract fun MovieDao():MovieDao
    abstract fun GenreDao():GenreDao
}