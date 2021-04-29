package es.usj.androidapps.alu100495.individualproject.api

import android.app.AlertDialog
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import es.usj.androidapps.alu100495.individualproject.PASSWORD
import es.usj.androidapps.alu100495.individualproject.SERVER
import es.usj.androidapps.alu100495.individualproject.USER
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class APIGenreAsyncTask : AsyncTask<Any, Any, Array<Genre>>() {
    override fun doInBackground(vararg params: Any?): Array<Genre>? {
        val url = URL("http://$SERVER:8888/user/getGenres.php?user=$USER&pass=$PASSWORD")
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

        try {
            val input: InputStream = BufferedInputStream(urlConnection.inputStream)
            val response = readStream(input)
            val result =  Gson().fromJson(response, Array<Genre>::class.java)
            return result
        }catch (e: Exception){
            Log.e("error",e.printStackTrace().toString())
        } finally {
            urlConnection.disconnect()
        }
        return null
    }

    fun readStream(inputStream: InputStream) : String {
        val br = BufferedReader(InputStreamReader(inputStream))
        val total = StringBuilder()
        while (true) {
            val line = br.readLine() ?: break
            total.append(line).append('\n')
        }
        return total.toString()
    }
}