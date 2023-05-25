package com.example.app_movie.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_movie.Home.DetailMovieActivity;
import com.example.app_movie.Model.categoryMovie;
import com.example.app_movie.Model.movie;
import com.example.app_movie.R;
import com.example.app_movie.Util.Server;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParentMovieAdapter extends RecyclerView.Adapter<ParentMovieAdapter.movieViewHolder> {
    Context context;
   ArrayList<categoryMovie> listCategory ;
   //public  movieAdapter adapterMovie  ;
    public ParentMovieAdapter(Context context, ArrayList<categoryMovie> listCategory) {
        this.context = context;
        this.listCategory= listCategory ;
    }


    @NonNull
    @Override
    public ParentMovieAdapter.movieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate
                (R.layout.item_parent_movie, parent, false);
        return new movieViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ParentMovieAdapter.movieViewHolder holder, @SuppressLint("RecyclerView") int position) {
      String category= listCategory.get(position).getCategory();
      ArrayList<movie> listMovie = listCategory.get(position).getListMovie();
      holder.tvCategory.setText(category);
      movieAdapter adapterMovie = new movieAdapter(context, listMovie);
      holder.ry_movie.setAdapter(adapterMovie);
      holder.ry_movie.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

    }

    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    public class movieViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCategory;
        public RecyclerView ry_movie;

        public movieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tvCategroyMovie);
            ry_movie = itemView.findViewById(R.id.ry_movie);

        }
    }

//    public void getMovie(String category) {
//        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.getMovieByCategory,
//                response -> {
//                    if (response != null) {
//
//                        list.clear();
//                        try {
//                            JSONArray jsonArray = new JSONArray(response);
//                            for (int i = 0; i <= jsonArray.length(); i++) {
//                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                int idMovie = jsonObject.getInt("idMovie");
//                                String nameMovie = jsonObject.getString("nameMovie");
//                                String thumbnailMovie = jsonObject.getString("thumbnailMovie");
//                                String coverMovie = jsonObject.getString("coverMovie");
//                                String contentMoive = jsonObject.getString("contentMoive");
//                                String categoryMovie = jsonObject.getString("categoryMovie");
//                                String yearMovie = jsonObject.getString("yearMovie");
//                                String director = jsonObject.getString("director");
//                                int view = jsonObject.getInt("view");
//                                movie mv = new movie(idMovie, nameMovie, thumbnailMovie, coverMovie, contentMoive, categoryMovie, yearMovie, director, view);
//                                list.add(mv);
//                            }
//                            adapterMovie.notifyDataSetChanged();
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                error -> {
//
//                }
//
//        ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> params = new HashMap<>();
//                params.put("categoryMovie",category );
//                return params;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }

}