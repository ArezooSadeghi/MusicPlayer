package com.example.musicplayer.repository;

import com.example.musicplayer.model.Song;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayerRepository {

    public static MusicPlayerRepository sInstance;
    private List<Song> mSongList = new ArrayList<>();

    private MusicPlayerRepository() {

    }

    public static MusicPlayerRepository getInstance() {
        if (sInstance == null)
            sInstance = new MusicPlayerRepository();
        return sInstance;
    }

    public List<Song> getSongList() {
        return mSongList;
    }

    public void setSongList(List<Song> songList) {
        mSongList = songList;
    }

    public void insertSong(Song song) {
        mSongList.add(song);
    }
}
