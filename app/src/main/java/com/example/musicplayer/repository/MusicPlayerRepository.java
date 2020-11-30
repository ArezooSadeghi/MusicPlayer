package com.example.musicplayer.repository;

import com.example.musicplayer.model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public Song getSong(UUID songId) {
        for (Song song : mSongList) {
            if (song.getSongId().equals(songId))
                return song;
        }
        return null;
    }
}
