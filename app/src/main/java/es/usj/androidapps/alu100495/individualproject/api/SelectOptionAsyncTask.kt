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
            APIControlAsyncTask(context).execute()

        } catch (e: java.lang.Exception) {
            loadData()
            e.printStackTrace()
        }
        finally {
           return true
        }
    }

    fun loadData(){
        var dataMovies : Array<Movie>
        var dataGenres : Array<Genre>
        var dataActors : Array<Actor>
        var coroutineFinished = 0

        context.lifecycleScope.launch {
            dataGenres = SingletonDatabase.db.room.GenreDao().getAll()
            SingletonGenres.fillWithContentFromAPI(dataGenres)
            coroutineFinished++
        }

        context.lifecycleScope.launch {
            dataMovies =SingletonDatabase.db.room.MovieDao().getAll()
            SingletonMovies.fillWithContentFromAPI(dataMovies)
            coroutineFinished++
        }

        context.lifecycleScope.launch {
            dataActors = SingletonDatabase.db.room.ActorDao().getAll()
            SingletonActors.fillWithContentFromAPI(dataActors)
            coroutineFinished++
        }
        while(coroutineFinished!=3){

        }

        val sendIntent = Intent(context, Home::class.java)
        context.startActivity(sendIntent)
        context.finish()
    }

}