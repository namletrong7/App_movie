package com.example.app_movie.PLayVideo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.app_movie.R;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.Collections;

public class PlayChannelActivity extends AppCompatActivity {
    TextView tvNameChannel;
    SimpleExoPlayer player;
    PlayerView playerView;
    LottieAnimationView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_channel);
        init();
        playChannel();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void init() {
        loading = findViewById(R.id.loading);
        tvNameChannel = findViewById(R.id.tvNameChannel);
        playerView = findViewById(R.id.playerView);
        playerView.setUseController(false);
        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setPlayWhenReady(false);
                finish();
            }
        });
    }

    public void playChannel() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
       String linkChannel = bundle.getString("linkChannel");
    //    String linkChannel="https://sgn-fpt-002-ca204.vieon.vn/afd366e9d57f0e2d030e573864ff1d0d/1682313764390/ott-vod-2022/vod/2023/04/06/95d7b8a1-b70d-4bc2-999f-12a377d4eb38/media-audio-tg-mp4a.mp4";
        String nameChannel= bundle.getString("nameChannel");
        tvNameChannel.setText("Bạn đang xem kênh truyền hình: "+nameChannel);

        SimpleExoPlayer player = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(player);

        DataSource.Factory dataSourceFactory = new DefaultHttpDataSourceFactory();
        HlsMediaSource mediaSource = new HlsMediaSource.Factory(dataSourceFactory).
                createMediaSource(MediaItem.fromUri(linkChannel));
        player.setMediaSource(mediaSource);
        player.prepare();
        player.setPlayWhenReady(true);

        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int state) {
                if (state == Player.STATE_READY) {
                    loading.setVisibility(View.GONE);
                    player.setPlayWhenReady(true);
                } else if (state == Player.STATE_BUFFERING) {
                    loading.setVisibility(View.VISIBLE);
                } else {
                    loading.setVisibility(View.GONE);
                    player.setPlayWhenReady(true);

                }
            }
        });

    }
    protected void releasePlayer() {
        if (player != null) {

            player.release();
            player = null;
            playerView.setPlayer(/* player= */ null);
        }

        playerView.getAdViewGroup().removeAllViews();

    }


}