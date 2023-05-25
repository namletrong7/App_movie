package com.example.app_movie.PLayVideo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.app_movie.R;
import com.example.app_movie.Util.Server;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.ui.DefaultTimeBar;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.TimeBar;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Util;

import java.util.Formatter;
import java.util.Locale;

public class PlayMovieActivity extends AppCompatActivity {
    ImageView imgTang10, imgGiam10, imgPlay, imgfullScreen, imgSettingQuality;
    DefaultTimeBar sb_time;
    TextView tvCurrentTime, tvDurationTime,tvNameEpisode;
    Boolean flag = true;
    Boolean flagPlay = true;
    SimpleExoPlayer player;
    StringBuilder mFormatBuilder;
    Formatter mFormatter;
    PlayerView playerView;
    LottieAnimationView loading;
    Handler handler;
    private DefaultTrackSelector trackSelector;
    private boolean isShowingTrackSelectionDialog;
    DefaultLoadControl loadControl;
    SimpleCache simpleCache;
    String linkEpisode , nameEpisode ;
    int numberEpisode ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_movie);
        init();
        getEpisode();
        playVideo();
        setFullScreen();
        setTime();


    }

    public void init() {
        // cài đặt bộ đệm cho  load control
        int MIN_BUFFER_DURATION = 3000; // 3 seconds
        int MAX_BUFFER_DURATION = 8000; // 8 seconds
        int MIN_PLAYBACK_RESUME_BUFFER = 1500; // 1.5 seconds
        int MIN_PLAYBACK_START_BUFFER = 500; // 0.5 seconds
        LoadControl loadControl = new DefaultLoadControl.Builder().setAllocator(new DefaultAllocator(true, 16)).setBufferDurationsMs(MIN_BUFFER_DURATION, MAX_BUFFER_DURATION, MIN_PLAYBACK_START_BUFFER, MIN_PLAYBACK_RESUME_BUFFER).setTargetBufferBytes(-1).setPrioritizeTimeOverSizeThresholds(true).createDefaultLoadControl();


        imgSettingQuality = findViewById(R.id.imgSettingQuality);
        loading = findViewById(R.id.loading);
     //   loading.setColorFilter(ContextCompat.getColor(this, R.color.green_sang), android.graphics.PorterDuff.Mode.MULTIPLY);
        imgTang10 = findViewById(R.id.imgTang10);
        imgGiam10 = findViewById(R.id.imgGiam10);
        imgPlay = findViewById(R.id.imgPlay);
        imgPlay.setImageResource(R.drawable.icon_pause);
        imgfullScreen = findViewById(R.id.imgFullScreen);

  //      sb_time.setBufferedPosition(player.getBufferedPosition());
        sb_time = findViewById(R.id.sb_time);
        sb_time.setPlayedColor(Color.parseColor("#FFFFFF")); // Màu sắc phần đã phát
        sb_time.setScrubberColor(Color.parseColor("#FFFFFF")); // Màu sắc thanh kéo
        sb_time.setBufferedColor(Color.parseColor("#99FFCC")); // Màu sắc phần đã đệm
        sb_time.setAdMarkerColor(Color.parseColor("#808080")); // Màu s

        tvCurrentTime = findViewById(R.id.tvCurrentTime);
        tvDurationTime = findViewById(R.id.tvDurationTime);
        playerView = findViewById(R.id.playerView);
        player = new SimpleExoPlayer.Builder(this).setLoadControl(loadControl).build();
        playerView.setPlayer(player);
       tvNameEpisode= findViewById(R.id.tvNameEpisode);

    }
   public void getEpisode(){
       Intent intent = getIntent();
       Bundle bundle = intent.getExtras();
       // lấy link episode từ adapter episode gửi về
        linkEpisode = bundle.getString("linkEpisode");
        nameEpisode=bundle.getString("nameEpisode");
        numberEpisode=bundle.getInt("numberEpisode");
        tvNameEpisode.setText("Tập "+numberEpisode+": "+nameEpisode);
   }

    public void playVideo() {

        String path = Server.getLinkEpisode + linkEpisode;
        String userAgent = Util.getUserAgent(this, "yourApplicationName");
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, userAgent);
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(path));
        player.setMediaSource(mediaSource);
        player.prepare();
        player.play();

        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int state) {
                if (state == Player.STATE_READY) {
                    loading.setVisibility(View.GONE);
                    sb_time.setDuration(player.getDuration());
                } else if (state == Player.STATE_BUFFERING) {
                    loading.setVisibility(View.VISIBLE);
                } else {
                    loading.setVisibility(View.VISIBLE);
                }
                if (state == Player.STATE_ENDED) {
                    loading.setVisibility(View.GONE);
                    player.pause();
                }
            }
        });

        sb_time.setDuration(player.getDuration());   // lấy độ dài của video set cho thanh timebar
        sb_time.addListener(new TimeBar.OnScrubListener() {  // khi thanh timbar kéo tới đâu thì video load tới đó
            @Override
            public void onScrubStart(TimeBar timeBar, long position) {
                player.pause();
            }

            @Override
            public void onScrubMove(TimeBar timeBar, long position) {
                player.seekTo(position);
            }

            @Override
            public void onScrubStop(TimeBar timeBar, long position, boolean canceled) {
                player.play();
            }
        });
        // set cho video tạm dùng hoặc play khi nhấn nút
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  player.pause();
                if (flag == true) {  // đang phát video
                    player.pause(); // tạm dùng video
                    imgPlay.setImageResource(R.drawable.icon_play);
                    flag = false;
                } else {
                    player.play(); // tạm dùng video
                    player.setPlayWhenReady(true);
                    imgPlay.setImageResource(R.drawable.icon_pause);
                    flag = true;
                }
            }
        });
        // set tang hoặc giảm 10 giây cho video
        imgTang10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.seekTo(player.getCurrentPosition() + 10000); // hỗ tợ tăng video lên 10 giây
            }
        });
        imgGiam10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.seekTo(player.getCurrentPosition() - 10000);   // hỗ trợ giảm video 10 giây

            }
        });


    }
