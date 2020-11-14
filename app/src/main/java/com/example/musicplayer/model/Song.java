package com.example.musicplayer.model;

import java.util.UUID;

public class Song {

    private UUID mSongId;
    private String mArtistName;
    private String mAlbumName;

    public Song() {
        mSongId = UUID.randomUUID();
    }

    public Song(String artistName, String albumName) {
        mArtistName = artistName;
        mAlbumName = albumName;
        mSongId = UUID.randomUUID();
    }

    public void setArtistName(String artistName) {
        mArtistName = artistName;
    }

    public void setAlbumName(String albumName) {
        mAlbumName = albumName;
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
}
