package com.example.app_movie.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_movie.Home.HomeActivity;
import com.example.app_movie.R;
import com.example.app_movie.Util.CheckNetwork;
import com.example.app_movie.Util.Server;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {
    EditText edtPhoneNumber , edtPassword ;
  TextView btnSignUp,tvMessagePhone,tvMessagePass ;
  FirebaseAuth mAuth ;
  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback ;
  ProgressBar tienTrinh ;
Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth=FirebaseAuth.getInstance();
        init();
        event();
        checkLengphoneAndPass();
    }
    public void init(){
        tienTrinh = findViewById(R.id.tienTrinh) ;
        tienTrinh.setVisibility(View.GONE);
        tvMessagePass=findViewById(R.id.tvMessagePass); tvMessagePass.setVisibility(View.INVISIBLE);
        tvMessagePhone=findViewById(R.id.tvMessagePhone); tvMessagePhone.setVisibility(View.INVISIBLE);
        btnSignUp=findViewById(R.id.btnSignUp);  btnSignUp.setClickable(false);
        edtPhoneNumber=findViewById(R.id.edtPhoneNumber);
        edtPassword=findViewById(R.id.edtPassword);

    }

    public void event(){
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtPhoneNumber.getText().toString().trim().isEmpty()|| edtPassword.getText()
                        .toString().trim().isEmpty()){
                    Toast.makeText(SignUpActivity.this, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                }else{

               isHavePhoneNumber();

                }

            }
        });

    }
    public void isHavePhoneNumber(){  // kiểm tra xem sdt đã tồn tại hay chưa
        String urlCheckAccount = Server.checkPhoneNumber;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlCheckAccount,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.toString().equals("1")){
                              notifycation_signIn("",150);
                        }else{
                               sendOTP();
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
                params.put("phoneNumber", edtPhoneNumber.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void checkLengphoneAndPass(){  // nếu đọ dài mk và số điện thoại đủ daif thì nút ms bấm được
        edtPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length()==10){
                    tvMessagePhone.setVisibility(View.INVISIBLE);
                    edtPassword.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if(s.length()>=6 && s.length()<=20){
                                tvMessagePass.setVisibility(View.INVISIBLE);
                             btnSignUp.setClickable(true);

                            }else{
                                tvMessagePass.setVisibility(View.VISIBLE);
                               btnSignUp.setClickable(false);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                }else{
                    tvMessagePhone.setVisibility(View.VISIBLE);
                    btnSignUp.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });














    }
    public boolean checkLengPhoneNumber (){ // sdt phải 10 số
        if(edtPhoneNumber.getText().toString().length()==10){
            return true ;
        }
        else{
            return false ;
        }
    }
    public boolean checkLengPass(){  // mk lớn hơn >=6 và <=20
        if(edtPassword.getText().toString().length()>=6 && edtPassword.getText().toString().length()<=20){
            return true ;
        }else{
            return false ;
        }
    }
    public void sendOTP(){
        tienTrinh.setVisibility(View.VISIBLE);
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }
            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                tienTrinh.setVisibility(View.GONE);
                Intent intent = new Intent(SignUpActivity.this, SignUpOTPActivity.class);
                intent.putExtra("phoneNumber", edtPhoneNumber.getText().toString().trim());
                intent.putExtra("password", edtPassword.getText().toString().trim());
                intent.putExtra("verificationId",verificationId);
                startActivity(intent);

            }

        };
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+84" + edtPhoneNumber.getText().toString().trim().substring(1))       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallback)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    public void notifycation(String text, int gravity) {
        Dialog dialog = new Dialog(this);
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
    public void notifycation_signIn(String text, int gravity) {  // được sử dụng thông báo khi số điện thoại đã tồn tại
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_notify_2); // xét content view cho dialog
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
        tvContent.setText("Số điện thoại này đã đăng ký tài khoản\n L02_MOVIE. Bạn có muốn đăng nhập\n ngay không ?");
        TextView btnCancel = dialog.findViewById(R.id.btnCancel);
        TextView btnSignIn = dialog.findViewById(R.id.btnSignIn);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }
    CheckNetwork checkNetWork = new CheckNetwork();

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(checkNetWork,filter);
        super.onStart();
    }
}