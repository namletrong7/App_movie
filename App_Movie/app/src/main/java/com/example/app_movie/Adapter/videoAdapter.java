package com.example.app_movie.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_movie.Home.DetailMovieActivity;
import com.example.app_movie.Model.movie;
import com.example.app_movie.Model.video;
import com.example.app_movie.R;
import com.example.app_movie.Util.Server;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class videoAdapter extends  RecyclerView.Adapter<videoAdapter.videoHolder> {
    Context context ;
    ArrayList<video> list ;



    public videoAdapter(Context context, ArrayList<video> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public videoAdapter.videoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate
                (R.layout.item_video, parent, false);
        return new videoHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull videoAdapter.videoHolder holder, @SuppressLint("RecyclerView") int position) {
         video Video = list.get(position);
         String linkAvatar=Server.getAvatar+Video.getAvatar();
        Picasso.get().load(linkAvatar).into(holder.imgAvatar);
        holder.tvContent.setText(Video.getContentVideo());
        String linkvideo = Server.getLinkVideo+list.get(position).getLinkVideo();
        holder.playView.setVideoPath(linkvideo);
        holder.playView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                holder.playView.start();
            }
        });
//        holder.playView.setUseController(true);
//
//
//
//
//        SimpleExoPlayer player = new SimpleExoPlayer.Builder(context.getApplicationContext()).build();
//        holder.playView.setPlayer(player);
//
//        DataSource.Factory dataSourceFactory = new DefaultHttpDataSourceFactory();
//        HlsMediaSource mediaSource = new HlsMediaSource.Factory(dataSourceFactory).
//                createMediaSource(MediaItem.fromUri(linkvideo));
//        player.setMediaSource(mediaSource);
//        player.prepare();
//   //     player.setPlayWhenReady(true);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class videoHolder extends RecyclerView.ViewHolder {
            public VideoView playView ;
            public TextView tvContent ;
            public CircleImageView imgAvatar ;
        public videoHolder(@NonNull View itemView) {
            super(itemView);
            playView=itemView.findViewById(R.id.playerVideo);
            tvContent=itemView.findViewById(R.id.tvContentVideo);  // tiêu đề video đó
            imgAvatar=itemView.findViewById(R.id.imgAvatar);   // hình đại diện của người đăng video đó
        }
    }
}

