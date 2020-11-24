package com.example.musicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.model.Song;

import java.util.List;

public class MusicPlayerAdapter extends
        RecyclerView.Adapter<MusicPlayerAdapter.MusicPlayerViewHolder> {

    private SendIntent mCallback;
    private Play mPlay;
    private List<Song> mSongList;
    private Context mContext;

    public MusicPlayerAdapter(List<Song> songList, Context context, SendIntent callback) {
        mSongList = songList;
        mContext = context;
        mCallback = callback;
    }

    public MusicPlayerAdapter(List<Song> songList, Context context, SendIntent callback, Play play) {
        mSongList = songList;
        mContext = context;
        mCallback = callback;
        mPlay = play;
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
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.viewHolderClicked();
                mPlay.playSong();
            }
        });
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

        public MusicPlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            findViews(itemView);
        }

        private void findViews(@NonNull View itemView) {
            mSongImage = itemView.findViewById(R.id.img_song);
            mSongName = itemView.findViewById(R.id.txt_song_name);
            mArtistName = itemView.findViewById(R.id.txt_artist_name);
            mDuration = itemView.findViewById(R.id.txt_song_time);
        }

        private void bindSong(Song song) {
            mArtistName.setText(song.getArtistName());
            mSongName.setText(song.getSongName());
            mSongImage.setImageBitmap(song.getSongImage());
            mDuration.setText(song.getDuration());
        }
    }

    public interface SendIntent {
        void viewHolderClicked();
    }

    public interface Play {
        void playSong();
    }
}
