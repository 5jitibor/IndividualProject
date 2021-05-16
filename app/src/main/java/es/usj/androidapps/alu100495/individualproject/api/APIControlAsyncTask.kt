package es.usj.androidapps.alu100495.individualproject.api


import android.content.Intent
import android.os.AsyncTask
import android.util.Log
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

class APIControlAsyncTask(private val context: Splash): AsyncTask<Any, Any, Boolean>() {
    private val movieTask = APIMovieAsyncTask()
    private val genreTask = APIGenreAsyncTask()
    private val actorTask = APIActorAsyncTask()
    private lateinit var movieData : Array<Movie>
    private lateinit var genreData : Array<Genre>
    private lateinit var actorData : Array<Actor>

    override fun onPreExecute() {
        super.onPreExecute()
        movieTask.execute()
        genreTask.execute()
        actorTask.execute()
    }
    override fun doInBackground(vararg params: Any?): Boolean {

        movieData = movieTask.get()
        genreData = genreTask.get()
        actorData = actorTask.get()


        return true
    }

    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
        val sendIntent = Intent(context, Home::class.java)
        SingletonActors.fillWithContentFromAPI(actorData)
        SingletonGenres.fillWithContentFromAPI(genreData)
        SingletonMovies.fillWithContentFromAPI(movieData)
        context.startActivity(sendIntent)
        context.finish()


    }
}