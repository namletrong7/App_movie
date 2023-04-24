package com.example.app_movie.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.app_movie.R;

public class SignUpOTPActivity extends AppCompatActivity {
   // đây là màn hình nhâp otp khi đăng ký tài khoản
    TextView tvMessage,btnDangKy ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_otp);
        init();

    }
    public  void init(){
        tvMessage=findViewById(R.id.tvMessage);
        btnDangKy=findViewById(R.id.btnDangKy);
        tvMessage.setVisibility(View.GONE);

    }
}