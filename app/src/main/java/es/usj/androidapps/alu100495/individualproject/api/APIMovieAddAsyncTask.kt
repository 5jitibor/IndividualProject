package es.usj.androidapps.alu100495.individualproject.api

import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.classData.MovieApi
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

class APIMovieAddAsyncTask(val movie: Movie,val tipo: String): AsyncTask<Any, Any, Boolean>() {

    override fun doInBackground(vararg params: Any?): Boolean {
        val url = URL("http://$SERVER:$PORT/user/$tipo.php?user=$USER&pass=$PASSWORD")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.doOutput = true
        connection.setRequestProperty("Content-Type", "application/json; utf-8")
        connection.setRequestProperty("Accept", "application/json")
        val writer = DataOutputStream(connection.outputStream)
        val json = Gson().toJson(MovieApi(movie.id,movie.title, movie.genres,movie.description,movie.director, movie.actors,  movie.year, movie.runtime, movie.rating, movie.votes, movie.revenue))

        writer.write(json.toByteArray(StandardCharsets.UTF_8))
        writer.flush()
        BufferedReader(InputStreamReader(connection.inputStream, "utf-8")).use { br ->
            val response = java.lang.StringBuilder()
            var responseLine: String?
            while (br.readLine().also { responseLine = it } != null) {
                response.append(responseLine!!.trim { it <= ' ' })
            }
            Log.e("response", response.toString())
        }
        writer.close()


        return true
    }


}