//
//    public void settingQuality() {
//        imgSettingQuality.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MappingTrackSelector.MappedTrackInfo mappedTrackInfo = trackSelector.getCurrentMappedTrackInfo();
//                if (mappedTrackInfo != null) {
//                    if (!isShowingTrackSelectionDialog && TrackSelectionDialog.willHaveContent(trackSelector)) {
//                        isShowingTrackSelectionDialog = true;
//                        TrackSelectionDialog trackSelectionDialog = TrackSelectionDialog.createForTrackSelector(trackSelector,/* onDismissListener= */ dismissedDialog -> isShowingTrackSelectionDialog = false);
//                        trackSelectionDialog.show(getSupportFragmentManager(), /* tag= */ null);
//                    }
//                }
//            }
//        });
//    }

    private void setTime() {  // láy thời gian thực tại và độ dài của video
        handler = new Handler();
        //Make sure you update Seekbar on UI thread
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (player != null) {
                    String currentTime = stringForTime((int) player.getCurrentPosition());
                    String durationTime = stringForTime((int) player.getDuration());
                    sb_time.setPosition(player.getCurrentPosition());  // cập nhập thanh timbar chạy theo độ dài của video
                    tvCurrentTime.setText(currentTime);
                    tvDurationTime.setText(durationTime);
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }

    private String stringForTime(int timeMs) {  /// chuyển dỏi thời gian sang dnajg giờ phúc giây
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
        int totalSeconds = timeMs / 1000;

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    public void setFullScreen() {// thực hiện set màn hình ra nằm ngang
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        imgfullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == true) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    flag = false;
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    flag = true;
                }

            }
        });
    }

    @Override
    protected void onPause() {
        player.pause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        player.release();
        super.onDestroy();
    }
}