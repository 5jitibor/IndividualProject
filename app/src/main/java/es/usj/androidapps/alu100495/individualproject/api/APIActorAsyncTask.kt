package es.usj.androidapps.alu100495.individualproject.api


import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonActors
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonDatabase
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class APIActorAsyncTask() : AsyncTask<Any, Any, Boolean>() {
    override fun doInBackground(vararg params: Any?): Boolean {
        val url = URL("http://$SERVER:$PORT/user/getActors.php?user=$USER&pass=$PASSWORD")
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

        try {
            val input: InputStream = BufferedInputStream(urlConnection.inputStream)
            val response = readStream(input)
            val result =  Gson().fromJson(response, Array<Actor>::class.java)
            update(result)
            SingletonActors.fillWithContentFromDatabase()
            return true
        }catch (e: Exception){
            e.printStackTrace()
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
    fun update(list: Array<Actor>){
        for(actor in list){
            if(SingletonDatabase.db.room.ActorDao().exist(actor.id)!=0){
                try {
                    SingletonDatabase.db.room.ActorDao().update(actor.id,actor.name)
                }
                catch (e: Exception){

                    Log.e("error",e.toString())
                }

            }
            else{
                SingletonDatabase.db.room.ActorDao().insertNoSuspend(actor)
            }
        }
    }
}