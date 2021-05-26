package es.usj.androidapps.alu100495.individualproject.api

import android.content.Intent
import android.os.AsyncTask
import androidx.lifecycle.lifecycleScope
import es.usj.androidapps.alu100495.individualproject.activity.Home
import es.usj.androidapps.alu100495.individualproject.activity.Splash
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonActors
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonDatabase
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonGenres
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonMovies
import kotlinx.coroutines.launch
import java.net.InetSocketAddress
import java.net.Socket

class SelectOptionAsyncTask(private val context: Splash): AsyncTask<Any, Any, Boolean>() {

    override fun doInBackground(vararg params: Any?): Boolean {
        try {
            val socket = InetSocketAddress(SERVER, PORT)

            // Create an unbound socket
            val sock = Socket()
            val timeoutMs = 2000 // 2 seconds
            sock.connect(socket, timeoutMs)


        } catch (e: java.lang.Exception) {
            loadData()
            e.printStackTrace()
            return false
        }
           return true

    }


    fun loadData(){

        var coroutineFinished = 0

        context.lifecycleScope.launch {
            SingletonGenres.fillWithContentFromDatabaseCoroutine()
            coroutineFinished++
        }

        context.lifecycleScope.launch {
            SingletonMovies.fillWithContentFromDatabaseCoroutine()
            coroutineFinished++
        }

        context.lifecycleScope.launch {
            SingletonActors.fillWithContentFromDatabaseCoroutine()
            coroutineFinished++
        }
        while(coroutineFinished!=3){

        }

        val sendIntent = Intent(context, Home::class.java)
        context.startActivity(sendIntent)
        context.finish()
    }

    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
        if(result == true){
            APIControlAsyncTask(context).execute()
        }

    }

}