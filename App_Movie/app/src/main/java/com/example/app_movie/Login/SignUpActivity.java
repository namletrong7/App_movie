package com.example.app_movie.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.app_movie.R;

public class SignUpActivity extends AppCompatActivity {
  TextView btnSignUp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        event();
    }
    public void init(){
        btnSignUp=findViewById(R.id.btnSignUp);

    }
    public void event(){
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignUpOTPActivity.class);
                startActivity(intent);
            }
        });

    }

}