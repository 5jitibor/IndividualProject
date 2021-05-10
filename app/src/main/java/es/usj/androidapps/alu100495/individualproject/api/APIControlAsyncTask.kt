package es.usj.androidapps.alu100495.individualproject.api


import android.content.Intent
import android.os.AsyncTask
import es.usj.androidapps.alu100495.individualproject.activity.Home
import es.usj.androidapps.alu100495.individualproject.activity.Splash
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonActors
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonGenres
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonMovies

class APIControlAsyncTask(private val context: Splash): AsyncTask<Any, Any, Int>() {
    val movieTask = APIMovieAsyncTask()
    val genreTask = APIGenreAsyncTask()
    val actorTask = APIActorAsyncTask()
    lateinit var movieData : Array<Movie>
    lateinit var genreData : Array<Genre>
    lateinit var actorData : Array<Actor>

    override fun onPreExecute() {
        super.onPreExecute()
        movieTask.execute()
        genreTask.execute()
        actorTask.execute()
    }
    override fun doInBackground(vararg params: Any?): Int {
        movieData = movieTask.get()
        genreData = genreTask.get()
        actorData = actorTask.get()
       /* if(movieData == null || genreData == null || actorData == null ){
            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setTitle("Error")
            alertDialogBuilder.setMessage("The server is not responding.Please try again later")
            alertDialogBuilder.setPositiveButton("OK"){dialogInterface, i->context.finish()}
            alertDialogBuilder.show()
            return null
        }*/
        return 1
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
        val sendIntent = Intent(context, Home::class.java)
        SingletonActors.fillWithContentFromAPI(actorData)
        SingletonGenres.fillWithContentFromAPI(genreData)
        SingletonMovies.fillWithContentFromAPI(movieData)
        context.startActivity(sendIntent)
        context.finish()
    }
}