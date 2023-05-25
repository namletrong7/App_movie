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
import com.example.app_movie.Model.movie;
import com.example.app_movie.R;
import com.example.app_movie.Util.Server;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class movie_search_Adapter extends  RecyclerView.Adapter<movie_search_Adapter.movieViewHolder> {
    Context context ;
    ArrayList<movie> list ;

    public movie_search_Adapter(Context context, ArrayList<movie> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public movieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate
                (R.layout.item_movie_search, parent, false);
        return new movieViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull movieViewHolder holder, @SuppressLint("RecyclerView") int position) {
        movie Movie=list.get(position);
        int id =Movie.getIdMovie();
        String linkCover= Server.getCover+Movie.getCoverMovie();
        Picasso.get().load(linkCover).into(holder.coverMovie);
        holder.coverMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMovieActivity.class);
                Bundle bundle = new Bundle();
                int id = list.get(position).getIdMovie();
                String cate =list.get(position).getCategoryMovie();
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

    public class movieViewHolder extends RecyclerView.ViewHolder {
       public ImageView coverMovie ;

        public movieViewHolder(@NonNull View itemView) {
            super(itemView);
            coverMovie=itemView.findViewById(R.id.coverMoive);

        }
    }

}
