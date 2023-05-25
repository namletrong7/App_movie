package com.example.app_movie.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.app_movie.Home.DetailMovieActivity;
import com.example.app_movie.Model.movie;
import com.example.app_movie.R;
import com.example.app_movie.Util.Server;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends  RecyclerView.Adapter<SliderAdapter.movieViewHolder> {
    Context context ;
    ArrayList<movie> list ;

    public SliderAdapter(Context context, ArrayList<movie> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public SliderAdapter.movieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate
                (R.layout.item_slider, parent, false);
        return new SliderAdapter.movieViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.movieViewHolder holder, @SuppressLint("RecyclerView") int position) {
        movie Movie=list.get(position);
        int id =Movie.getIdMovie();
        String linkThumbnail= Server.getThumbnail+Movie.getThumbnailMovie();
        Picasso.get().load(linkThumbnail).into(holder.imgThumbnail);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class movieViewHolder extends RecyclerView.ViewHolder {
        public RoundedImageView imgThumbnail ;

        public movieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThumbnail=itemView.findViewById(R.id.imgThumbnailMovie);

        }
    }
}