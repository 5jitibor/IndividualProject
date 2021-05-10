package es.usj.androidapps.alu100495.individualproject.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.adapter.ActorAdapter
import es.usj.androidapps.alu100495.individualproject.adapter.GenreAdapter
import es.usj.androidapps.alu100495.individualproject.adapter.MovieAdapter
import es.usj.androidapps.alu100495.individualproject.fragments.ActorsFragment
import es.usj.androidapps.alu100495.individualproject.fragments.GenresFragment
import es.usj.androidapps.alu100495.individualproject.fragments.MoviesFragment
import es.usj.androidapps.alu100495.individualproject.fragments.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_home.*

lateinit var homeContext : Context

class Home : AppCompatActivity() {
    var adapterM = MovieAdapter()
    var adapterA = ActorAdapter()
    var adapterG = GenreAdapter()
    val adapter = ViewPagerAdapter(supportFragmentManager)

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
        val listenerSearch = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                when {
                    adapter.getItem(0).isResumed -> {
                        adapterM.filter.filter(newText)
                    }
                    adapter.getItem(1).isResumed -> {
                        adapterA.filter.filter(newText)
                    }
                    else -> {
                        adapterG.filter.filter(newText)
                    }
                }

                return false
            }


        }

        searchView.setOnQueryTextListener(listenerSearch)

        tabs.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position ==0) {
                    adapterM.filter.filter(searchView.query.toString())
                } else if (tab.getPosition()==1) {
                    adapterA.filter.filter(searchView.query.toString())
                } else {
                    adapterG.filter.filter(searchView.query.toString())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

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

        adapter.addFragment(MoviesFragment(adapterM), "Movies")
        adapter.addFragment(ActorsFragment(adapterA), "Actors")
        adapter.addFragment(GenresFragment(adapterG), "Genres")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_movie_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_person_24)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_menu_24)
    }


}