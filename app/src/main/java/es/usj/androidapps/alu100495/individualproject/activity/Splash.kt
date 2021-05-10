package es.usj.androidapps.alu100495.individualproject.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.usj.androidapps.alu100495.individualproject.R
import es.usj.androidapps.alu100495.individualproject.api.APIControlAsyncTask
import es.usj.androidapps.alu100495.individualproject.singletons.SingletonDatabase


class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        SingletonDatabase.start(this)
        APIControlAsyncTask(this).execute()

    }

}