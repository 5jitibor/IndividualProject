package es.usj.androidapps.alu100495.individualproject.api


import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import es.usj.androidapps.alu100495.individualproject.*
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.database.ArrayListTypeConverter
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonDatabase
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonMovies
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class APIMovieAsyncTask : AsyncTask<Any, Any, Boolean>() {
    override fun doInBackground(vararg params: Any?): Boolean {
        val url = URL("http://$SERVER:$PORT/user/getMovies.php?user=$USER&pass=$PASSWORD")
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

        try {
            val input: InputStream = BufferedInputStream(urlConnection.inputStream)
            val response = readStream(input)
            val result =  Gson().fromJson(response, Array<Movie>::class.java)
            update(result)
            SingletonMovies.fillWithContentFromDatabase()
            return true
        }catch (e: Exception){
            e.printStackTrace()
            Log.e("error",e.printStackTrace().toString())
        } finally {
            urlConnection.disconnect()
        }
        println("ok")
        return false
    }

    private fun readStream(inputStream: InputStream) : String {
        val br = BufferedReader(InputStreamReader(inputStream))
        val total = StringBuilder()
        while (true) {
            val line = br.readLine() ?: break
            total.append(line).append('\n')
        }
        return total.toString()
    }

     fun update(list: Array<Movie>){
        for(movie in list){
            Log.d("movie", movie.id.toString())
            if(movie.genres==null){
                movie.genres = listOf()
            }
            if(movie.actors==null){
                movie.actors = listOf()
            }
            if(SingletonDatabase.db.room.MovieDao().exist(movie.id)!=0){
                try {
                    SingletonDatabase.db.room.MovieDao().update(movie.id,movie.title, movie.genres,movie.description,movie.director, movie.actors,  movie.year, movie.runtime, movie.rating, movie.votes, movie.revenue)
                }
                catch (e: Exception){

                    Log.e("error",e.toString())
                }

            }
            else{
                SingletonDatabase.db.room.MovieDao().insertNoSuspend(movie)
            }
        }
    }
}