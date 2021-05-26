package es.usj.androidapps.alu100495.individualproject.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonFilterActor
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonFilterGenres
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.activity_filter_movies.*
import kotlinx.android.synthetic.main.activity_filter_movies.tvTitleFilter
import kotlinx.android.synthetic.main.activity_filter_movies.view.*

class FilterGenre : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        tvTitleFilter.text = "Filter Genre"
        switchLikeFilter.isChecked = SingletonFilterGenres.like

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bar_menu_filter, menu)

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_finish -> {
                SingletonFilterGenres.like = switchLikeFilter.isChecked
                setResult(Activity.RESULT_OK,intent)
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}