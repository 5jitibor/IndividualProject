package es.usj.androidapps.alu100495.individualproject.singletons

import android.content.Context
import es.usj.androidapps.alu100495.individualproject.database.DbManage

object SingletonDatabase {

    lateinit var db : DbManage

    fun start(context: Context){
        db = DbManage(context)
    }
}