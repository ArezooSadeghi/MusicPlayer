package com.example.musicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.model.Song;

import java.util.List;

public class MusicPlayerAdapter extends
        RecyclerView.Adapter<MusicPlayerAdapter.MusicPlayerViewHolder> {

    private List<Song> mSongList;
    private Context mContext;

    public MusicPlayerAdapter(List<Song> songList, Context context) {
        mSongList = songList;
        mContext = context;
    }

    public List<Song> getSongList() {
        return mSongList;
    }

    public void setSongList(List<Song> songList) {
        mSongList = songList;
    }

    @NonNull
    @Override
    public MusicPlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.item_detail, parent, false);
        return new MusicPlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicPlayerViewHolder holder, int position) {
        holder.bindSong(mSongList.get(position));
    }

    @Override
    public int getItemCount() {
        return mSongList.size();
    }

    public class MusicPlayerViewHolder extends RecyclerView.ViewHolder {

        public MusicPlayerViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        private void bindSong(Song song) {

        }
    }
}
