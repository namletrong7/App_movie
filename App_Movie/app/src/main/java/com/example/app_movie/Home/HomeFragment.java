package com.example.app_movie.Home;

import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_movie.Adapter.ParentMovieAdapter;
import com.example.app_movie.Adapter.SliderAdapter;
import com.example.app_movie.Adapter.channelAdapter;
import com.example.app_movie.Adapter.favoriteMovieAdapter;
import com.example.app_movie.Adapter.movieAdapter;
import com.example.app_movie.Model.categoryMovie;
import com.example.app_movie.Model.channel;
import com.example.app_movie.Model.favoriteMovie;
import com.example.app_movie.Model.movie;
import com.example.app_movie.R;
import com.example.app_movie.Util.Server;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import me.relex.circleindicator.CircleIndicator3;


public class HomeFragment extends Fragment {
    ImageView btn;
    public RecyclerView ly_thinhHanh, ry_channel, ry_categoryMovie, ry_favoriteMovie;
    private ArrayList<favoriteMovie> listFavorite;
    public ArrayList<movie> listMovieThinhHanh;
    public ArrayList<String> listCategory;
    public ArrayList<categoryMovie> listCategoryMovie;
    public ArrayList<channel> listChannel;
    public channelAdapter adapterChannel;
    public movieAdapter adapter;
    private favoriteMovieAdapter favoriteAdapter;
    public ViewPager2 viewPager;
    ImageView imgBackground;
    public ParentMovieAdapter adapterParent;
    public SliderAdapter sliderAdapter;
    public CircleIndicator3 indicator;
    View view;
    String phoneNumber, checkLogIn = "1";

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        phoneNumber = HomeActivity.phoneNumber;
        init();
        getCategory();
        getMovie();
        getListFavoriteMovie();
        new MyAsyncTask().execute("");
        getDataChannel();
        return view;
    }

    public void getCategory() {  // lấy danh sách thể loại phim
//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.getCategoryMovie, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        if (response != null) {
//                            listCategory.clear();
//                            for (int i = 0; i <= response.length(); i++) {
//                                try {
//                                    JSONObject jsonObject = response.getJSONObject(i);
//                                    String categoryMovie = jsonObject.getString("categoryMovie");
//                                    listCategory.add(categoryMovie);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }
//
//        );
//        requestQueue.add(jsonArrayRequest);
        listCategory.add("Hanh dong");
        listCategory.add("Tinh cam");
        listCategory.add("Phim bo");
        listCategory.add("Giai tri");
        listCategory.add("Vien tuong");
        listCategory.add("Khoa hoc");
        listCategory.add("Kinh di");
        listCategory.add("Anime");
        // sau khi lấy danh sách thể loại phim xong ta thực hiện lấy danh sách phim theo từng thể loại đó


    }

    public void getMovie() {
        listCategoryMovie.clear();
        for (int i = 0; i < listCategory.size(); i++) {
            ArrayList<movie> listMovie = new ArrayList<>();
            getMovieByCategory(listCategory.get(i).toString(), listMovie);
            listCategoryMovie.add(new categoryMovie(listCategory.get(i).toString(), listMovie));
            Log.d("KMA", listMovie.size() + " mam");
            adapterParent.notifyDataSetChanged();

        }


    }

    public void getMovieByCategory(String category, ArrayList<movie> listMovie) {  // lấy danh sách phim theo từng thể loại phim
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.getMovieByCategory,
                response -> {
                    if (response != null) {

                        listMovie.clear();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i <= jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
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
                                movie mv = new movie();
                                Gson gson = new Gson();
                                mv = gson.fromJson(jsonObject.toString(), movie.class);
                                listMovie.add(mv);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                error -> {

                }

        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("categoryMovie", category);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void getMovieThinhHanh() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String urlGetMovie = Server.getMovieThinhHanh;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlGetMovie, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null) {
                            listMovieThinhHanh.clear();
                            for (int i = 0; i <= response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
//                                    int idMovie = jsonObject.getInt("idMovie");
//                                    String nameMovie = jsonObject.getString("nameMovie");
//                                    String thumbnailMovie = jsonObject.getString("thumbnailMovie");
//                                    String coverMovie = jsonObject.getString("coverMovie");
//                                    String contentMoive = jsonObject.getString("contentMoive");
//                                    String categoryMovie = jsonObject.getString("categoryMovie");
//                                    String yearMovie = jsonObject.getString("yearMovie");
//                                    String director = jsonObject.getString("director");
//                                    int view = jsonObject.getInt("view");
//                                    movie mv = new movie(idMovie, nameMovie, thumbnailMovie, coverMovie, contentMoive, categoryMovie, yearMovie, director, view);
                                     movie mv = new movie();
                                      Gson gson = new Gson();
                                     mv = gson.fromJson(jsonObject.toString(), movie.class);
                                    listMovieThinhHanh.add(mv);
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

    private class MyAsyncTask extends AsyncTask<String, Integer, String> {
        private boolean mIsRunning = true;

        @Override
        protected String doInBackground(String... strings) {
            getMovieThinhHanh();
            getCategory();  // lấy danh sách thể loại phim
            //      getMovie();
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }

    public void getDataChannel() {
        String url = Server.getChannel;
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listChannel.clear();
                        if (response != null) {
                            for (int i = 0; i <= response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
//                                    int id = jsonObject.getInt("idChannel");
//                                    String nameChannel = jsonObject.getString("nameChannel");
//                                    String thumbnailChannel = jsonObject.getString("thumbnailChannel");
//                                    String linkChannel = jsonObject.getString("linkChannel");
//                                    String typeChannel = jsonObject.getString("typeChannel");
//                                    String contentChannel = jsonObject.getString("contentChannel");
//                                    channel Channel = new channel(id, nameChannel, thumbnailChannel, linkChannel, typeChannel, contentChannel);
                                    channel Channel = new channel();
                                    Gson gson = new Gson();
                                    Channel = gson.fromJson(jsonObject.toString(), channel.class);
                                    listChannel.add(Channel);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                            adapterChannel.notifyDataSetChanged();
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

    public void getListFavoriteMovie() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String linkFavoriteMovie = Server.getFavoriteMovie;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, linkFavoriteMovie,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            listFavorite.clear();
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                    int idFavorite = jsonObject.getInt("idFavorite");
//                                    int idMovie = jsonObject.getInt("idMovie");
//                                    String phoneNumber = jsonObject.getString("phoneNumber");
//                                    String thumbnail = jsonObject.getString("thumbnailMovie");
                                    favoriteMovie mv = new favoriteMovie();
                                    Gson gson = new Gson();
                                    mv = gson.fromJson(jsonObject.toString(), favoriteMovie.class);
                                    listFavorite.add(mv);
                                }
                                favoriteAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }


        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("phoneNumber", phoneNumber);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void init() {
        imgBackground = view.findViewById(R.id.imgBackground);

        btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //      getMovie();
                checkLogIn = "0";
                ///  Toast.makeText(getContext(),listCategory.get(3).toString(), Toast.LENGTH_SHORT).show();
            }
        });
        // phim thịnh hành  ;
        ly_thinhHanh = view.findViewById(R.id.ry_thinhHanh);
        listMovieThinhHanh = new ArrayList<>();
        adapter = new movieAdapter(getContext(), listMovieThinhHanh);
        ly_thinhHanh.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        ly_thinhHanh.setLayoutManager(layoutManager);
//
        // phần kênh truyền hình
        ry_channel = view.findViewById(R.id.ry_channel);
        listChannel = new ArrayList<>();
        adapterChannel = new channelAdapter(getContext(), listChannel);
        ry_channel.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ry_channel.setAdapter(adapterChannel);


        // phần pager
        viewPager = view.findViewById(R.id.viewPager);
        sliderAdapter = new SliderAdapter(getContext(), listMovieThinhHanh);
        viewPager.setAdapter(sliderAdapter);
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPadding(170, 0, 170, 0);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(0));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.9f + r * 0.15f);
            }
        });
        viewPager.setPageTransformer(compositePageTransformer);

        indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        sliderAdapter.registerAdapterDataObserver(indicator.getAdapterDataObserver());

        //     hoạt động khi vuốt tới item nào thực hiện 1 hành động nào đó
        ViewPager2.OnPageChangeCallback onPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                String thumbnailMovie = listMovieThinhHanh.get(position).getThumbnailMovie();
                String linkThumbnail = Server.getThumbnail + thumbnailMovie;
                Picasso.get().load(linkThumbnail).into(imgBackground);
            }
        };

        viewPager.registerOnPageChangeCallback(onPageChangeCallback);
        // phần danh sách phim của tôi
        listFavorite = new ArrayList<>();
        ry_favoriteMovie = view.findViewById(R.id.ry_favoriteMovie);
        favoriteAdapter = new favoriteMovieAdapter(getContext(), listFavorite);
        ry_favoriteMovie.setAdapter(favoriteAdapter);
        ry_favoriteMovie.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        // phần cho từng danh mục phim
        ry_categoryMovie = view.findViewById(R.id.ry_categoryMovie);
        listCategoryMovie = new ArrayList<>();
        listCategory = new ArrayList<>();
        adapterParent = new ParentMovieAdapter(getContext(), listCategoryMovie);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        ry_categoryMovie.setLayoutManager(layoutManager4);
        ry_categoryMovie.setAdapter(adapterParent);
    }

    @Override
    public void onResume() {
        super.onResume();
        getListFavoriteMovie();

    }

    @Override
    public void onStart() {
        getMovie();
        super.onStart();
    }
}