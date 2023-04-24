package com.example.app_movie.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_movie.R;

public class LoginActivity extends AppCompatActivity {
    EditText edtPhoneNumber, edtPassword;
    TextView tvForgotPass, btnLogin, tvSignUp;
    ImageView imgShowPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        showPassword();
        event();
    }

    public void init() {
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtPassword = findViewById(R.id.edtPassword);
        tvForgotPass = findViewById(R.id.tvForgotPass); tvForgotPass.setEnabled(false);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignUp = findViewById(R.id.tvSignUp);
        imgShowPass = findViewById(R.id.imgShowPass);
        imgShowPass.setColorFilter(ContextCompat.getColor(this, R.color.white));  // sé màu trắng cho cái nút ẩn hiển mật khẩu
        edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

    }
      public void event(){
        tvSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            // trước khi nhập text
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
         // trong khi nhập text
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.toString().length()>=6 && edtPhoneNumber.getText().toString().length()==10){
                        btnLogin.setBackgroundResource(R.drawable.custom_background_green_toi);
                    }else{
                        btnLogin.setBackgroundResource(R.drawable.custom_background_gray);
                    }
            }
           // sau khi nhập text
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

      }
    public void showPassword() {
        imgShowPass.setOnClickListener(v -> {
            if (edtPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                imgShowPass.setImageResource(R.drawable.icon_eye_show);
                //Show Password
                edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                imgShowPass.setImageResource(R.drawable.icon_eye_hiden);
                //Hide Password
                edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }

        });
    }
}