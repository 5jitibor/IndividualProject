package es.usj.androidapps.alu100495.individualproject.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.usj.androidapps.alu100495.individualproject.R
import kotlinx.android.synthetic.main.activity_contact.*

class Contact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        btnMail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/html"
            intent.putExtra(Intent.EXTRA_EMAIL, "alu.100495@usj.com")
            startActivity(Intent.createChooser(intent, "Send Email"))
        }

        btnTelephone.setOnClickListener {
            val uri = Uri.parse("tel:+34567989091")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        btnWeb.setOnClickListener {
            val uri = Uri.parse("http://www.usj.es")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)

        }
    }
}