package es.usj.androidapps.alu100495.individualproject.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.adapter.ActorAdapterSelect
import es.usj.androidapps.alu100495.individualproject.classData.Actor
import kotlinx.android.synthetic.main.activity_select.*

class SelectActors : AppCompatActivity() {
    lateinit var adapter : ActorAdapterSelect
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)
        val list= intent.getSerializableExtra("actor") as ArrayList<Actor>
        adapter =ActorAdapterSelect(list)
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
                intent.putExtra("list",adapter.actorListSelected)
                setResult(CODEACTORS,intent)
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}