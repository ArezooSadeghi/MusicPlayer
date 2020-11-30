package com.example.musicplayer.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.adapter.MusicPlayerAdapter;
import com.example.musicplayer.model.Song;

import java.util.ArrayList;
import java.util.List;

public class ArtistFragment extends Fragment {

    private RecyclerView mArtistRecyclerView;

    public ArtistFragment() {

    }

    public static ArtistFragment newInstance() {
        ArtistFragment fragment = new ArtistFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_artist, container, false);

        findViews(view);
        initViews();
        setupAdapter();

        return view;
    }

    private void findViews(View view) {
        mArtistRecyclerView = view.findViewById(R.id.artist_recycler_view);
    }

    private void initViews() {
        mArtistRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupAdapter() {
        List<Song> artistList = new ArrayList<>();
        MusicPlayerAdapter adapter = new MusicPlayerAdapter(artistList, getContext());
        mArtistRecyclerView.setAdapter(adapter);
    }
}