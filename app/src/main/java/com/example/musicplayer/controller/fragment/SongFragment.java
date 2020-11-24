package com.example.musicplayer.controller.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.adapter.MusicPlayerAdapter;
import com.example.musicplayer.controller.activity.ContainerActivity;
import com.example.musicplayer.model.Song;
import com.example.musicplayer.repository.MusicPlayerRepository;
import com.example.musicplayer.service.MusicPlayerService;

public class SongFragment extends Fragment {

    public static final String TAG = "SongFragment";
    private RecyclerView mSongRecyclerView;
    private MusicPlayerRepository mRepository;

    public SongFragment() {

    }

    public static SongFragment newInstance() {
        SongFragment fragment = new SongFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = MusicPlayerRepository.getInstance();
        getSongInformation();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_song, container, false);

        findViews(view);
        initViews();
        setupAdapter();

        return view;
    }

    private void findViews(View view) {
        mSongRecyclerView = view.findViewById(R.id.song_recycler_view);
    }

    private void initViews() {
        mSongRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupAdapter() {
        mSongRecyclerView.setAdapter(new MusicPlayerAdapter(mRepository.getSongList(),
                getContext(),
                new MusicPlayerAdapter.SendIntent() {
            @Override
            public void viewHolderClicked() {
                Intent intent = ContainerActivity.newIntent(getContext());
                startActivity(intent);

            }}, new MusicPlayerAdapter.Play() {
            @Override
            public void playSong() {
                getContext().startService(MusicPlayerService.newIntent(getContext()));
            }
        }));
    }

    private void getSongInformation() {

        int[] songs = new int[]{R.raw.hello,
                R.raw.someone_like_you,
                R.raw.skyfall,
                R.raw.rumour_has_it,
                R.raw.rolling_in_the_deep,
                R.raw.one_and_only,
                R.raw.lovesong,
                R.raw.dark,
                R.raw.chasing_pavements,
                R.raw.best_for_last};

        MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
        for (int i = 0; i < songs.length; i++) {
            String songName = getContext().getResources().getResourceName(songs[i]).substring(28);
            Uri mediaPath = Uri.parse(
                    "android.resource://" + getContext().getPackageName() + "/" + songs[i]);
            metadataRetriever.setDataSource(getContext(), mediaPath);
            long duration = Long.parseLong(
                    metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
            String seconds = String.valueOf((duration % 60000) / 1000);
            String minutes = String.valueOf(duration / 60000);
            String songDuration = minutes + ":" + seconds;
            byte[] artBytes =  metadataRetriever.getEmbeddedPicture();
            Bitmap songImage = ((BitmapDrawable) getResources().getDrawable(R.drawable.thumbnail_art)).getBitmap();
            if(artBytes!=null)
            {
                songImage = BitmapFactory.decodeByteArray(artBytes, 0, artBytes.length);
            }
            Song song = new Song(metadataRetriever
                    .extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST), metadataRetriever
                    .extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM),
                    songName, songDuration, songImage);
            mRepository.insertSong(song);
        }
    }
}