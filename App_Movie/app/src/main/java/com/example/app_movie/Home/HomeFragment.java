package com.example.app_movie.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_movie.Adapter.SliderAdapter;
import com.example.app_movie.Adapter.movieAdapter;
import com.example.app_movie.Model.movie;
import com.example.app_movie.R;
import com.example.app_movie.Util.Server;
import com.google.android.material.slider.Slider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    public RecyclerView ly_thinhHanh;
    public ArrayList<movie> listMovie;
    public movieAdapter adapter;
    public ViewPager viewPager ;
    public SliderAdapter sliderAdapter ;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
        init();
        getData();
        return view;
    }

    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String urlGetMovie = Server.getMovie;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlGetMovie, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null) {
                            listMovie.clear();
                            for (int i = 0; i <= response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    int idMovie = jsonObject.getInt("idMovie");
                                    String nameMovie = jsonObject.getString("nameMovie");
                                    String thumbnailMovie = jsonObject.getString("thumbnailMovie");
                                    String coverMovie = jsonObject.getString("coverMovie");
                                    String contentMoive = jsonObject.getString("contentMoive");
                                    String categoryMovie = jsonObject.getString("categoryMovie");
                                    String yearMovie = jsonObject.getString("yearMovie");
                                    String director = jsonObject.getString("director");
                                    int view = jsonObject.getInt("view");
                                    movie mv=new movie(idMovie,nameMovie,thumbnailMovie,coverMovie,contentMoive,categoryMovie,yearMovie,director,view);
                                    listMovie.add(mv);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                               adapter.notifyDataSetChanged();
                             sliderAdapter.notifyDataSetChanged();
                            }

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );
        requestQueue.add(jsonArrayRequest);
    }

    private void init() {
        ly_thinhHanh = view.findViewById(R.id.ly_thinhHanh);
        listMovie = new ArrayList<>();
        adapter = new movieAdapter(getContext(), listMovie);
        ly_thinhHanh.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        ly_thinhHanh.setLayoutManager(layoutManager);


        // phần pager
        viewPager = view.findViewById(R.id.viewPager);
        sliderAdapter = new SliderAdapter(getContext(), listMovie);
        viewPager.setAdapter(sliderAdapter);
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPadding(100, 0, 120, 0);

    }
}