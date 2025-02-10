package com.osman.firebasefcm.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.osman.firebasefcm.R
import com.osman.firebasefcm.view.MainActivity
import java.util.Random


class FirebaseService : FirebaseMessagingService() {

    val channelID = "firebase_channel"

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = Random().nextInt()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannelOlustur(notificationManager)
        }

        val notification = NotificationCompat.Builder(this, channelID)
            .setContentTitle(message.data["baslik"])
            .setContentText(message.data["mesaj"])
            .setSmallIcon(R.drawable.ic_android_black_24dp)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()




        notificationManager.notify(notificationId, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun notificationChannelOlustur(notificationManager: NotificationManager) {
        val channelName = "firebaseChannel"
        val channel = NotificationChannel(channelID, channelName, IMPORTANCE_HIGH).apply {
            description = "Kanal tanımı"
            enableLights(true)
            lightColor = Color.RED
        }

        notificationManager.createNotificationChannel(channel)
    }
}