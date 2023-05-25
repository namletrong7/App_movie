package com.example.app_movie.Personal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_movie.Adapter.favoriteMovieAdapter;
import com.example.app_movie.Home.HomeActivity;
import com.example.app_movie.Model.favoriteMovie;
import com.example.app_movie.R;
import com.example.app_movie.Util.CheckNetwork;
import com.example.app_movie.Util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListFavoriteActivity extends AppCompatActivity {
  private ArrayList<favoriteMovie> list  ;
  private RecyclerView ry_listFavorite ;
  private favoriteMovieAdapter  favoriteAdapter ;
  public String phoneNumber ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_favorite);
        phoneNumber= HomeActivity.phoneNumber;
        init();
        getListFavoriteMovie();
        event();
    }
    public void event(){
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
        });
    }
    public void init(){
        list=new ArrayList<>();
        ry_listFavorite=findViewById(R.id.ry_listFavorite);
        favoriteAdapter=new favoriteMovieAdapter(this, list);
        ry_listFavorite.setAdapter(favoriteAdapter);
        ry_listFavorite.setLayoutManager(new GridLayoutManager(this, 3));
    }
    public void getListFavoriteMovie(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String linkFavoriteMovie = Server.getFavoriteMovie;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, linkFavoriteMovie,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       if(response!=null){
                           list.clear();
                           try {
                               JSONArray jsonArray=new JSONArray(response);
                               for(int i = 0 ; i<jsonArray.length();i++){
                                   JSONObject jsonObject= jsonArray.getJSONObject(i);
                                   int idFavorite= jsonObject.getInt("idFavorite");
                                   int idMovie= jsonObject.getInt("idMovie");
                                   String phoneNumber=jsonObject.getString("phoneNumber");
                                   String thumbnail=jsonObject.getString("thumbnailMovie");
                                   list.add(new favoriteMovie(idFavorite,idMovie,phoneNumber,thumbnail));
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


        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("phoneNumber", phoneNumber);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    CheckNetwork checkNetWork = new CheckNetwork();

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(checkNetWork,filter);
        super.onStart();
    }
}