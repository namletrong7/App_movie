package com.example.app_movie.Personal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
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
import com.example.app_movie.Home.UserFragment;
import com.example.app_movie.Login.ChangePasswordActivity;
import com.example.app_movie.Login.LoginActivity;
import com.example.app_movie.MainActivity;
import com.example.app_movie.Model.user;
import com.example.app_movie.R;
import com.example.app_movie.Util.CheckNetwork;
import com.example.app_movie.Util.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.callback.CallbackHandler;

public class InformationAccountActivity extends AppCompatActivity {
    String phoneNumber, userName, sex;
    TextView tvPhone, tvPass, tvSex, tvName, tvBirthday;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_account);
        phoneNumber = HomeActivity.phoneNumber;
        init();
        getUserInfor();
        event();
    }

    public void init() {
        tvPhone = findViewById(R.id.tvPhoneNumber);
        tvPass = findViewById(R.id.tvPassword);
        tvSex = findViewById(R.id.tvSex);
        tvName = findViewById(R.id.tvUserName);
        tvBirthday = findViewById(R.id.tvBirthday);
    }

    public void event() {
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();

        });
        tvName.setOnClickListener(v -> {
            changeName(100);


        });
        tvSex.setOnClickListener(v -> {
            changeSex(200);
        });
        tvPass.setOnClickListener(v -> {
            Intent intent = new Intent(InformationAccountActivity.this, ChangePasswordActivity.class);
            startActivity(intent);
        });

        // -------lấy ngày tháng năm hiện tại của hệ thống
        Calendar calender = Calendar.getInstance();
        int nam = calender.get(Calendar.YEAR);
        int thang = calender.get(Calendar.MONTH);
        int ngay = calender.get(Calendar.DAY_OF_MONTH);
        tvBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(InformationAccountActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int thang = month + 1;
                        String birthday = dayOfMonth + "/" + thang + "/" + year;
                        Toast.makeText(InformationAccountActivity.this, birthday, Toast.LENGTH_SHORT).show();
                        changeBirthday(birthday);
                    }
                    // lấy ngày tháng hiện tại để hiển thị mặc định khi ta mở ra
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });
    }

    public void changeSex(int gravity) {
        String linkChangeSex = Server.changeSex;
        Dialog dialog = new Dialog(InformationAccountActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_change_sex); // xét content view cho dialog
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        TextView btnCancel = dialog.findViewById(R.id.btnCancel);
        TextView btnUpdate = dialog.findViewById(R.id.btnUpdate);
        RadioGroup groupSex = dialog.findViewById(R.id.groupSex);
        groupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btnNam: {
                        sex = "Nam";
                        break;
                    }
                    case R.id.btnNu: {
                        sex = "Nữ";
                        break;
                    }
                    case R.id.btnKhac: {
                        sex = "Khác";
                        break;
                    }
                }
            }
        });
        btnCancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        btnUpdate.setOnClickListener(v -> {
            RequestQueue requestQueue = Volley.newRequestQueue(InformationAccountActivity.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, linkChangeSex,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("1")) {
                                dialog.dismiss();
                                getUserInfor(); // sau khi thay đổ xong thi gọi lại hàm này để cập nhập lại thoong tin trên màn hình
                                notifycation("Cập nhập giới tính thành công", 200);
                            } else {
                                dialog.dismiss();
                                getUserInfor();
                                notifycation("Cập nhập giới tính thất bại", 200);
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
                    params.put("sex", sex);
                    params.put("phoneNumber", phoneNumber);
                    return params;
                }
            };
            ;
            requestQueue.add(stringRequest);
        });


        window.setAttributes(windowAttributes);
        dialog.show();
    }

    public void changeBirthday(String birthday) {
        String linkChangeBirthday = Server.changeBirthday;
        RequestQueue requestQueue = Volley.newRequestQueue(InformationAccountActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, linkChangeBirthday,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("1")) {
                            notifycation("Cập nhập ngày sinh thành công", 200);
                            getUserInfor();
                        } else {
                            notifycation("Cập nhập ngày sinh thất bại", 200);
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
                params.put("birthday", birthday);
                params.put("phoneNumber", phoneNumber);
                return params;
            }
        };
        ;
        requestQueue.add(stringRequest);
    }

    public void changeName(int gravity) {
        String linkChangeName = Server.changeName;
        Dialog dialog = new Dialog(InformationAccountActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_change_name); // xét content view cho dialog
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        EditText edtUserName = dialog.findViewById(R.id.edtUserName);
        edtUserName.setText(userName);
        TextView btnCancel = dialog.findViewById(R.id.btnCancel);
        TextView btnUpdate = dialog.findViewById(R.id.btnUpdate);

        btnCancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        btnUpdate.setOnClickListener(v -> {
            RequestQueue requestQueue = Volley.newRequestQueue(InformationAccountActivity.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, linkChangeName,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("1")) {
                                dialog.dismiss();
                                getUserInfor();
                                notifycation("Cập nhập tên thành công", 200);
                            } else {
                                dialog.dismiss();
                                getUserInfor();
                                notifycation("Cập nhập tên thất bại", 200);
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
                    params.put("userName", edtUserName.getText().toString().trim());
                    params.put("phoneNumber", phoneNumber);
                    return params;
                }
            };
            ;
            requestQueue.add(stringRequest);
        });


        window.setAttributes(windowAttributes);
        dialog.show();
    }

    public void getUserInfor() {
        RequestQueue requestQueue = Volley.newRequestQueue(InformationAccountActivity.this);
        String url = Server.getUserInfor;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i <= jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String phoneNumber = jsonObject.getString("phoneNumber");
                                tvPhone.setText(phoneNumber);
                                String pass = jsonObject.getString("password");
                                tvPass.setText(pass);
                                String nameUser = jsonObject.getString("nameUser");
                                tvName.setText(nameUser);
                                userName = nameUser;
                                String sex = jsonObject.getString("sex");
                                tvSex.setText(sex);
                                String birthday = jsonObject.getString("birthday");
                                tvBirthday.setText(birthday);
                                //    String avatar = jsonObject.getString("avatar");
                                //       String linkAvatar = Server.getAvatar+avatar;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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
                return params;
            }
        };
        ;
        requestQueue.add(stringRequest);
    }

    public void notifycation(String text, int gravity) {
        Dialog dialog = new Dialog(InformationAccountActivity.this);
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