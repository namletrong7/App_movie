package com.example.app_movie.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_movie.Home.TiViFragment;
import com.example.app_movie.R;

import java.util.ArrayList;

public class TvAdapter extends  RecyclerView.Adapter<TvAdapter.TVViewHolder> {
    Context context ;
    ArrayList<String> list ;

    public TvAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public TVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate
                (R.layout.item_category_tv, parent, false);
        return new TVViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull TVViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String channel=list.get(position);
        holder.tvChannel.setText(channel);
        holder.tvChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( holder.tvChannel.getText().equals("VTV")){
                    TiViFragment.ry_AllChannel.setVisibility(View.GONE);
                    TiViFragment.ry_channel.setVisibility(View.VISIBLE);
                    TiViFragment.categoryChannel="vtv";
                    TiViFragment.setTypeChannel();
                    TiViFragment.adapterChannel.notifyDataSetChanged();
                }
                if( holder.tvChannel.getText().equals("HTV")){
                    TiViFragment.ry_AllChannel.setVisibility(View.GONE);
                    TiViFragment.ry_channel.setVisibility(View.VISIBLE);
                    TiViFragment.categoryChannel="htv";
                    TiViFragment.setTypeChannel();
                    TiViFragment.adapterChannel.notifyDataSetChanged();
                }
                if( holder.tvChannel.getText().equals("VTC")){
                    TiViFragment.ry_AllChannel.setVisibility(View.GONE);
                    TiViFragment.ry_channel.setVisibility(View.VISIBLE);
                    TiViFragment.categoryChannel="vtc";
                    TiViFragment.setTypeChannel();
                    TiViFragment.adapterChannel.notifyDataSetChanged();
                }
                if( holder.tvChannel.getText().equals("THVL")){
                    TiViFragment.ry_AllChannel.setVisibility(View.GONE);
                    TiViFragment.ry_channel.setVisibility(View.VISIBLE);
                    TiViFragment.categoryChannel="thvl";
                    TiViFragment.setTypeChannel();
                    TiViFragment.adapterChannel.notifyDataSetChanged();
                }
                if( holder.tvChannel.getText().equals("Tất cả các kênh")){
                    TiViFragment.ry_AllChannel.setVisibility(View.VISIBLE);
                    TiViFragment.ry_channel.setVisibility(View.GONE);
                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TVViewHolder extends RecyclerView.ViewHolder {
       public TextView tvChannel;

        public TVViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChannel=itemView.findViewById(R.id.tvCategory_tv);

        }
    }
}
