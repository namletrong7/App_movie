package com.example.app_movie.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.app_movie.Adapter.CommentAdapter;
import com.example.app_movie.Adapter.EpisodeAdapter;
import com.example.app_movie.Adapter.actorAdapter;
import com.example.app_movie.Adapter.movieAdapter;
import com.example.app_movie.Login.LoginActivity;
import com.example.app_movie.Model.actor;
import com.example.app_movie.Model.channel;
import com.example.app_movie.Model.comment;
import com.example.app_movie.Model.device;
import com.example.app_movie.Model.episode;
import com.example.app_movie.Model.movie;
import com.example.app_movie.PLayVideo.PlayMovieActivity;
import com.example.app_movie.R;
import com.example.app_movie.Util.CheckNetwork;
import com.example.app_movie.Util.Server;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.jaredrummler.android.device.DeviceName;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlinx.coroutines.channels.Channel;

public class DetailMovieActivity extends AppCompatActivity {
    public movie Movie;
    public ImageView imgCover, btnClose;
    public RoundedImageView imgThumbnail;
    public TextView btnPlay, tvNameMovie, tvContent, tvYear, tvView, tvActor, tvCategory, tvDirector, tvSizeEpisode, tvInfor, tvComment, tvNumberComment, tvLike;
    public RecyclerView ry_actor, ry_episode, ry_propose, ry_comment;
    public ArrayList<comment> listComment;
    public ArrayList<episode> listEpisode;
    public ArrayList<movie> listPropose;
    public ArrayList<actor> listactor;
    public movieAdapter adapterMovie;
    public EpisodeAdapter adapterEpisode;
    public CommentAdapter commentAdapter;
    public actorAdapter adapterActor;
    LinearLayout ly_episode, ly_comment_empty, ly_actor, ly_propose, ly_infor;
    ImageView btnSend, imgAddFavorite, imgLike;
    String idMovie;
    int view;
    EditText tvContentComment;
    public Runnable runnable;
    CircleImageView imgAvatar;
    String phoneNumber;
    LinearLayout ly_addLike ;

    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        phoneNumber = HomeActivity.phoneNumber;
        database = FirebaseDatabase.getInstance();
        //       phoneNumber="0977204530";
        init();
        //  checkLogin();
        getIdMovie();
        getComment();
        getDataMovie();
        getEpisode();
        getUserInfor();
        event();
        checkFavoriteMovie();
        checkLikeMovie();
        getActor();
    }

    public void init() {
        ly_addLike =findViewById(R.id.ly_addLike);
        imgLike=findViewById(R.id.imgLike);
        tvLike=findViewById(R.id.tvLike);

        btnClose = findViewById(R.id.btnClose);
        imgAddFavorite = findViewById(R.id.imgAddFavorite);
        btnPlay = findViewById(R.id.btnPlay);
        imgAvatar = findViewById(R.id.imgAvatar);
        tvNumberComment = findViewById(R.id.tvNumberComment);
        tvInfor = findViewById(R.id.tvInfor);
        tvComment = findViewById(R.id.tvComment);
        ly_comment_empty = findViewById(R.id.ly_comment_empty);
        ly_comment_empty.setVisibility(View.GONE);
        tvContentComment = findViewById(R.id.tvContentComment);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setColorFilter(ContextCompat.getColor(this, R.color.green), android.graphics.PorterDuff.Mode.MULTIPLY);
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
        ly_actor = findViewById(R.id.ly_actor);
        ly_propose = findViewById(R.id.ly_propose);
        ly_infor = findViewById(R.id.ly_infor);
        // phần tập phim
        listEpisode = new ArrayList<>();
        adapterEpisode = new EpisodeAdapter(DetailMovieActivity.this, listEpisode);
        ry_episode = findViewById(R.id.ry_episode);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(DetailMovieActivity.this, LinearLayoutManager.HORIZONTAL, false);
        ry_episode.setLayoutManager(layoutManager);
        ry_episode.setAdapter(adapterEpisode);
        // phan phim de xuat
        listPropose = new ArrayList<>();
        adapterMovie = new movieAdapter(DetailMovieActivity.this, listPropose);
        ry_propose = findViewById(R.id.ry_propose);
        ry_propose.setAdapter(adapterMovie);
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(DetailMovieActivity.this, LinearLayoutManager.HORIZONTAL, false);
        ry_propose.setLayoutManager(layoutManager1);

        // phan comment
        listComment = new ArrayList<>();
        commentAdapter = new CommentAdapter(DetailMovieActivity.this, listComment);
        ry_comment = findViewById(R.id.ry_comment);
        ry_comment.setAdapter(commentAdapter);
        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(DetailMovieActivity.this, LinearLayoutManager.VERTICAL, false);

        ry_comment.setLayoutManager(layoutManager2);

        // phần diễn viên
        listactor = new ArrayList<>();
        adapterActor = new actorAdapter(DetailMovieActivity.this, listactor);
        ry_actor = findViewById(R.id.ry_actor);
        ry_actor.setAdapter(adapterActor);
        ry_actor.setLayoutManager(new LinearLayoutManager(DetailMovieActivity.this, LinearLayoutManager.HORIZONTAL, false));

        //
        getComment();
        tvInfor.setTextColor(Color.WHITE);
        tvComment.setTextColor(Color.parseColor("#CCCCCC"));
        tvComment.setAlpha(0.3f);
        tvInfor.setAlpha(1f);
        ly_infor.setVisibility(View.VISIBLE);
        ly_actor.setVisibility(View.VISIBLE);
        ly_propose.setVisibility(View.VISIBLE);

        ly_addLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLikeMovie();
            }
        });
    }

    public void event() {
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvContentComment.getText().length() <= 0) {
                    notifycation("Không được để trống thông tin", 200);
                    return;
                } else {
                    sendComment();
                    tvContentComment.setText("");
                    notifycation("Bình luận thành công", 200);
                }

            }
        });
        tvInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getComment();
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
                getComment();
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
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateView(); // mỗi lần nhấn xem thì sẽ cập nhập lại lượt xem của movie đó
                playMovie();
            }
        });
        imgAddFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFavoriteMovie();
            }
        });
    }

    //    public void checkLogin() {
