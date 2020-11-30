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
import com.example.musicplayer.adapter.MusicPlayerAdapter;
import com.example.musicplayer.controller.activity.ContainerActivity;
import com.example.musicplayer.controller.fragment.PlaybackPageFragment;
import com.example.musicplayer.model.Song;
import com.example.musicplayer.repository.MusicPlayerRepository;

import java.util.UUID;

public class MyService extends Service implements MediaPlayer.OnPreparedListener {

    public static final String ACTION_PLAY = "com.example.musicplayer.action.play";

    private MediaPlayer mPlayer;
    private MusicPlayerRepository mRepository;
    private Song mSong;
    private int mCurrentIndexSong;
    private int[] mSongs;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mCurrentIndexSong = -1;
        mRepository = MusicPlayerRepository.getInstance();
        mSongs = mRepository.getSongs();
        startForeground(1, createNotification());
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getAction().equals(ACTION_PLAY)) {
            UUID songId = (UUID) intent.getExtras().getSerializable(MusicPlayerAdapter.EXTRA_SONG_ID);
            mSong = mRepository.getSong(songId);
            playMusic();
        }

        if (intent.getAction().equals(PlaybackPageFragment.ACTION_PAUSE)) {
            mPlayer.pause();
        }

        if (intent.getAction().equals(PlaybackPageFragment.ACTION_PLAY_AGAIN)) {
            mPlayer.start();
        }

        if (intent.getAction().equals(PlaybackPageFragment.ACTION_NEXT)) {
            mPlayer.release();
            if (mCurrentIndexSong == 9) {
                mPlayer = MediaPlayer.create(this, mSongs[0]);
                mCurrentIndexSong = 0;
                mPlayer.start();
            } else {
                mPlayer = MediaPlayer.create(this, mSongs[mCurrentIndexSong + 1]);
                ++mCurrentIndexSong;
                mPlayer.start();
            }
        }

        if (intent.getAction().equals(PlaybackPageFragment.ACTION_PREVIOUS)) {
            mPlayer.release();
            if (mCurrentIndexSong == 0) {
                mPlayer = MediaPlayer.create(this, mSongs[9]);
                mCurrentIndexSong = 9;
                mPlayer.start();
            } else {
                mPlayer = MediaPlayer.create(this, mSongs[mCurrentIndexSong - 1]);
                --mCurrentIndexSong;
                mPlayer.start();
            }
        }

        return START_REDELIVER_INTENT;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }


    private void playMusic() {
        for (int i = 0; i < mSongs.length; i++)
            if (getResources().getResourceName(mSongs[i]).substring(28).equals(mSong.getSongName())) {
                mCurrentIndexSong = i;
                mPlayer = MediaPlayer.create(this, mSongs[i]);
                mPlayer.start();
            }
    }


    private Notification createNotification() {

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                ContainerActivity.newIntent(this),
                0);

        return new NotificationCompat.Builder(
                this, getResources().getString(R.string.notification_channel_id))
                .setOngoing(true)
                .setContentTitle(getResources().getString(R.string.notification_title))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
    }


    public static Intent newIntent(Context context) {
        return new Intent(context, MyService.class);
    }
}