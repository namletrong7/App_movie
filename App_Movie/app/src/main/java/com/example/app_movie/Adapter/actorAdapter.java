package com.example.app_movie.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_movie.Model.actor;
import com.example.app_movie.Model.comment;
import com.example.app_movie.R;
import com.example.app_movie.Util.Server;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class actorAdapter extends  RecyclerView.Adapter<actorAdapter.actorViewHolder> {
    Context context ;
    ArrayList<actor> list ;

    public actorAdapter(Context context, ArrayList<actor> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public actorAdapter.actorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate
                (R.layout.item_actor, parent, false);
        return new  actorViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull actorAdapter.actorViewHolder holder, @SuppressLint("RecyclerView") int position) {
          actor Actor = list.get(position);
          String linkAvatarActor =Server.getAvatarActor+Actor.getAvatarActor();
          Picasso.get().load(linkAvatarActor).into(holder.imgActor);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class actorViewHolder extends RecyclerView.ViewHolder {
        public RoundedImageView imgActor ;
        public actorViewHolder(@NonNull View itemView) {
            super(itemView);
            imgActor=itemView.findViewById(R.id.imgActor);
        }
    }
}
