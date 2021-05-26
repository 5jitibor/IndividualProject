package es.usj.androidapps.alu100495.individualproject.api


import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonDatabase
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonGenres
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class APIGenreAsyncTask : AsyncTask<Any, Any, Boolean>() {
    override fun doInBackground(vararg params: Any?): Boolean {
        val url = URL("http://$SERVER:$PORT/user/getGenres.php?user=$USER&pass=$PASSWORD")
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

        try {
            val input: InputStream = BufferedInputStream(urlConnection.inputStream)
            val response = readStream(input)
            val result =  Gson().fromJson(response, Array<Genre>::class.java)
            update(result)
            SingletonGenres.fillWithContentFromDatabase()

            return true
        }catch (e: Exception){
            Log.e("error",e.printStackTrace().toString())
        } finally {
            urlConnection.disconnect()
        }
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

    fun update(list: Array<Genre>){
        for(genre in list){
            if(SingletonDatabase.db.room.GenreDao().exist(genre.id)!=0){
                try {
                    SingletonDatabase.db.room.GenreDao().update(genre.id,genre.name)
                }
                catch (e: Exception){

                    Log.e("error",e.toString())
                }

            }
            else{
                SingletonDatabase.db.room.GenreDao().insertNoSuspend(genre)
            }
        }
    }

}