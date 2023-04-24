package com.example.app_movie.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_movie.Home.TiViFragment;
import com.example.app_movie.Model.channel;
import com.example.app_movie.PLayVideo.PlayChannelActivity;
import com.example.app_movie.PLayVideo.PlayMovieActivity;
import com.example.app_movie.R;
import com.example.app_movie.Util.Server;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class channelAdapter extends  RecyclerView.Adapter<channelAdapter.channelViewHolder> {
    Context context ;
    ArrayList<channel> list ;

    public channelAdapter(Context context, ArrayList<channel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public channelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate
                (R.layout.item_channel, parent, false);
        return new channelViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull channelViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String link= Server.getThumbnailChannel+list.get(position).getThumbnailChannel();
        Picasso.get().load(link).into(holder.imgChannel);
        holder.imgChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayChannelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("linkChannel",list.get(position).getLinkChannel());
                bundle.putString("nameChannel",list.get(position).getNameChannel());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class channelViewHolder extends RecyclerView.ViewHolder {
       public ImageView imgChannel;

        public channelViewHolder(@NonNull View itemView) {
            super(itemView);
            imgChannel=itemView.findViewById(R.id.imgChannel);

        }
    }
}