//
//        DeviceName.with(this).request(new DeviceName.Callback() {
//            @Override public void onFinished(DeviceName.DeviceInfo info, Exception error) {
//                String deviceName = info.getName();       // "Galaxy S8+
//                database.getReference("device").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if (snapshot.exists()) {
//                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {
//                                device dv = new device();
//                                dv.setIdDevice(snapshot1.child("idDevice").getValue().toString());
//                                dv.setNameDevice(snapshot1.child("nameDevice").getValue().toString());
//                                dv.setPhoneNumber(snapshot1.child("phoneNumber").getValue().toString());
//                                dv.setTimeDateSignIn(snapshot1.child("timeDateSignIn").getValue().toString());
//                                if (dv.getPhoneNumber().equals(phoneNumber) && dv.getNameDevice().equals(deviceName)) {
//                                    Toast.makeText(DetailMovieActivity.this, "đang đăng nhập", Toast.LENGTH_SHORT).show();
//                                }
//                                else{
//                                    Toast.makeText(DetailMovieActivity.this, "Tài khoản của bạn đã được đăng xuất từ xa", Toast.LENGTH_SHORT).show();
//                                    finish();
//                                    Intent intent = new Intent(DetailMovieActivity.this, LoginActivity.class);
//                                    startActivity(intent);
//
//                                }
//
//
//                            }
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//            }
//        });
//
//
//
//
//    }
    public void sendComment() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id", 0);
        String idMovie = id + "";
        String content = tvContentComment.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(DetailMovieActivity.this);
        String url = Server.sendComment;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("1")) {
                            getComment();  // khi gửi cmt xong thì ta gọi lại hàm này để cập nhập lại danh sách bình luận của phim đó
                            //    Toast.makeText(DetailMovieActivity.this, "Gui tin nhan thanh cong", Toast.LENGTH_SHORT).show();
                        } else {
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
                params.put("phone", phoneNumber);
                params.put("content", content);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    public void getComment() {
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
                        if (response != null) {
                            listComment.clear();
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                tvNumberComment.setText(jsonArray.length() + "");
                                if (jsonArray.length() > 0) {
                                    ry_comment.setVisibility(View.VISIBLE);
                                    ly_comment_empty.setVisibility(View.GONE);
                                } else {
                                    ry_comment.setVisibility(View.GONE);
                                    ly_comment_empty.setVisibility(View.VISIBLE);
                                }
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                    int idCm = jsonObject.getInt("idComment");
//                                    int idMovie = jsonObject.getInt("idMovie");
//                                    String phone = jsonObject.getString("phoneNumber");
//                                    String contentComment = jsonObject.getString("contentComment");
//                                    String avatar = jsonObject.getString("avatar");
                                    Gson gson = new Gson();
                                    comment Comment = new comment();
                                    Comment = gson.fromJson(jsonObject.toString(), comment.class);
                                    listComment.add(Comment);
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
        idMovie = id + "";
    }

    public void getMoviePropose(String cate) {
        RequestQueue request = Volley.newRequestQueue(DetailMovieActivity.this);
        String link = Server.getMoviePropose;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            listPropose.clear();
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                int idMovie = jsonObject.getInt("idMovie");
//                                String nameMovie = jsonObject.getString("nameMovie");
//                                String thumbnail = jsonObject.getString("thumbnailMovie");
//                                String cover = jsonObject.getString("coverMovie");
//                                String content = jsonObject.getString("contentMoive");
//                                String category = jsonObject.getString("categoryMovie");
//                                String year = jsonObject.getString("yearMovie");
//                                String director = jsonObject.getString("director");
//                                int view = jsonObject.getInt("view");
//                                movie mv = new movie(idMovie, nameMovie, thumbnail, cover, content, category, year, director, view);
                                Gson gson = new Gson();
                                movie Movie = new movie();
                                Movie = gson.fromJson(jsonObject.toString(), movie.class);
                                listPropose.add(Movie);
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
        String url = Server.getInforMovieByid;
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
                                tvContent.setText("Nội dung phim: " + jsonObject.getString("contentMoive"));
                                tvCategory.setText("Thể loại phim: " + jsonObject.getString("categoryMovie"));
                                tvYear.setText("Phát hành: " + jsonObject.getString("yearMovie"));
                                tvDirector.setText("Đạo diễn: " + jsonObject.getString("director"));
                                view = jsonObject.getInt("view");
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

    public void getActor() {
        String linkActor = Server.getActor;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id", 0);
        String idMovie = id + "";
        RequestQueue requestQueue = Volley.newRequestQueue(DetailMovieActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, linkActor,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            listComment.clear();
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    int idActor = jsonObject.getInt("idActor");
                                    int idMovie = jsonObject.getInt("idMovie");
                                    String nameActor = jsonObject.getString("nameActor");
                                    String avatarActor = jsonObject.getString("avatarActor");
                                    listactor.add(new actor(idActor, idMovie, nameActor, avatarActor));
                                }
                                adapterActor.notifyDataSetChanged();

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

    public void getUserInfor() {
        RequestQueue requestQueue = Volley.newRequestQueue(DetailMovieActivity.this);
        String url = Server.getUserInfor;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i <= jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String avatar = jsonObject.getString("avatar");
                                String linkAvatar = Server.getAvatar + avatar;
                                Picasso.get().load(linkAvatar).into(imgAvatar);
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
                params.put("phoneNumber", phoneNumber);
                return params;
            }
        };
        ;
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

    public void playMovie() {  // trong trường hợp mà phim chỉ có 1 tâp thì jhi bấm phát sẽ phát tập đó luôn
        // hoặc phim chỉ có nhiều tập thig khi nhấn nút xem sẽ phát tập 1 của phim đó
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
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                if (response.equals("0")) {
                                    return;
                                } else {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        String linkEpisode = jsonObject.getString("linkEpisode");
                                        //     linkEpisode
                                        Intent intent = new Intent(DetailMovieActivity.this, PlayMovieActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("linkEpisode", linkEpisode);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        break;
                                    }
                                }
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
    public void addLikeMovie(){  // thêm phim này vào danh sách đã xếp hạng
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id", 0);
        String idMovie = id + "";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = Server.addLikeMovie;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("1")) {
                            // thêm vào danh sách thành công
                            checkLikeMovie();  // cập nhập lại trạng thái của phim xem đã được thêm chưa

                        } else if (response.equals("0")) {  // xóa khỏi danh sách
                            checkLikeMovie();


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
                params.put("phoneNumber", phoneNumber);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void addFavoriteMovie() {// kiểm tra xem phim này đã được đưa vào danh sách phim đa lưu hay chưa
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id", 0);
        String idMovie = id + "";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = Server.addFavoriteMovie;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("1")) {
                            // thêm vào danh sách thành công
                            checkFavoriteMovie();  // cập nhập lại trạng thái của phim xem đã được thêm chưa
                            notifycation("Đã thêm vào danh sách của tôi", 200);
                        } else if (response.equals("0")) {  // xóa khỏi danh sách
                            checkFavoriteMovie();
                            notifycation("Đã gỡ khỏi danh sách của tôi", 200);

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
                params.put("phoneNumber", phoneNumber);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
    public void checkLikeMovie(){// kiểm tra xem đã like phim này hay chưa
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id", 0);
        String idMovie = id + "";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = Server.checkLikeMovie;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("1")) {
                            // đang ở trong sanh sách
                            imgLike.setImageResource(R.drawable.icon_like_detail);
                            tvLike.setText("Đã xếp hạng");
                        } else if (response.equals("0")) {  // không ở trong danh sách
                            imgLike.setImageResource(R.drawable.icon_unlike);
                            tvLike.setText("Xếp hạng ngay");
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
                params.put("phoneNumber", phoneNumber);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
    public void checkFavoriteMovie() {// kiểm tra xem phim này đã được đưa vào danh sách phim đa lưu hay chưa
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id", 0);
        String idMovie = id + "";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = Server.checkFavoriteMovie;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("1")) {
                            // đang ở trong sanh sách
                            imgAddFavorite.setImageResource(R.drawable.check_solid);
                        } else if (response.equals("0")) {  // không ở trong danh sách
                            imgAddFavorite.setImageResource(R.drawable.plus_solid);
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
                params.put("phoneNumber", phoneNumber);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    public void updateView() {  // cập lượt xem cho view
        view = view + 1;
        RequestQueue requestQueue = Volley.newRequestQueue(DetailMovieActivity.this);
        String url = Server.updateView;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

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
                params.put("view", view + "");
                params.put("idMovie", idMovie);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    CheckNetwork checkNetWork = new CheckNetwork();

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(checkNetWork, filter);
        super.onStart();
    }

}

