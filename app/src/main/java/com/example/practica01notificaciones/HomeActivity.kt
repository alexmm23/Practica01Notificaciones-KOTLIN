package com.example.practica01notificaciones

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import java.util.Calendar

class HomeActivity : AppCompatActivity() {
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var intent: Intent
    private lateinit var btnSetAlarm: Button
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
        btnSetAlarm = findViewById(R.id.btnSetAlarm)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        intent = Intent(this, ProductListActivity::class.java)
        pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        btnSetAlarm.setOnClickListener {

            val minutes = 1
            Toast.makeText(this, "Alarma programada en ${minutes} minutos", Toast.LENGTH_SHORT).show()
            setTimer(minutes)
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
    private fun setTimer(minutes: Int){
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            add(Calendar.MINUTE, minutes)
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }
}