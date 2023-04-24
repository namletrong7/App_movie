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
import com.example.app_movie.Model.comment;
import com.example.app_movie.Model.movie;
import com.example.app_movie.R;
import com.example.app_movie.Util.Server;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends  RecyclerView.Adapter<CommentAdapter.commentViewHolder> {
    Context context ;
    ArrayList<comment> list ;

    public CommentAdapter(Context context, ArrayList<comment> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public commentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate
                (R.layout.item_comment, parent, false);
        return new commentViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull commentViewHolder holder, @SuppressLint("RecyclerView") int position) {
        comment cm = list.get(position);
        String linkAvatar=Server.getAvatar+cm.getAvatar();
        Picasso.get().load(linkAvatar).into(holder.imgAvatar);
        holder.tvContent.setText(cm.getContentComment());
        char[] arraySDT = cm.getPhoneNumber().toCharArray();
        String phanDau = String.valueOf(arraySDT[0])+String.valueOf(arraySDT[1])+String.valueOf(arraySDT[2]);
        String phanCuoi = String.valueOf(arraySDT[7])+String.valueOf(arraySDT[8])+String.valueOf(arraySDT[9]);
        holder.tvPhone.setText(String.valueOf(phanDau+"***"+phanCuoi));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class commentViewHolder extends RecyclerView.ViewHolder {
       public CircleImageView imgAvatar ;
       public TextView tvPhone , tvContent ;
        public commentViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar=itemView.findViewById(R.id.imgAvatar);
            tvPhone=itemView.findViewById(R.id.tvPhone);
            tvContent=itemView.findViewById(R.id.tvContent);
        }
    }
}
