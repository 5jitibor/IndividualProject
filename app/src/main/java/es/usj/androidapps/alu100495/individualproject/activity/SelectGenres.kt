package es.usj.androidapps.alu100495.individualproject.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.adapter.GenreAdapterSelect
import es.usj.androidapps.alu100495.individualproject.classData.Genre
import kotlinx.android.synthetic.main.activity_select_actors.*

class SelectGenres : AppCompatActivity() {
    lateinit var adapter : GenreAdapterSelect
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_actors)
        val list= intent.getSerializableExtra("genre") as ArrayList<Genre>
        adapter =GenreAdapterSelect(list)
        rvSelect.layoutManager = LinearLayoutManager(this)
        rvSelect.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bar_menu_select, menu)
        val search = menu?.findItem(R.id.action_search_edit)
        val searchView = search?.actionView as SearchView
        val listenerSearch = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        }
        searchView.setOnQueryTextListener(listenerSearch)


        return super.onCreateOptionsMenu(menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_finish -> {
                intent.putExtra("list",adapter.genreListSelected)
                setResult(CODEGENRES,intent)
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}