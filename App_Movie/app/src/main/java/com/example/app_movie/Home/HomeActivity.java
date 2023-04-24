package com.example.app_movie.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.app_movie.R;

public class HomeActivity extends AppCompatActivity {

    ImageView lotie_trangChu , lotie_TruyenHinh, lotie_timKiem, lotie_caNhan ;
    LinearLayout layOutTrangChu, layOutTruyenHinh, layoutTimKiem, layOutCaNhan,layout_toolbar ;
    TextView tvTrangChu , tvTruyenHinh, tvTimKiem, tvCaNhan ;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        setBottomNavigation();
    }
    public void init(){
        tvTrangChu=(TextView) findViewById(R.id.tvTrangChu);
        tvTruyenHinh=(TextView)findViewById(R.id.tvTruyenHinh);
        tvTimKiem=(TextView)findViewById(R.id.tvTimKiem);
        tvCaNhan=(TextView)findViewById(R.id.tvCaNhan);

        lotie_trangChu=findViewById(R.id.lottie_trangChu);
        lotie_TruyenHinh=findViewById(R.id.lottie_truyenHinh);
        lotie_timKiem=findViewById(R.id.lotie_timKiem);
        lotie_caNhan=findViewById(R.id.lotie_caNhan);

        layOutTrangChu=findViewById(R.id.layOutTrangChu);
        layoutTimKiem=findViewById(R.id.layoutTimKiem);
        layOutCaNhan=findViewById(R.id.layoutCaNhan);
        layOutTruyenHinh=findViewById(R.id.layoutTruyenHinh);
        layout_toolbar=findViewById(R.id.layout_toolbar);
//        findViewById(R.id.imgDetal).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                notifySucess("Đăng nhập thành công",100);
////                Intent intent=new Intent(HomeActivity.this, DetailMovieActivity.class);
////                startActivity(intent);
//            }
//        });


    }

//    public void notifySucess(String text, int gravity) {
//        Dialog dialog = new Dialog(HomeActivity.this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.dialog_fail); // xét content view cho dialog
//        Window window = dialog.getWindow();
//        if (window == null) {
//            return;
//        }
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        WindowManager.LayoutParams windowAttributes = window.getAttributes();
//        windowAttributes.gravity = gravity;
//        window.setAttributes(windowAttributes);
//        dialog.show();
//    }
    public void showFragment(Fragment fragment) {
        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        mTransactiont.replace(R.id.content_layout, fragment, fragment.getClass().getName());
        mTransactiont.commit();
    }
    public void setBottomNavigation(){
        layOutTrangChu.setOnClickListener(v -> {
            layout_toolbar.setVisibility(View.VISIBLE);
            showFragment(new HomeFragment());
            lotie_trangChu.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_TruyenHinh.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_timKiem.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_caNhan.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            tvTrangChu.setTextColor(Color.WHITE);
            tvTruyenHinh.setTextColor(Color.parseColor("#F8F8FF"));
            tvTimKiem.setTextColor(Color.parseColor("#F8F8FF"));
            tvCaNhan.setTextColor(Color.parseColor("#F8F8FF"));

        });
        layOutTruyenHinh.setOnClickListener(v -> {
            layout_toolbar.setVisibility(View.VISIBLE);
            showFragment(new TiViFragment());
            lotie_trangChu.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_TruyenHinh.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_timKiem.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_caNhan.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            tvTrangChu.setTextColor(Color.parseColor("#F8F8FF"));
            tvTruyenHinh.setTextColor(Color.WHITE);
            tvTimKiem.setTextColor(Color.parseColor("#F8F8FF"));
            tvCaNhan.setTextColor(Color.parseColor("#F8F8FF"));
        });
        layoutTimKiem.setOnClickListener(v -> {
            layout_toolbar.setVisibility(View.GONE);
            showFragment(new SearchFragment());
            lotie_trangChu.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_TruyenHinh.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_timKiem.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_caNhan.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            tvTrangChu.setTextColor(Color.parseColor("#F8F8FF"));
            tvTruyenHinh.setTextColor(Color.parseColor("#F8F8FF"));
            tvTimKiem.setTextColor(Color.WHITE);
            tvCaNhan.setTextColor(Color.parseColor("#F8F8FF"));
        });
        layOutCaNhan.setOnClickListener(v -> {
            layout_toolbar.setVisibility(View.GONE);
            showFragment(new UserFragment());
            lotie_trangChu.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_TruyenHinh.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_timKiem.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_caNhan.setColorFilter(ContextCompat.getColor(this, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
            tvTrangChu.setTextColor(Color.parseColor("#F8F8FF"));
            tvTruyenHinh.setTextColor(Color.parseColor("#F8F8FF"));
            tvTimKiem.setTextColor(Color.parseColor("#F8F8FF"));
            tvCaNhan.setTextColor(Color.WHITE);
        });
    }

}