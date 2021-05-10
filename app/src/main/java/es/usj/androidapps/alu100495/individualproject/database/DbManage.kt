package es.usj.androidapps.alu100495.individualproject.database

import android.app.Application
import android.content.Context
import androidx.room.Room

class DbManage(context: Context): Application() {

    val room = Room.databaseBuilder(context.applicationContext,AppDB::class.java, DB_NAME).build()
}