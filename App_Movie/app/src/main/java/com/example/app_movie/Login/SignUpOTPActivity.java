package com.example.app_movie.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_movie.R;
import com.example.app_movie.Util.CheckNetwork;
import com.example.app_movie.Util.Server;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SignUpOTPActivity extends AppCompatActivity {
   // đây là màn hình nhâp otp khi đăng ký tài khoản
    TextView tvMessage,btnContinue,tvPhone,tvResendOTP ;
    EditText so1,so2,so3,so4,so5,so6;
    String phoneNumber,verificationId,password ;
    Runnable runnable ;
    FirebaseAuth mAuth ;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_otp);
        mAuth=FirebaseAuth.getInstance();
        init();
        ChuyenNut();
        event();
     //   resendOTP();

    }
    public  void init(){
        tvResendOTP=findViewById(R.id.tvResendOTP);
        tvPhone=findViewById(R.id.tvPhone);
        tvMessage=findViewById(R.id.tvMessage);
        btnContinue=findViewById(R.id.btnContinue);
        tvMessage.setVisibility(View.GONE);
        so1 = findViewById(R.id.input_one);
        so2 = findViewById(R.id.input_two);
        so3 = findViewById(R.id.input_three);
        so4 = findViewById(R.id.input_four);
        so5 = findViewById(R.id.input_five);
        so6 = findViewById(R.id.input_six);


        Intent intent=this.getIntent() ;
        phoneNumber= intent.getStringExtra("phoneNumber");
        password= intent.getStringExtra("password");

        tvPhone.setText("Vui lòng nhập mã OTP đã được gửi về số điện \nthoại "+ phoneNumber);
        verificationId=intent.getStringExtra("verificationId");
    }
     public void event(){
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
        });
         tvResendOTP.setOnClickListener(v -> {
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
                     verificationId=verificationId ;
                 }

             };
             PhoneAuthOptions options =
                     PhoneAuthOptions.newBuilder(mAuth)
                             .setPhoneNumber("+84" +phoneNumber)       // Phone number to verify
                             .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                             .setActivity(this)                 // (optional) Activity for callback binding
                             // If no activity is passed, reCAPTCHA verification can not be used.
                             .setCallbacks(mCallback)          // OnVerificationStateChangedCallbacks
                             .build();
             PhoneAuthProvider.verifyPhoneNumber(options);
           notifycation("Mã OTP vừa được gửi lại",200);
             so1.setText("");
             so2.setText("");
             so3.setText("");
             so4.setText("");
             so5.setText("");
             so6.setText("");
         });

         btnContinue.setOnClickListener(v -> {
             if(so1.getText().toString().trim().isEmpty()||
                     so2.getText().toString().trim().isEmpty()||
                     so3.getText().toString().trim().isEmpty()||
                      so4.getText().toString().trim().isEmpty()||
                     so5.getText().toString().trim().isEmpty()||
                      so6.getText().toString().trim().isEmpty()){
            notifycation("không được để trống mã OTP",200);
             }else{
                 if(verificationId!=null){
                     String OTP = so1.getText().toString().trim()+
                             so2.getText().toString().trim()+
                             so3.getText().toString().trim()+
                             so4.getText().toString().trim()+
                             so5.getText().toString().trim()+
                             so6.getText().toString().trim();
                     PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,OTP);
                     mAuth.signInWithCredential(credential)
                             .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                 @Override
                                 public void onComplete(@NonNull Task<AuthResult> task) {
                                     if (task.isSuccessful()) {
                                         creatAccount();
                                     } else {
                                         notifycation("Mã OTP không chính xác vui lòng nhập lại\nLưu ý mã OTP chỉ có hiệu lực trong 60S", 200);
                                         so1.setText("");
                                         so2.setText("");
                                         so3.setText("");
                                         so4.setText("");
                                         so5.setText("");
                                         so6.setText("");
                                     }
                                 }
                             });

                 }
             }
         });
     }
     public  void creatAccount(){
         RequestQueue requestQueue = Volley.newRequestQueue(this);
         StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.createAccount,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         if(response.toString().equals("1")){
                          notifycation("Xin chúc mừng bạn đã tạo tài khoản thành công", 200);
                         }else{
                           notifycation("Xin lỗi bạn đã tạo tài khoản thất bại",200);
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
                 params.put("password", password);
                 return params;
             }
         };
         requestQueue.add(stringRequest);
     }
    private void ChuyenNut() {
        so1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().equals("")){
                    so2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        so2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().equals("")){
                    so3.requestFocus();
                }
                if (charSequence.toString().trim().equals("")){
                    so1.requestFocus();
                }
                if (!charSequence.toString().trim().equals("")){
                    so3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        so3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().equals("")){
                    so4.requestFocus();
                }
                if (charSequence.toString().trim().equals("")){
                    so2.requestFocus();
                }
                if (!charSequence.toString().trim().equals("")){
                    so4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        so4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().equals("")){
                    so5.requestFocus();
                }
                if (charSequence.toString().trim().equals("")){
                    so3.requestFocus();
                }
                if (!charSequence.toString().trim().equals("")){
                    so5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        so5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().equals("")){
                    so6.requestFocus();
                }
                if (charSequence.toString().trim().equals("")){
                    so4.requestFocus();
                }
                if (!charSequence.toString().trim().equals("")){
                    so6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        so6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().equals("")){
                    so5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    public void notifycation(String text, int gravity) {
        Dialog dialog = new Dialog(SignUpOTPActivity.this);
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

        handler.postDelayed(runnable, 2000);
        dialog.show();
    }
   public void  resendOTP(){
        long duration = TimeUnit.MINUTES.toMillis(1);
        new CountDownTimer(duration,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                   String timer = String.format(Locale.ENGLISH,"%02d",
                           TimeUnit.MILLISECONDS.toSeconds(1) -
                           TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(1)));
                   tvResendOTP.setText(timer);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
    CheckNetwork checkNetWork = new CheckNetwork();

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(checkNetWork,filter);
        super.onStart();
    }
}