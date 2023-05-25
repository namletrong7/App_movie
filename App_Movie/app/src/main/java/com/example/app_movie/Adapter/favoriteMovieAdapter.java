package com.example.app_movie.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_movie.Home.DetailMovieActivity;
import com.example.app_movie.Model.favoriteMovie;
import com.example.app_movie.Model.movie;
import com.example.app_movie.R;
import com.example.app_movie.Util.Server;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class favoriteMovieAdapter  extends  RecyclerView.Adapter<favoriteMovieAdapter.favoriteMovieViewHolder> {
    Context context ;
    ArrayList<favoriteMovie> list ;

    public favoriteMovieAdapter(Context context, ArrayList<favoriteMovie> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public favoriteMovieAdapter.favoriteMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate
                (R.layout.item_favorite_movie, parent, false);
        return new favoriteMovieAdapter.favoriteMovieViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull favoriteMovieAdapter.favoriteMovieViewHolder holder, @SuppressLint("RecyclerView") int position) {
        favoriteMovie Movie=list.get(position);
        String linkThumbnail=Server.getThumbnail+Movie.getThumbnailMovie();
        Picasso.get().load(linkThumbnail).into(holder.imgThumbnail);
        holder.imgThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMovieActivity.class);
                Bundle bundle = new Bundle();
                int id = list.get(position).getIdMovie();
                bundle.putInt("id",id);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class favoriteMovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgThumbnail ;

        public favoriteMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThumbnail=itemView.findViewById(R.id.imgThumbnailMovie);

        }
    }
}
