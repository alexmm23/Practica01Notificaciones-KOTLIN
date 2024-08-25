package com.example.practica01notificaciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.Toolbar

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val btnViewProducts = findViewById<Button>(R.id.btnProducts)
        btnViewProducts.setOnClickListener {
            startActivity(Intent(this, ProductListActivity::class.java))
        }

    }

    // Volver a la activity anterior
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() // Finaliza la actividad y vuelve a la anterior
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}