package com.example.app_movie.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_movie.Home.DetailMovieActivity;
import com.example.app_movie.Home.HomeActivity;
import com.example.app_movie.Model.device;
import com.example.app_movie.R;
import com.example.app_movie.Util.CheckNetwork;
import com.example.app_movie.Util.Server;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.android.device.DeviceName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText edtPhoneNumber, edtPassword;
    TextView tvForgotPass, btnLogin, tvSignUp,tvMessagePhone,tvMessagePass;
    ImageView imgShowPass;
    String phone , pass ;
    RequestQueue requestQueue;
    Runnable runnable ;
    FirebaseDatabase database ;
   public static device dv1 ;
    public static String KEY_PHONENUMBER="key_phoneNumber";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database=FirebaseDatabase.getInstance();
        init();
        showPassword();
        event();
        checkLengPhoneAndPass();
    }

    public void init() {
        requestQueue = Volley.newRequestQueue(LoginActivity.this);
        tvMessagePass=findViewById(R.id.tvMessagePass); tvMessagePass.setVisibility(View.INVISIBLE);
        tvMessagePhone=findViewById(R.id.tvMessagePhone); tvMessagePhone.setVisibility(View.INVISIBLE);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtPassword = findViewById(R.id.edtPassword);
        tvForgotPass = findViewById(R.id.tvForgotPass);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignUp = findViewById(R.id.tvSignUp);
        imgShowPass = findViewById(R.id.imgShowPass);
        imgShowPass.setColorFilter(ContextCompat.getColor(this, R.color.white));  // sé màu trắng cho cái nút ẩn hiển mật khẩu
        edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        phone=edtPhoneNumber.getText().toString().trim();
        pass=edtPassword.getText().toString().trim();
    }
    public void event() {
          tvSignUp.setOnClickListener(v -> {
              Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
              startActivity(intent);
          });
          btnLogin.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                 checkAccount();
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
    public void checkLengPhoneAndPass(){  //
       edtPhoneNumber.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
                 if(s.toString().length()==10){
                     tvMessagePhone.setVisibility(View.INVISIBLE);

                 }else{
                     tvMessagePhone.setVisibility(View.VISIBLE);
                 }
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
       edtPassword.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(s.length()>=6 && s.length()<=20){
                   tvMessagePass.setVisibility(View.INVISIBLE);

               }else{
                   tvMessagePass.setVisibility(View.VISIBLE);
               }
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
    }
    public void checkAccount(){// kiểm tra sự tồn tại của tài khoản nhập vào
        String urlCheckAccount = Server.checkAccount;
   //     String url="http://appmoviel02.infinityfreeapp.com/checkAccount.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlCheckAccount,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      if(response.toString().equals("1")){
                         // chuyển sang activiy home
                          // tạo 1 biến để lưu trữ biến chứa số điện thoại người dừng
                          SharedPreferences sharedPreferences = getSharedPreferences(KEY_PHONENUMBER,MODE_PRIVATE);
                          SharedPreferences.Editor myEdit = sharedPreferences.edit();
                          myEdit.putString("phoneNumber", edtPhoneNumber.getText().toString());
                          myEdit.commit();
                          addDeivice();
                          finish();
                          Intent intent = new Intent(LoginActivity.this, HomeActivity.class) ;
                          startActivity(intent);
                          Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                      }else{
                          notifycation("Số điện thoại hoặc mật khẩu không đúng",200);
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
                params.put("password", edtPassword.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
    // check account trong csdl
    public void addDeivice(){
        String PATTERN = "HH:mm dd/MM/yyyy ";
        DateFormat df = new SimpleDateFormat(PATTERN);
        Date today = Calendar.getInstance().getTime();
        String dateTimeSignIn = df.format(today).toString();

    //
        String deviceName ;
        // hàm lấy thông tin máy đăng nhặp
        DeviceName.with(this).request(new DeviceName.Callback() {
            @Override public void onFinished(DeviceName.DeviceInfo info, Exception error) {
                String manufacturer = info.manufacturer;  // "Samsung"
                String name = info.marketName;            // "Galaxy S8+"
                String model = info.model;                // "SM-G955W"
                String codename = info.codename;          // "dream2qltecan"
                String deviceName = info.getName();       // "Galaxy S8+
                String linkAddDevice=Server.addDevice ;
                String idDevice=  System.currentTimeMillis()+codename ;
                String phone= edtPhoneNumber.getText().toString();
                dv1 = new device(idDevice,phone, name,dateTimeSignIn);
                database.getReference().child("device").child(idDevice).setValue(dv1);

//                StringRequest stringRequest = new StringRequest(Request.Method.POST, linkAddDevice,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//
//                            }
//                        }
//
//                ){
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        HashMap<String, String> params = new HashMap<>();
//                        params.put("phoneNumber", edtPhoneNumber.getText().toString());
//                        params.put("nameDevice", deviceName);
//                        params.put("timeDateSignIn", dateTimeSignIn);
//                        return params;
//                    }
//                };
//                requestQueue.add(stringRequest);
            }
        });

    }
    public void notifycation(String text, int gravity) {
        Dialog dialog = new Dialog(LoginActivity.this);
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
    CheckNetwork checkNetWork = new CheckNetwork();

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(checkNetWork,filter);
        super.onStart();
    }
}