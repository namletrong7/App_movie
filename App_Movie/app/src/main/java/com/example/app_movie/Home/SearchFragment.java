package com.example.app_movie.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_movie.Adapter.historySearchAdapter;
import com.example.app_movie.Adapter.movieAdapter;
import com.example.app_movie.Adapter.movie_search_Adapter;
import com.example.app_movie.Model.historySearch;
import com.example.app_movie.Model.movie;
import com.example.app_movie.R;
import com.example.app_movie.Util.Server;
import com.example.app_movie.databinding.FragmentSearchBinding;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SearchFragment extends Fragment {
    ArrayList<movie> listMovie;
    movie_search_Adapter adapterMovie;
    View view;
    public static EditText edtNameMovie;
    RecyclerView ry_movie, ry_historySearch;
    LinearLayout ly_no_result;
    ArrayList<historySearch> listHistory;
    historySearchAdapter adapterHistory;
    String phoneNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        phoneNumber = HomeActivity.phoneNumber;
        init();
        event();
        getHistorySearch();
        return view;
    }

    private void init() {
        ly_no_result = view.findViewById(R.id.ly_no_result);
        ly_no_result.setVisibility(View.GONE);
        edtNameMovie = view.findViewById(R.id.edtNameMovie);
        ry_movie = view.findViewById(R.id.ry_movie);
        // xét phần kết quả tìm kiếm
        listMovie = new ArrayList<>();
        adapterMovie = new movie_search_Adapter(getContext(), listMovie);
        ry_movie.setAdapter(adapterMovie);
        ry_movie.setLayoutManager(new GridLayoutManager(getContext(), 2));
        // phần lịch sử tìm kiếm
        listHistory = new ArrayList<>();
        adapterHistory = new historySearchAdapter(getContext(), listHistory);
        ry_historySearch = view.findViewById(R.id.ry_historySearch);
        ry_historySearch.setAdapter(adapterHistory);
        ry_historySearch.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    public void event() {
        view.findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMovie(edtNameMovie.getText().toString());
                addHistorySearch();
            }
        });
        edtNameMovie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listMovie.clear();
                searchMovie(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void getHistorySearch() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.getHistorySearch,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listHistory.clear();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                historySearch history = new historySearch();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                history.setContentSearch(jsonObject.getString("content"));
                                history.setPhoneNumber(phoneNumber);
                                listHistory.add(history);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapterHistory.notifyDataSetChanged();

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

    public void addHistorySearch() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.addHistorySearch,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("1")) {
                            getHistorySearch();
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
                params.put("contentSearch", edtNameMovie.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void searchMovie(String nameMovie) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.getMovieByName,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listMovie.clear();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            if (jsonArray.length() > 0) {
                                ly_no_result.setVisibility(View.GONE);

                            } else {
                                ly_no_result.setVisibility(View.VISIBLE);
                            }
                            for (int i = 0; i < jsonArray.length(); i++) {
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
                        adapterMovie.notifyDataSetChanged();

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
                params.put("nameMovie", nameMovie);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


}