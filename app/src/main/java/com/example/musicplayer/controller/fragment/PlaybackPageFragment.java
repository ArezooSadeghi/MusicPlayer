package com.example.musicplayer.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.musicplayer.R;
import com.example.musicplayer.model.Song;
import com.example.musicplayer.repository.MusicPlayerRepository;
import com.example.musicplayer.service.MyService;

import java.util.UUID;

public class PlaybackPageFragment extends Fragment {

    public static final String ARGS_SONG_ID = "songId";
    public static final String ACTION_PAUSE = "com.example.musicplayer.action.pause";
    public static final String ACTION_PLAY_AGAIN = "com.example.musicplayer.action.play.again";
    public static final String ACTION_NEXT = "com.example.musicplayer.action.next";
    public static final String ACTION_PREVIOUS = "com.example.musicplayer.action.previous";

    private TextView mTextStart, mTextEnd, mTextSongName, mTextSongArtist;
    private ImageView mImageSong;
    private ImageButton mButtonPlay,
            mButtonNext,
            mButtonPrevious,
            mButtonShuffle,
            mButtonRepeat,
            mButtonPause;
    private Song mSong;
    private MusicPlayerRepository mRepository;

    public PlaybackPageFragment() {
    }

    public static PlaybackPageFragment newInstance(UUID songId) {
        PlaybackPageFragment fragment = new PlaybackPageFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_SONG_ID, songId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRepository = MusicPlayerRepository.getInstance();

        UUID songId = (UUID) getArguments().getSerializable(ARGS_SONG_ID);
        mSong = mRepository.getSong(songId);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_play_back_page, container, false);

        findViews(view);
        initViews();

        mButtonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mButtonPause.setVisibility(View.GONE);
                mButtonPlay.setVisibility(View.VISIBLE);
                Intent intent = MyService.newIntent(getContext());
                intent.setAction(ACTION_PAUSE);
                getContext().startService(intent);
            }
        });

        mButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mButtonPause.setVisibility(View.VISIBLE);
                mButtonPlay.setVisibility(View.GONE);
                Intent intent = MyService.newIntent(getContext());
                intent.setAction(ACTION_PLAY_AGAIN);
                getContext().startService(intent);
            }
        });

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MyService.newIntent(getContext());
                intent.setAction(ACTION_NEXT);
                getContext().startService(intent);
            }
        });

        mButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MyService.newIntent(getContext());
                intent.setAction(ACTION_PREVIOUS);
                getContext().startService(intent);
            }
        });

        return view;
    }

    private void findViews(View view) {
        mTextStart = view.findViewById(R.id.txt_start);
        mTextEnd = view.findViewById(R.id.txt_end);
        mTextSongName = view.findViewById(R.id.txt_song_name);
        mTextSongArtist = view.findViewById(R.id.txt_artist_name);
        mImageSong = view.findViewById(R.id.img_song_picture);
        mButtonNext = view.findViewById(R.id.btn_next);
        mButtonPrevious = view.findViewById(R.id.btn_previous);
        mButtonPlay = view.findViewById(R.id.btn_play);
        mButtonPause = view.findViewById(R.id.btn_pause);
        mButtonRepeat = view.findViewById(R.id.btn_repeat);
        mButtonShuffle = view.findViewById(R.id.btn_shuffle);
    }

    private void initViews() {
        mImageSong.setImageBitmap(mSong.getSongImage());
        mTextSongName.setText(mSong.getSongName());
        mTextSongArtist.setText(mSong.getArtistName());
    }
}