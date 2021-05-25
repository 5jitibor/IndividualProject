package es.usj.androidapps.alu100495.individualproject.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.adapter.ActorAdapter
import es.usj.androidapps.alu100495.individualproject.adapter.GenreAdapter
import es.usj.androidapps.alu100495.individualproject.adapter.MovieAdapter
import es.usj.androidapps.alu100495.individualproject.classData.Movie
import es.usj.androidapps.alu100495.individualproject.fragments.ActorsFragment
import es.usj.androidapps.alu100495.individualproject.fragments.GenresFragment
import es.usj.androidapps.alu100495.individualproject.fragments.MoviesFragment
import es.usj.androidapps.alu100495.individualproject.fragments.ViewPagerAdapter
import es.usj.androidapps.alu100495.individualproject.singletons.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.launch

lateinit var homeContext : Home
lateinit var adapterM : MovieAdapter
lateinit var adapterA : ActorAdapter
lateinit var adapterG :GenreAdapter
var searchView: SearchView? = null
class Home : AppCompatActivity() {

    var fragmentM = MoviesFragment()
    var fragmentG = GenresFragment()
    var fragmentA = ActorsFragment()
    val adapter = ViewPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeContext = this
        adapterM = MovieAdapter(SingletonMovies.list)
        adapterA = ActorAdapter(SingletonActors.list)
        adapterG = GenreAdapter(SingletonGenres.list)

        setUpTabs()
        btnNewMovie.setOnClickListener {
            var intent = Intent(this, EditMovie::class.java)
            intent.putExtra("code", CODENEWMOVIE)
            startActivityForResult(intent,0)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            var movieAux = data?.extras?.get("movie") as Movie
            lifecycleScope.launch{
                movieAux.id = SingletonDatabase.db.room.MovieDao().getMax()+1
                SingletonMovies.list.add(movieAux)
                SingletonDatabase.db.room.MovieDao().insert(movieAux)
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bar_menu_home, menu)
        val search = menu?.findItem(R.id.action_search)
        searchView = search?.actionView as SearchView
        searchView!!.queryHint = "Search Something!"
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

        searchView!!.setOnQueryTextListener(listenerSearch)
            return super.onCreateOptionsMenu(menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_contact -> {
                startActivity(Intent(this, Contact::class.java))
            }
            R.id.action_filter->{
                startActivity(Intent(this, FilterMovies::class.java))
            }

        }
        return super.onOptionsItemSelected(item)
    }


    private fun setUpTabs(){

        adapter.addFragment(fragmentM, "Movies")
        adapter.addFragment(fragmentA, "Actors")
        adapter.addFragment(fragmentG, "Genres")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_movie_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_person_24)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_menu_24)
    }


}