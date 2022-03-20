package com.example.chat.firebase

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.IBinder
import android.speech.RecognitionListener
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.example.chat.Data
import com.example.chat.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingServicing : FirebaseMessagingService(){
    override fun onNewToken(token: String) {
        super.onNewToken(token)

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage.notification?.imageUrl==null){
            showNotification(title = remoteMessage.notification?.title?:"",
                body =remoteMessage.notification?.body?:"")
        }else{
            Glide.with(this)
                .asBitmap()
                .load(remoteMessage.notification?.imageUrl)
                .into(object :CustomTarget<Bitmap>(){
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        showNotification(title = remoteMessage.notification?.title?:"",
                            body =remoteMessage.notification?.body?:"",imageUri = resource)
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {
                    }

                })

        }
    }

    fun showNotification(title:String,body:String){
        //set the notification's content and channel
        val notificationBuilder= NotificationCompat.Builder(this, Data.CHANNEL_ID)
        notificationBuilder.setSmallIcon(R.drawable.ic_notif_icon)
        notificationBuilder.setContentTitle(title)
        notificationBuilder.setContentText(body)

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(10,notificationBuilder.build())

    }
    fun showNotification(title:String,body:String,imageUri:Bitmap?){
        //set the notification's content and channel
        val notificationBuilder= NotificationCompat.Builder(this, Data.CHANNEL_ID)
        notificationBuilder.setSmallIcon(R.drawable.ic_notif_icon)
        notificationBuilder.setContentTitle(title)
        notificationBuilder.setContentText(body)
        notificationBuilder.setStyle(NotificationCompat.BigPictureStyle().bigPicture(imageUri))
    }
}