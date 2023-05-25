package com.example.app_movie.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_movie.Home.DetailMovieActivity;
import com.example.app_movie.Home.SearchFragment;
import com.example.app_movie.Model.historySearch;
import com.example.app_movie.Model.movie;
import com.example.app_movie.R;
import com.example.app_movie.Util.Server;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class historySearchAdapter extends RecyclerView.Adapter<historySearchAdapter.historySearchHolder> {
    Context context;
    ArrayList<historySearch> list;

    public historySearchAdapter(Context context, ArrayList<historySearch> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public historySearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate
                (R.layout.item_history_search, parent, false);
        return new historySearchHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull historySearchHolder holder, @SuppressLint("RecyclerView") int position) {
        historySearch obj = list.get(position);
        holder.tvContentSearch.setText(obj.getContentSearch());
        holder.tvContentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragment.edtNameMovie.setText(holder.tvContentSearch.getText().toString().trim());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class historySearchHolder extends RecyclerView.ViewHolder {

              TextView tvContentSearch ;
        public historySearchHolder(@NonNull View itemView) {
            super(itemView);
            tvContentSearch=itemView.findViewById(R.id.tvContentSearch);

        }
    }
}

