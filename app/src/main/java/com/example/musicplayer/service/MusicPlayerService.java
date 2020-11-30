package com.example.musicplayer.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.musicplayer.R;
import com.example.musicplayer.controller.activity.ContainerActivity;
import com.example.musicplayer.model.Song;
import com.example.musicplayer.repository.MusicPlayerRepository;

import java.util.UUID;

public class MusicPlayerService extends Service {

    public static final String EXTRA_SONG_ID = "com.example.musicplayer.songId";

    private Song mSong;
    private MusicPlayerRepository mRepository;

    public MusicPlayerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mRepository = MusicPlayerRepository.getInstance();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        UUID songId = (UUID) intent.getExtras().getSerializable(EXTRA_SONG_ID);
        mSong = mRepository.getSong(songId);

        int[] songs = new int[]{R.raw.hello,
                R.raw.someone_like_you,
                R.raw.skyfall,
                R.raw.rumour_has_it,
                R.raw.rolling_in_the_deep,
                R.raw.one_and_only,
                R.raw.lovesong,
                R.raw.dark,
                R.raw.chasing_pavements,
                R.raw.best_for_last};

        MediaPlayer mediaPlayer = new MediaPlayer();

        for (int i = 0; i < songs.length; i++)
            if (getResources().getResourceName(songs[i]).substring(28).equals(mSong.getSongName())) {
                mediaPlayer = MediaPlayer.create(this, songs[i]);
            }

        Notification notification = createNotification();
        startForeground(R.string.notification_channel_id, notification);
        mediaPlayer.start();

        return START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Notification createNotification() {
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                ContainerActivity.newIntent(this, mSong.getSongId()),
                0);

        return new NotificationCompat.Builder(
                this, getResources().getString(R.string.notification_channel_id))
                .setContentTitle(getResources().getString(R.string.notification_channel_title))
                .setContentText(getResources().getString(R.string.notification_channel_description))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
    }

    public static Intent newIntent(Context context, UUID songId) {
        Intent intent = new Intent(context, MusicPlayerService.class);
        intent.putExtra(EXTRA_SONG_ID, songId);
        return intent;
    }
}