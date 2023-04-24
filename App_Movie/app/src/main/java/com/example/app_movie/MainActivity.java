package com.example.app_movie;

import static com.example.app_movie.R.color.black;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
   ImageView lotie_trangChu , lotie_TruyenHinh, lotie_timKiem, lotie_caNhan ;
   LinearLayout layOutTrangChu, layOutTruyenHinh, layoutTimKiem, layOutCaNhan ;
   TextView tvTrangChu , tvTruyenHinh, tvTimKiem, tvCaNhan ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      anhXa();
  setBottomNavigation();  // khi nhân vào các thành phần trong thanh navigation

    }
    public void anhXa(){
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

    }


    public void setBottomNavigation(){
        layOutTrangChu.setOnClickListener(v -> {
            lotie_trangChu.setColorFilter(ContextCompat.getColor(this, R.color.green), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_TruyenHinh.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_timKiem.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_caNhan.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
             tvTrangChu.setTextColor(Color.WHITE);
            tvTruyenHinh.setTextColor(Color.parseColor("#DDDDDD"));
            tvTimKiem.setTextColor(Color.parseColor("#DDDDDD"));
            tvCaNhan.setTextColor(Color.parseColor("#DDDDDD"));

        });
        layOutTruyenHinh.setOnClickListener(v -> {
            lotie_trangChu.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_TruyenHinh.setColorFilter(ContextCompat.getColor(this, R.color.green), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_timKiem.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_caNhan.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            tvTrangChu.setTextColor(Color.parseColor("#DDDDDD"));
            tvTruyenHinh.setTextColor(Color.WHITE);
            tvTimKiem.setTextColor(Color.parseColor("#DDDDDD"));
            tvCaNhan.setTextColor(Color.parseColor("#DDDDDD"));
        });
        layoutTimKiem.setOnClickListener(v -> {
            lotie_trangChu.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_TruyenHinh.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_timKiem.setColorFilter(ContextCompat.getColor(this, R.color.green), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_caNhan.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            tvTrangChu.setTextColor(Color.parseColor("#DDDDDD"));
            tvTruyenHinh.setTextColor(Color.parseColor("#DDDDDD"));
            tvTimKiem.setTextColor(Color.WHITE);
            tvCaNhan.setTextColor(Color.parseColor("#DDDDDD"));
        });
        layOutCaNhan.setOnClickListener(v -> {
            lotie_trangChu.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_TruyenHinh.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_timKiem.setColorFilter(ContextCompat.getColor(this, R.color.gray), android.graphics.PorterDuff.Mode.MULTIPLY);
            lotie_caNhan.setColorFilter(ContextCompat.getColor(this, R.color.green), android.graphics.PorterDuff.Mode.MULTIPLY);
            tvTrangChu.setTextColor(Color.parseColor("#DDDDDD"));
            tvTruyenHinh.setTextColor(Color.parseColor("#DDDDDD"));
            tvTimKiem.setTextColor(Color.parseColor("#DDDDDD"));
            tvCaNhan.setTextColor(Color.WHITE);
        });
    }
}