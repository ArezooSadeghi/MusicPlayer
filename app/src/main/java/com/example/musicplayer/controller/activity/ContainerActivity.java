package com.example.musicplayer.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.musicplayer.R;
import com.example.musicplayer.controller.fragment.PlaybackPageFragment;

import java.util.UUID;

public class ContainerActivity extends AppCompatActivity {

    public static final String EXTRA_SONG_ID = "com.example.musicplayer.songId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        UUID songId = (UUID) getIntent().getSerializableExtra(EXTRA_SONG_ID);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, PlaybackPageFragment.newInstance(songId))
                    .commit();
        }
    }

    public static Intent newIntent(Context context, UUID songId) {
        Intent intent = new Intent(context, ContainerActivity.class);
        intent.putExtra(EXTRA_SONG_ID, songId);
        return intent;
    }
}