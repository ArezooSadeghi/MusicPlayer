package com.example.musicplayer.repository;

import com.example.musicplayer.R;
import com.example.musicplayer.model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MusicPlayerRepository {

    public static MusicPlayerRepository sInstance;
    private List<Song> mSongList = new ArrayList<>();
    private int[] mSongs = new int[]{
            R.raw.hello,
            R.raw.someone_like_you,
            R.raw.skyfall,
            R.raw.rumour_has_it,
            R.raw.rolling_in_the_deep,
            R.raw.one_and_only,
            R.raw.lovesong,
            R.raw.dark,
            R.raw.chasing_pavements,
            R.raw.best_for_last
    };

    private MusicPlayerRepository() {
    }

    public static MusicPlayerRepository getInstance() {
        if (sInstance == null)
            sInstance = new MusicPlayerRepository();
        return sInstance;
    }

    public int[] getSongs() {
        return mSongs;
    }

    public void setSongs(int[] songs) {
        mSongs = songs;
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
