package es.usj.androidapps.alu100495.individualproject.api


import android.app.AlertDialog
import android.content.Intent
import android.os.AsyncTask
import es.usj.androidapps.alu100495.individualproject.Singletons.SingletonMovies
import es.usj.androidapps.alu100495.individualproject.activity.Home
import es.usj.androidapps.alu100495.individualproject.activity.Splash
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.classData.Movie

class APIControlAsyncTask(private val context: Splash): AsyncTask<Any, Any, Int>() {
    val movieTask = APIMovieAsyncTask()
    val genreTask = APIGenreAsyncTask()
    val actorTask = APIActorAsyncTask()
    var movieData : Array<Movie>? = null
    var genreData : Array<Genre>? = null
    var actorData : Array<Actor>? = null

    override fun onPreExecute() {
        super.onPreExecute()
        movieTask.execute()
        genreTask.execute()
        actorTask.execute()
    }
    override fun doInBackground(vararg params: Any?): Int? {
        movieData = movieTask.get()
        genreData = genreTask.get()
        actorData = actorTask.get()
        if(movieData == null || genreData == null || actorData == null ){
            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setTitle("Error")
            alertDialogBuilder.setMessage("The server is not responding.Please try again later")
            alertDialogBuilder.setPositiveButton("OK"){dialogInterface, i->context.finish()}
            alertDialogBuilder.show()
            return null
        }
        return 1
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
        val sendIntent = Intent(context, Home::class.java)
        sendIntent.putExtra("movies",movieData)
        sendIntent.putExtra("genres",genreData)
        sendIntent.putExtra("actors",actorData)
        context.startActivity(sendIntent)
        context.finish()
    }
}