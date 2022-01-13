package com.msc.hybrid.push

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.msc.hybrid.R
import com.msc.hybrid.ui.MainActivity

class MyFirebaseMessagingService: FirebaseMessagingService() {

    companion object {
        private val TAG = "MyFirebaseMessagingService"
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "onNewToken token : ${token}")

        // 토큰 값을 따로 저장해둔다.
        val pref = this.getSharedPreferences("token", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("token", token).apply()
        editor.commit()
        Log.d(TAG, "토큰 저장 완료")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "onMessageReceived From : ${remoteMessage.from}")

        // Notification 메시지를 수신할 경우는
        // remoteMessage.notification?.body!! 여기에 내용이 저장되어있다.
        // Log.d(TAG, "Notification Message Body: " + remoteMessage.notification?.body!!)

        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "onMessageReceived 바디 : ${remoteMessage.data["body"].toString()}")
            Log.d(TAG, "onMessageReceived 타이틀 : ${remoteMessage.data["title"].toString()}")
            sendNotification(remoteMessage.notification?.body)
        } else {
            Log.d(TAG, "onMessageReceived 수신에러 : ${remoteMessage.data.toString()}")
        }
    }

    private fun sendNotification(body: String?) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("Notification", body)
        }
        .0

        var pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        var notificationBuilder = NotificationCompat.Builder(this,"Notification")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Push Notification FCM")
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(notificationSound)
            .setContentIntent(pendingIntent)

        var notificationManager: NotificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }

}