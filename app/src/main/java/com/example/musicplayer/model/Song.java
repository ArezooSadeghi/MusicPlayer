package com.example.musicplayer.model;

import android.graphics.Bitmap;

import java.util.UUID;

public class Song {

    private UUID mSongId;
    private String mArtistName;
    private String mAlbumName;
    private String mDuration;
    private String mSongName;
    private Bitmap mSongImage;

    public Song() {
        mSongId = UUID.randomUUID();
    }

    public Song(String artistName, String albumName, String songName, String duration, Bitmap songImage) {
        mArtistName = artistName;
        mAlbumName = albumName;
        mSongName = songName;
        mSongImage = songImage;
        mDuration = duration;
        mSongId = UUID.randomUUID();
    }

    public void setSongImage(Bitmap songImage) {
        mSongImage = songImage;
    }

    public void setArtistName(String artistName) {
        mArtistName = artistName;
    }

    public void setAlbumName(String albumName) {
        mAlbumName = albumName;
    }

    public void setSongName(String songName) {
        mSongName = songName;
    }

    public void setDuration(String duration) {
        mDuration = duration;
    }

    public UUID getSongId() {
        return mSongId;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public String getSongName() {
        return mSongName;
    }

    public String getDuration() {
        return mDuration;
    }

    public Bitmap getSongImage() {
        return mSongImage;
    }
}
