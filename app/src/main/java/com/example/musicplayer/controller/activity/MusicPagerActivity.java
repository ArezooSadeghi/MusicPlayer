package com.example.musicplayer.controller.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.musicplayer.R;
import com.example.musicplayer.controller.fragment.AlbumFragment;
import com.example.musicplayer.controller.fragment.ArtistFragment;
import com.example.musicplayer.controller.fragment.SongFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MusicPagerActivity extends AppCompatActivity {

    private ViewPager2 mMusicPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicpager);
        findViews();
        initViews();

    }

    private void findViews() {
        mMusicPager = findViewById(R.id.music_player_view_pager);
        mTabLayout = findViewById(R.id.music_player_tab_layout);
    }

    private void initViews() {
        mMusicPager.setAdapter(new MusicPagerAdapter(this));
        TabLayoutMediator mediator = new TabLayoutMediator(
                mTabLayout, mMusicPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch (position) {
                            case 0:
                                tab.setText("Songs");
                                break;
                            case 1:
                                tab.setText("Artists");
                                break;
                            default:
                                tab.setText("Albums");
                                break;
                        }
                    }
                });
        mediator.attach();
    }

    private class MusicPagerAdapter extends FragmentStateAdapter {
        public static final int NUMBER_OF_PAGES = 3;

        public MusicPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return SongFragment.newInstance();
                case 1:
                    return ArtistFragment.newInstance();
                default:
                    return AlbumFragment.newInstance();
            }
        }

        @Override
        public int getItemCount() {
            return NUMBER_OF_PAGES;
        }
    }
}