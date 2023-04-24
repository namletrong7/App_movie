package com.example.app_movie.Home;

import static com.example.app_movie.Util.Server.getDevice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_movie.Adapter.CommentAdapter;
import com.example.app_movie.Adapter.EpisodeAdapter;
import com.example.app_movie.Adapter.movieAdapter;
import com.example.app_movie.Model.comment;
import com.example.app_movie.Model.episode;
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
import java.util.Map;

public class DetailMovieActivity extends AppCompatActivity {
    public movie Movie;
    public ImageView imgCover;
    public RoundedImageView imgThumbnail;
    public TextView tvNameMovie, tvContent, tvYear, tvView, tvActor, tvCategory, tvDirector, tvSizeEpisode, tvInfor , tvComment,tvNumberComment ;
    public RecyclerView ry_actor, ry_episode, ry_propose,ry_comment;
    public ArrayList<comment> listComment;
    public ArrayList<episode> listEpisode;
    public ArrayList<movie> listPropose ;
    public movieAdapter adapterMovie;
    public EpisodeAdapter adapterEpisode;
    CommentAdapter commentAdapter ;
    LinearLayout ly_episode,ly_comment_empty, ly_actor,ly_propose,ly_infor;
    ImageView btnSend;
    String idMovie;
    EditText tvContentComment ;
    public Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        init();
        getDataMovie();
        getEpisode();
        getComment();
        event();
    }

    public void init() {
        tvNumberComment=findViewById(R.id.tvNumberComment);
        tvInfor=findViewById(R.id.tvInfor);
        tvComment=findViewById(R.id.tvComment);
        ly_comment_empty=findViewById(R.id.ly_comment_empty); ly_comment_empty.setVisibility(View.GONE);
        tvContentComment=findViewById(R.id.tvContentComment);
        btnSend=findViewById(R.id.btnSend); btnSend.setColorFilter(ContextCompat.getColor(this, R.color.green), android.graphics.PorterDuff.Mode.MULTIPLY);
        imgCover = findViewById(R.id.imgCover);
        imgThumbnail = findViewById(R.id.imgThumbnail);
        tvNameMovie = findViewById(R.id.tvNameMovie);
        tvContent = findViewById(R.id.tvContent);
        tvYear = findViewById(R.id.tvYear);
        tvView = findViewById(R.id.tvView);
        tvActor = findViewById(R.id.tvActor);
        tvCategory = findViewById(R.id.tvCategory);
        tvDirector = findViewById(R.id.tvDirector);
        tvSizeEpisode = findViewById(R.id.tvSizeEpisode);
        ly_episode = findViewById(R.id.ly_episode);
        ly_episode.setVisibility(View.GONE);
        ly_actor=findViewById(R.id.ly_actor);
        ly_propose=findViewById(R.id.ly_propose);
        ly_infor=findViewById(R.id.ly_infor);
        // phần tập phim
        listEpisode = new ArrayList<>();
        adapterEpisode = new EpisodeAdapter(DetailMovieActivity.this, listEpisode);
        ry_episode = findViewById(R.id.ry_episode);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(DetailMovieActivity.this, LinearLayoutManager.HORIZONTAL, false);
        ry_episode.setLayoutManager(layoutManager);
        ry_episode.setAdapter(adapterEpisode);
        // phan phim de xuat
        listPropose=new ArrayList<>();
        adapterMovie=new movieAdapter(DetailMovieActivity.this, listPropose);
        ry_propose=findViewById(R.id.ry_propose);
        ry_propose.setAdapter(adapterMovie);
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(DetailMovieActivity.this, LinearLayoutManager.HORIZONTAL, false);
        ry_propose.setLayoutManager(layoutManager1);

        // phan comment
        listComment=new ArrayList<>();
        commentAdapter=new CommentAdapter(DetailMovieActivity.this, listComment);
        ry_comment=findViewById(R.id.ry_comment);
        ry_comment.setAdapter(commentAdapter);
        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(DetailMovieActivity.this, LinearLayoutManager.VERTICAL, false);

        ry_comment.setLayoutManager(layoutManager2);
    }
    public void event(){
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvContentComment.getText().length()<=0){
                    notifycation("Không được để trống thông tin",200);
                    return ;
                }else{
                    sendComment();
                    tvContentComment.setText("");
                    notifycation("Bình luận thành công",200);
                }

            }
        });
        tvInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEpisode();
                tvInfor.setTextColor(Color.WHITE);
                tvComment.setTextColor(Color.parseColor("#CCCCCC"));
                tvComment.setAlpha(0.3f);
                tvInfor.setAlpha(1f);
                ly_infor.setVisibility(View.VISIBLE);
                ly_actor.setVisibility(View.VISIBLE);
                ly_propose.setVisibility(View.VISIBLE);
            }
        });
        tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInfor.setTextColor(Color.parseColor("#CCCCCC"));
                tvInfor.setAlpha(0.3f);
                tvComment.setAlpha(1f);
                tvComment.setTextColor(Color.WHITE);
                ly_infor.setVisibility(View.GONE);
                ly_episode.setVisibility(View.GONE);
                ly_actor.setVisibility(View.GONE);
                ly_propose.setVisibility(View.GONE);
            }
        });
    }
     public void sendComment(){
         Intent intent = getIntent();
         Bundle bundle = intent.getExtras();
         int id = bundle.getInt("id", 0);
         String idMovie = id + "";
         String phone="0337356550";
         String content=tvContentComment.getText().toString();
         String avatar="avatar.jpg";  /// avatar sau này nên select lấy luôn ở trong lệnh truy vấn của backend ko nên để ở đây
         RequestQueue requestQueue = Volley.newRequestQueue(DetailMovieActivity.this);
         String url = Server.sendComment;
         StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                       if(response.equals("1")){
                           getComment();
                       //    Toast.makeText(DetailMovieActivity.this, "Gui tin nhan thanh cong", Toast.LENGTH_SHORT).show();
                       }else{
                           Toast.makeText(DetailMovieActivity.this, "Gui tin nhan that bai", Toast.LENGTH_SHORT).show();
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
                 params.put("idMovie", idMovie);
                 params.put("phone", phone);
                 params.put("content", content);
                 params.put("avatar", avatar);
                 return params;
             }
         };
         requestQueue.add(stringRequest);

     }
    public void getComment(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id", 0);
        String idMovie = id + "";
        RequestQueue requestQueue = Volley.newRequestQueue(DetailMovieActivity.this);
        String url = Server.getComment;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response!=null){
                            listComment.clear();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            tvNumberComment.setText(jsonArray.length()+"");
                              if(jsonArray.length()>0){
                                  ry_comment.setVisibility(View.VISIBLE);
                                  ly_comment_empty.setVisibility(View.GONE);
                              }
                            else {
                                ry_comment.setVisibility(View.GONE);
                                ly_comment_empty.setVisibility(View.VISIBLE);
                            }
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int idCm=jsonObject.getInt("idComment");
                                int idMovie=jsonObject.getInt("idMovie");
                                String phone =jsonObject.getString("phoneNumber");
                                String contentComment=jsonObject.getString("contentComment");
                                String avatar=jsonObject.getString("avatar");
                                comment cm = new comment(idCm, idMovie,phone,contentComment,avatar);
                                listComment.add(cm);
                            }
                            commentAdapter.notifyDataSetChanged();

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
                params.put("id", idMovie);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void getIdMovie() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id", 0);
        String idMovie = id + "";
    }

    public void getMoviePropose(String cate) {
        RequestQueue request = Volley.newRequestQueue(DetailMovieActivity.this);
        String link =Server.getMoviePropose;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            listPropose.clear();
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int idMovie = jsonObject.getInt("idMovie");
                                String nameMovie = jsonObject.getString("nameMovie");
                                String thumbnail = jsonObject.getString("thumbnailMovie");
                                String cover = jsonObject.getString("coverMovie");
                                String content = jsonObject.getString("contentMoive");
                                String category = jsonObject.getString("categoryMovie");
                                String year = jsonObject.getString("yearMovie");
                                String director = jsonObject.getString("director");
                                int view = jsonObject.getInt("view");
                                movie mv = new movie(idMovie, nameMovie, thumbnail, cover, content, category, year, director, view);
                                listPropose.add(mv);
                            }
                            adapterMovie.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
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
                HashMap<String, String> param = new HashMap<>();
                param.put("category", cate);
                return param;
            }
        };
        request.add(stringRequest);
    }

    public void getDataMovie() {  // lấy thông tin phim
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id", 0);
        String idMovie = id + "";
        RequestQueue requestQueue = Volley.newRequestQueue(DetailMovieActivity.this);
        String url = Server.getMovieByid;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String nameplace = jsonObject.getString("nameMovie");
                                tvNameMovie.setText(nameplace);
                                String thumbnail = Server.getThumbnail + jsonObject.getString("thumbnailMovie");
                                Picasso.get().load(thumbnail).into(imgThumbnail);
                                String cover = Server.getCover + jsonObject.getString("coverMovie");
                                Picasso.get().load(cover).into(imgCover);
                                tvContent.setText(jsonObject.getString("contentMoive"));
                                tvCategory.setText("Thể loại phim: " + jsonObject.getString("categoryMovie"));
                                tvYear.setText("Năm phát hành: " + jsonObject.getString("yearMovie"));
                                tvDirector.setText("Đạo diễn: " + jsonObject.getString("director"));
                                tvView.setText(jsonObject.getInt("view") + "");
                                String cate = jsonObject.getString("categoryMovie");
                                getMoviePropose(cate);  // thực hiện lấy danh sách phim đề xuát cùng danh mục vs phim đó
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
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
                params.put("idMovie", idMovie);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void getEpisode() {  // lấy danh sách tập phim của phim đó
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id", 0);
        String idMovie = id + "";
        RequestQueue requestQueue = Volley.newRequestQueue(DetailMovieActivity.this);
        String url = Server.getEpisode;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            listEpisode.clear();
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                if (response.equals("0")) {
                                    ly_episode.setVisibility(View.GONE);
                                } else {
                                    tvSizeEpisode.setText(jsonArray.length() + " tập");
                                    ly_episode.setVisibility(View.VISIBLE);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        int idEpisode = jsonObject.getInt("idEpisode");
                                        int idMovie = jsonObject.getInt("idMovie");
                                        String linkEpisode = jsonObject.getString("linkEpisode");
                                        String nameEpisode = jsonObject.getString("nameEpisode");
                                        String thumbnailEpisode = jsonObject.getString("thumbnailEpisode");
                                        int numberEpisode = jsonObject.getInt("numberEpisode");
                                        episode Episode = new episode(idEpisode, idMovie, linkEpisode, nameEpisode, thumbnailEpisode, numberEpisode);
                                        listEpisode.add(Episode);
                                    }
                                }

                                adapterEpisode.notifyDataSetChanged();
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
                params.put("idMovie", idMovie);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void notifycation(String text, int gravity) {
        Dialog dialog = new Dialog(DetailMovieActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_notify); // xét content view cho dialog
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        TextView tvContent = dialog.findViewById(R.id.tvContent);
        tvContent.setText(text);
        Handler handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                handler.removeCallbacks(runnable);
            }
        };

        handler.postDelayed(runnable, 900);
        dialog.show();
    }
}

