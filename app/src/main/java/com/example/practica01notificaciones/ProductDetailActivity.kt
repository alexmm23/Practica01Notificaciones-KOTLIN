package com.example.practica01notificaciones

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        createNotificationChannel()
        val toolbar: Toolbar = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val title = intent.getStringExtra("title")
        val price = intent.getStringExtra("price")
        val description = intent.getStringExtra("description")

        findViewById<TextView>(R.id.txtProductDetailTitle).text = title
        findViewById<TextView>(R.id.txtProductDetailPrice).text = price
        findViewById<TextView>(R.id.txtProductDetailDescription).text = description

        findViewById<Button>(R.id.btnBuy).setOnClickListener {
            sendPurchaseNotification()
        }
    }

    // Volver a la activity anterior
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun createNotificationChannel() {
        val name = "CompraExitosaChannel"
        val descriptionText = "Canal de notificaciones de compra exitosa"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("compra_exitosa_channel", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

    @SuppressLint("MissingPermission")
    private fun sendPurchaseNotification() {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )


        // Construir la notificación
        val builder = NotificationCompat.Builder(this, "compra_exitosa_channel")
            .setSmallIcon(R.drawable.round_add_shopping_cart_24) // Cambia por tu icono
            .setContentTitle("Compra exitosa")
            .setContentText("Tu compra ha sido procesada correctamente")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        // Mostrar la notificación
        with(NotificationManagerCompat.from(this)) {
            notify(1001, builder.build())
        }
    }

}