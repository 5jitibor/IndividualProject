package es.usj.androidapps.alu100495.individualproject.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonActors
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonDatabase
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonFilterMovies
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonMovies
import kotlinx.android.synthetic.main.activity_filter_movies.*
import kotlinx.android.synthetic.main.activity_filter_movies.view.*
import kotlinx.android.synthetic.main.movie_item_layout.view.*
import kotlinx.coroutines.launch

class FilterMovies : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_movies)
        switchLike.switchLike.isChecked = SingletonFilterMovies.like
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bar_menu_filter, menu)

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_finish -> {
                SingletonFilterMovies.like = switchLike.switchLike.isChecked
                setResult(Activity.RESULT_OK,intent)
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}