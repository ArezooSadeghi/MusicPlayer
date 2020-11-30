package com.example.musicplayer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.controller.activity.ContainerActivity;
import com.example.musicplayer.model.Song;
import com.example.musicplayer.service.MusicPlayerService;

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
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.item_detail,
                parent,
                false);
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

        private ImageView mSongImage;
        private TextView mSongName, mArtistName, mDuration;
        private Song mSong;

        public MusicPlayerViewHolder(@NonNull View itemView) {
            super(itemView);

            mSongImage = itemView.findViewById(R.id.img_song);
            mSongName = itemView.findViewById(R.id.txt_song_name);
            mArtistName = itemView.findViewById(R.id.txt_artist_name);
            mDuration = itemView.findViewById(R.id.txt_song_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    startActivity();

                    startService();

                }
            });
        }

        private void startService() {
            Intent serviceIntent = MusicPlayerService.newIntent(mContext, mSong.getSongId());
            mContext.startService(serviceIntent);
        }

        private void startActivity() {
            Intent activityIntent = ContainerActivity.newIntent(mContext, mSong.getSongId());
            mContext.startActivity(activityIntent);
        }

        private void bindSong(Song song) {
            mSong = song;
            mArtistName.setText(song.getArtistName());
            mSongName.setText(song.getSongName());
            mSongImage.setImageBitmap(song.getSongImage());
            mDuration.setText(song.getDuration());
        }
    }
}
