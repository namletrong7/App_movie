package com.example.app_movie.PLayVideo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.app_movie.Adapter.videoAdapter;
import com.example.app_movie.Model.video;
import com.example.app_movie.R;

import java.util.ArrayList;

public class PLayShortVideo extends AppCompatActivity {
  ViewPager2 viewPager ;
  ArrayList<video> listVideo ;
  videoAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_short_video);

        init();
    }
    public void init(){
        listVideo=new ArrayList<>();
        viewPager=findViewById(R.id.viewpager);
        adapter=new videoAdapter(this, listVideo);
        viewPager.setAdapter(adapter);

        int idVideo ;
        String phoneNumber, avatar , linkVideo, contentVideo ;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        viewPager.setLayoutManager(layoutManager);
        listVideo.add(new video(0,"","avatar.jpg","video.mp4","nafy thi an nay"));
        listVideo.add(new video(0,"","avatar.jpg","nam.mp4","nafy thi an nay"));
        listVideo.add(new video(0,"","avatar.jpg","video.mp4","nafy thi an nay"));
        listVideo.add(new video(0,"","avatar.jpg","nam.mp4","nafy thi an nay"));
        listVideo.add(new video(0,"","avatar.jpg","video.mp4","nafy thi an nay"));
        adapter.notifyDataSetChanged();
    }
}