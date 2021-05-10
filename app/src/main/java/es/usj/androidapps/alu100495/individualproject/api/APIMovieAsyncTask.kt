package es.usj.androidapps.alu100495.individualproject.api


import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import es.usj.androidapps.alu100495.individualproject.*
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonDatabase
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class APIMovieAsyncTask : AsyncTask<Any, Any, Array<Movie>>() {
    override fun doInBackground(vararg params: Any?): Array<Movie>? {
        val url = URL("http://$SERVER:8888/user/getMovies.php?user=$USER&pass=$PASSWORD")
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

        try {
            val input: InputStream = BufferedInputStream(urlConnection.inputStream)
            val response = readStream(input)
            val result =  Gson().fromJson(response, Array<Movie>::class.java)
            val arrayList : ArrayList<Movie> = arrayListOf()
            arrayList.addAll(result)
            SingletonDatabase.db.room.MovieDao().insert(arrayList)
            return result
        }catch (e: Exception){
            Log.e("error",e.printStackTrace().toString())
        } finally {
            urlConnection.disconnect()
        }
        println("ok")
        return null
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
}