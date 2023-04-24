package com.example.app_movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_movie.Adapter.SliderAdapter;
import com.example.app_movie.Adapter.VPAdapter;
import com.example.app_movie.Adapter.movieAdapter;
import com.example.app_movie.Model.ViewPagerItem;
import com.example.app_movie.Model.movie;
import com.example.app_movie.Util.Server;
import com.google.android.material.transition.SlideDistanceProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import me.relex.circleindicator.CircleIndicator2;
import me.relex.circleindicator.CircleIndicator3;


public class TestActivity extends AppCompatActivity {
     ArrayList<movie> listMovie ;
     SliderAdapter adapter ;
     movieAdapter adapterMovie;
     ViewPager viewPager ;
     CircleIndicator ci ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        listMovie= new ArrayList<>();
        adapter=new SliderAdapter(this, listMovie);
        ci =findViewById(R.id.ci);
        viewPager=findViewById(R.id.viewpager);
        adapterMovie=new movieAdapter(TestActivity.this, listMovie);
         viewPager.setAdapter(adapter);
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPadding(100, 0, 100, 0);

        getData();
        ci.setViewPager(viewPager);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
//        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
//            @Override
//            public void transformPage(@NonNull View page, float position) {
//                float r = 1 - Math.abs(position);
//                page.setScaleY(0.85f + r * 0.15f);
//            }
//        });
    //    viewPager.setPageTransformer(compositePageTransformer);
    }
    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(TestActivity.this);
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
}