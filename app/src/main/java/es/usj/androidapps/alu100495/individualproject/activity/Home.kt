package es.usj.androidapps.alu100495.individualproject.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.fragments.ActorsFragment
import es.usj.androidapps.alu100495.individualproject.fragments.GenresFragment
import es.usj.androidapps.alu100495.individualproject.fragments.MoviesFragment
import es.usj.androidapps.alu100495.individualproject.fragments.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_home.*

lateinit var homeContext : Context

class Home : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeContext = this
        setUpTabs()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bar_menu_home, menu)
        val search = menu?.findItem(R.id.action_search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search Something!"

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
            return super.onCreateOptionsMenu(menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
           R.id.action_contact -> {
                startActivity(Intent(this, Contact::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MoviesFragment(),"Movies")
        adapter.addFragment(ActorsFragment(),"Actors")
        adapter.addFragment(GenresFragment(),"Genres")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)


        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_movie_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_person_24)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_menu_24)
    }


}