package com.example.app_movie.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_movie.Home.HomeActivity;
import com.example.app_movie.Personal.InformationAccountActivity;
import com.example.app_movie.R;
import com.example.app_movie.Util.CheckNetwork;
import com.example.app_movie.Util.Server;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class ChangePasswordActivity extends AppCompatActivity {
    String phoneNumber;
  public EditText edtOldPassword , edtNewPass , edtReTypePass ;
  public TextView btnChangePass,tvMessagePass ;
    ImageView imgShowOldPass ,imgNewPass,imgReTypePass;
    Runnable runnable ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        phoneNumber= HomeActivity.phoneNumber;
        init();
        showPass();
        event();
    }
    public void init(){
        tvMessagePass=findViewById(R.id.tvMessagePass); tvMessagePass.setVisibility(View.INVISIBLE);
        edtOldPassword=findViewById(R.id.edtOldPassword);
        edtNewPass=findViewById(R.id.edtNewPass);
        edtReTypePass=findViewById(R.id.edtReTypePass);
        btnChangePass=findViewById(R.id.btnChangePass);
        imgShowOldPass=findViewById(R.id.imgShowOldPass);
        imgNewPass=findViewById(R.id.imgNewPass);
        imgReTypePass=findViewById(R.id.imgReTypePass);
    }
    public void event(){
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNewPass.getText().toString().trim().equals(edtReTypePass.getText().toString().trim())){
                    checkOldPass();

                }else{
                    notifycation("Mật khẩu nhập mới lại không trùng nhau",200);
                    return ;
                }
            }
        });
        edtNewPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                       if(s.length()>=6 && s.length()<=20){
                           tvMessagePass.setVisibility(View.INVISIBLE);
                           btnChangePass.setClickable(true);
                       }else{
                           tvMessagePass.setVisibility(View.VISIBLE);
                           btnChangePass.setClickable(false);
                       }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void changePassWord(){
        RequestQueue requestQueue = Volley.newRequestQueue(ChangePasswordActivity.this);
        String linkChangePass=Server.changePassword;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, linkChangePass,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("1")){
                            notifycation("Đổi mật khẩu thành công",200);
                            edtNewPass.setText("");
                            edtOldPassword.setText("");
                            edtReTypePass.setText("");
                            return ;
                       //     finish();
                        }
                        else{
                            notifycation("Đổi mật khẩu thất bại",200);
                            return ;
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
                params.put("password", edtNewPass.getText().toString().trim());
                params.put("phoneNumber", phoneNumber.trim());
                return params;
            }
        };;
        requestQueue.add(stringRequest);
    }
    public void checkOldPass(){// kiểm tra mật khẩu cũ xem có nhập đúng hay không
        RequestQueue requestQueue = Volley.newRequestQueue(ChangePasswordActivity.this);
        String linkCheckPass=Server.checkAccount;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, linkCheckPass,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("1")){
                            changePassWord();
                        }
                        else{
                          notifycation("Mật khẩu cũ không chính xác",200);
                          return ;
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
                params.put("password", edtOldPassword.getText().toString().trim());
                params.put("phoneNumber", phoneNumber.trim());
                return params;
            }
        };;
        requestQueue.add(stringRequest);
    }
    public void notifycation(String text, int gravity) {
        Dialog dialog = new Dialog(ChangePasswordActivity.this);
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
    public void showPass(){
        imgShowOldPass.setOnClickListener(v -> {
            if (edtOldPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                imgShowOldPass.setImageResource(R.drawable.icon_eye_show);
                //Show Password
                edtOldPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                imgShowOldPass.setImageResource(R.drawable.icon_eye_hiden);
                //Hide Password
                edtOldPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }

        });
        imgNewPass.setOnClickListener(v -> {
            if (edtNewPass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                imgNewPass.setImageResource(R.drawable.icon_eye_show);
                //Show Password
                edtNewPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                imgNewPass.setImageResource(R.drawable.icon_eye_hiden);
                //Hide Password
                edtNewPass.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }

        });
        imgReTypePass.setOnClickListener(v -> {
            if (edtReTypePass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                imgReTypePass.setImageResource(R.drawable.icon_eye_show);
                //Show Password
                edtReTypePass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                imgReTypePass.setImageResource(R.drawable.icon_eye_hiden);
                //Hide Password
                edtReTypePass.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }

        });

    }
    CheckNetwork checkNetWork = new CheckNetwork();

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(checkNetWork,filter);
        super.onStart();
    }
}