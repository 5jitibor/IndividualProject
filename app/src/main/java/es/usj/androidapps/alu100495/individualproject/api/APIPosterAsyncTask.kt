package es.usj.androidapps.alu100495.individualproject.api

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap

import android.os.AsyncTask
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.classData.MoviePoster
import kotlinx.android.synthetic.main.movie_item_layout.view.*
import java.io.*
import java.net.HttpURLConnection
import java.net.URL


class APIPosterAsyncTask(private var view: View, var movie: Movie) : AsyncTask<Any, Any, String?>() {


    override fun doInBackground(vararg params: Any?): String? {
        val title : String = movie.title
        val titleProcessed = title.replace(' ', '+')
        val url = URL("http://www.omdbapi.com/?t=$titleProcessed&apikey=$APIPOSTER")
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        try {
            val input: InputStream = BufferedInputStream(urlConnection.inputStream)
            val response = readStream(input)
            val result = Gson().fromJson(response, MoviePoster::class.java)
            if(result.Poster.equals("null")){
                return null
            }
            saveImage(result.Poster)
            return result.Poster

        } catch (e: Exception) {
            Log.e("error", e.printStackTrace().toString())
        } finally {
            urlConnection.disconnect()
        }
        return null
    }

    private fun readStream(inputStream: InputStream): String {
        val br = BufferedReader(InputStreamReader(inputStream))
        val total = StringBuilder()
        while (true) {
            val line = br.readLine() ?: break
            total.append(line).append('\n')
        }
        return total.toString()
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        if(result!=null){
            val bitmap = Picasso.get().load(result)
            bitmap.into(view.movie_photo)
        }
        else{
            view.movie_photo.setImageResource(R.drawable.icon)
        }
    }

    private fun saveImage(result: String){
        val bitmap = Picasso.get().load(result).get()
        val cw = ContextWrapper(view.context)
        val directory =  cw.getDir("images",Context.MODE_PRIVATE)
        val nameImage = movie.id.toString() + ".jpg"
        val imagePath = File(directory,nameImage)
        try {
            val out = FileOutputStream(imagePath)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,out)
            out.close()
        }catch (e : java.lang.Exception){
            Log.e("error", e.printStackTrace().toString())
        }
    }


}