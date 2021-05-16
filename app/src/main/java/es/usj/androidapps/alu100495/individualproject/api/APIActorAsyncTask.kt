package es.usj.androidapps.alu100495.individualproject.api


import android.os.AsyncTask
import com.google.gson.Gson
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonDatabase
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class APIActorAsyncTask() : AsyncTask<Any, Any, Array<Actor>>() {
    override fun doInBackground(vararg params: Any?): Array<Actor>? {
        val url = URL("http://$SERVER:$PORT/user/getActors.php?user=$USER&pass=$PASSWORD")
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

        try {
            val input: InputStream = BufferedInputStream(urlConnection.inputStream)
            val response = readStream(input)
            val result =  Gson().fromJson(response, Array<Actor>::class.java)
            SingletonDatabase.db.room.ActorDao().insert(result)
            return result
        }catch (e: Exception){
            e.printStackTrace()
        } finally {
            urlConnection.disconnect()
        }
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