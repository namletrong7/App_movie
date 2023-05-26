package com.example.app_movie.Home;

import static com.example.app_movie.R.color.black;
import static com.example.app_movie.R.color.green;
import static com.example.app_movie.R.color.green_sang;
import static com.example.app_movie.R.color.orange;
import static com.example.app_movie.R.color.white;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_movie.Login.LoginActivity;
import com.example.app_movie.Model.device;
import com.example.app_movie.PLayVideo.PLayShortVideo;
import com.example.app_movie.R;
import com.example.app_movie.Util.CheckNetwork;
import com.example.app_movie.Util.Server;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

public class HomeActivity extends AppCompatActivity {

    ImageView lotie_trangChu, lotie_TruyenHinh, lotie_timKiem, lotie_caNhan;
    LinearLayout layOutTrangChu, layOutTruyenHinh, layoutTimKiem, layOutCaNhan, layout_toolbar;
    TextView tvTrangChu, tvTruyenHinh, tvTimKiem, tvCaNhan, tvPhone;
    Fragment homeFragment, searchFragment, tiviFragment, userFragment, active;
    public static String KEY_PHONENUMBER = "key_phoneNumber";
    public static String phoneNumber;
    RoundedImageView imgAvatar;
    FragmentTransaction mTransactiont;
    int select = 1;
    String checkLogIn = "1";
    ArrayList<device> listDevice;
    FirebaseDatabase database;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        phoneNumber = getPhoneNumber();
        database = FirebaseDatabase.getInstance();
        init();
        event();
        setBottomNavigation();
        getUserInfor();
//      checkLogin();
    }

    public void init() {
        listDevice = new ArrayList<>();
        mTransactiont = getSupportFragmentManager().beginTransaction();
        // khi khởi tạo thì add tất cả các fragment
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        tiviFragment = new TiViFragment();
        userFragment = new UserFragment();
        mTransactiont.add(R.id.content_layout, homeFragment);
        mTransactiont.add(R.id.content_layout, searchFragment);
        mTransactiont.add(R.id.content_layout, tiviFragment);
        mTransactiont.add(R.id.content_layout, userFragment);
        mTransactiont.hide(searchFragment);
        mTransactiont.hide(tiviFragment);
        mTransactiont.hide(userFragment);
        mTransactiont.show(homeFragment);
        mTransactiont.commit();
        active = homeFragment;
        mTransactiont.addToBackStack(null);


        tvPhone = findViewById(R.id.tvPhoneNumber);
        imgAvatar = findViewById(R.id.imgAvatar);


        tvTrangChu = (TextView) findViewById(R.id.tvTrangChu);
        tvTruyenHinh = (TextView) findViewById(R.id.tvTruyenHinh);
        tvTimKiem = (TextView) findViewById(R.id.tvTimKiem);
        tvCaNhan = (TextView) findViewById(R.id.tvCaNhan);

        lotie_trangChu = findViewById(R.id.lotie_trangChu);
        lotie_TruyenHinh = findViewById(R.id.lotie_TruyenHinh);
        lotie_timKiem = findViewById(R.id.lotie_timKiem);
        lotie_caNhan = findViewById(R.id.lotie_caNhan);

        layOutTrangChu = findViewById(R.id.layOutTrangChu);
        layoutTimKiem = findViewById(R.id.layoutTimKiem);
        layOutCaNhan = findViewById(R.id.layoutCaNhan);
        layOutTruyenHinh = findViewById(R.id.layOutTruyenHinh);
        layout_toolbar = findViewById(R.id.layout_toolbar);


    }

    public void event() {
        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this, PLayShortVideo.class);
                startActivity(intent);
            }
        });
    }

    public void showFragment(Fragment fragment) {
        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        ;
        mTransactiont.show(fragment);
        mTransactiont.hide(active);
        active = fragment;
        mTransactiont.commit();

    }

    public void setBottomNavigation() {
        layOutTrangChu.setOnClickListener(v -> {
            TiViFragment.releasePlayer();
            if (select != 1) {
                layout_toolbar.setVisibility(View.VISIBLE);
                showFragment(homeFragment);
                lotie_trangChu.setColorFilter(ContextCompat.getColor(this, green), android.graphics.PorterDuff.Mode.MULTIPLY);
                lotie_TruyenHinh.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
                lotie_timKiem.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
                lotie_caNhan.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
                tvTrangChu.setTextColor(Color.GREEN);
                tvTruyenHinh.setTextColor(Color.GRAY);
                tvTimKiem.setTextColor(Color.GRAY);
                tvCaNhan.setTextColor(Color.GRAY);
                select = 1;
            }
        });
        layOutTruyenHinh.setOnClickListener(v -> {;
            TiViFragment.playChannel(this);
            if (select != 2) {
                layout_toolbar.setVisibility(View.VISIBLE);
                showFragment(tiviFragment);

                lotie_trangChu.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
                lotie_TruyenHinh.setColorFilter(ContextCompat.getColor(this, green), android.graphics.PorterDuff.Mode.MULTIPLY);
                lotie_timKiem.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
                lotie_caNhan.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
                tvTrangChu.setTextColor(Color.GRAY);
                tvTruyenHinh.setTextColor(Color.GREEN);
                tvTimKiem.setTextColor(Color.GRAY);
                tvCaNhan.setTextColor(Color.GRAY);
                select = 2;

            }
        });
        layoutTimKiem.setOnClickListener(v -> {
            TiViFragment.releasePlayer();
            if (select != 3) {

                layout_toolbar.setVisibility(View.GONE);
                showFragment(searchFragment);
                lotie_trangChu.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
                lotie_TruyenHinh.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
                lotie_timKiem.setColorFilter(ContextCompat.getColor(this, green), android.graphics.PorterDuff.Mode.MULTIPLY);
                lotie_caNhan.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
                tvTrangChu.setTextColor(Color.GRAY);
                tvTruyenHinh.setTextColor(Color.GRAY);
                tvTimKiem.setTextColor(Color.GREEN);
                tvCaNhan.setTextColor(Color.GRAY);
                select = 3;
            }
        });
        layOutCaNhan.setOnClickListener(v -> {
            TiViFragment.releasePlayer();
            if (select != 4) {
                layout_toolbar.setVisibility(View.GONE);
                showFragment(userFragment);

                lotie_trangChu.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
                lotie_TruyenHinh.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
                lotie_timKiem.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
                lotie_caNhan.setColorFilter(ContextCompat.getColor(this, green), android.graphics.PorterDuff.Mode.MULTIPLY);
                tvTrangChu.setTextColor(Color.GRAY);
                tvTruyenHinh.setTextColor(Color.GRAY);
                tvTimKiem.setTextColor(Color.GRAY);
                tvCaNhan.setTextColor(Color.GREEN);
                select = 4;

            }
        });
    }

    public String getPhoneNumber() {
        SharedPreferences sh = getSharedPreferences(KEY_PHONENUMBER, Context.MODE_PRIVATE);
        String phoneNumber = sh.getString("phoneNumber", "");
        return phoneNumber;
    }

    public void getUserInfor() {
        RequestQueue requestQueue = Volley.newRequestQueue(HomeActivity.this);
        String url = Server.getUserInfor;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i <= jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                String  phone = jsonObject.getString("phoneNumber");
//                                String pass = jsonObject.getString("password");
//                                userName= jsonObject.getString("nameUser");
//                                String sex = jsonObject.getString("sex");
//                                String birthday = jsonObject.getString("birthday");
                        String avatar = jsonObject.getString("avatar");
                        String linkAvatar = Server.getAvatar + avatar;
                        Picasso.get().load(linkAvatar).into(imgAvatar);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
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
        Dialog dialog = new Dialog(HomeActivity.this);
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
        dialog.show();
    }

    CheckNetwork checkNetWork = new CheckNetwork();

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(checkNetWork, filter);
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserInfor();
        if(tiviFragment.isHidden()==false){
            TiViFragment.playChannel(this);
        }

    }

//    public void checkLogin() {
//
//        DeviceName.with(this).request(new DeviceName.Callback() {
//            @Override public void onFinished(DeviceName.DeviceInfo info, Exception error) {
//                String deviceName = info.getName();       // "Galaxy S8+
//                database.getReference("device").orderByChild().addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if (snapshot.exists()) {
//
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}