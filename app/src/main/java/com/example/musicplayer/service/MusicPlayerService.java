package com.example.musicplayer.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.example.musicplayer.R;
import com.example.musicplayer.controller.activity.ContainerActivity;

public class MusicPlayerService extends Service {

    public MusicPlayerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, MusicPlayerService.class);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int notificationId = 1;
        Intent notificationIntent = ContainerActivity.newIntent(this);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(this, "music_player_channel_id")
                .setContentTitle("Music Player Notification")
                .setContentText("This is a notification for Music Player")
                .setSmallIcon(R.drawable.thumbnail_art)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.dark);
        mediaPlayer.start();
        return START_REDELIVER_INTENT;
    }
}
