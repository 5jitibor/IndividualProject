package es.usj.androidapps.alu100495.individualproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import kotlinx.android.synthetic.main.activity_view_movie.*

class ViewMovie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_movie)
        var movie : Movie = intent.getSerializableExtra("movie") as Movie
        tvtitle.text = movie.title
    }
}