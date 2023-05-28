package com.example.app_movie.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_movie.PLayVideo.PlayMovieActivity;
import com.example.app_movie.Model.episode;
import com.example.app_movie.R;
import com.example.app_movie.Util.Server;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.episodeViewHolder> {
    Context context;
    ArrayList<episode> list;

    public EpisodeAdapter(Context context, ArrayList<episode> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public episodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate
                (R.layout.item_episode, parent, false);
        return new episodeViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull episodeViewHolder holder, @SuppressLint("RecyclerView") int position) {
           episode Episode = list.get(position);
           String linkEpisode= list.get(position).getLinkEpisode();
           holder.tvNumberEpisode.setText("Tập "+Episode.getNumberEpisode()+". ");
           holder.tvNameEpisode.setText(Episode.getNameEpisode());
         String linkThumbnail= Server.getThumbnailEpisode+ Episode.getThumbnailEpisode();
         Picasso.get().load(linkThumbnail).placeholder(R.drawable.ic_loading).into(holder.imgThumnailEpisode);
          holder.imgThumnailEpisode.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(context, PlayMovieActivity.class);
                  Bundle bundle = new Bundle();
                  bundle.putString("linkEpisode",linkEpisode);
                  bundle.putString("nameEpisode",Episode.getNameEpisode());
                  bundle.putInt("numberEpisode",Episode.getNumberEpisode());
                  intent.putExtras(bundle);
                  context.startActivity(intent);
              }
          });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class episodeViewHolder extends RecyclerView.ViewHolder {
       public RoundedImageView imgThumnailEpisode ;
       public TextView tvNumberEpisode , tvNameEpisode ;

       public episodeViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThumnailEpisode = itemView.findViewById(R.id.imgThumbnailEpisode);
            tvNumberEpisode = itemView.findViewById(R.id.tvNumberEpisode);
            tvNameEpisode = itemView.findViewById(R.id.tvNameEpisode);

        }
    }

}

