package com.example.app_movie.Personal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_movie.Adapter.DeviceAdapter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DeviceActivity extends AppCompatActivity {
   public    RecyclerView ly_device ;
      public ArrayList<device> listDevice ;
      public DeviceAdapter adapterDevice ;
      ImageView  btnBack ;
      TextView tvTimeDateSignIn ;
      FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        database=FirebaseDatabase.getInstance();
        init(); //ánh xạ
        getDeviceAndTimePresent();// thực hiện lấy ngày giờ và thiết bị hiện tại
        Handler handler = new Handler();
           getDevice();
        event() ;
    }


    public void event(){
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void getDeviceAndTimePresent() {  // lấy tên thiết bị hiện tại
        // lấy giờ và ngày tháng năm hiện tại
        String PATTERN = "HH:mm dd/MM/yyyy ";
        DateFormat df = new SimpleDateFormat(PATTERN);
        Date today = Calendar.getInstance().getTime();
        String dateTimeSignIn = df.format(today).toString();
        tvTimeDateSignIn.setText(dateTimeSignIn);



      //   lấy thông tin thiết bị hiện tại
        DeviceName.with(this).request(new DeviceName.Callback() {
            @Override public void onFinished(DeviceName.DeviceInfo info, Exception error) {
                String manufacturer = info.manufacturer;  // "Samsung"
                String name = info.marketName;            // "Galaxy S8+"
                String model = info.model;                // "SM-G955W"
                String codename = info.codename;          // "dream2qltecan"
                String deviceName = info.getName();       // "Galaxy S8+
                TextView tv= findViewById(R.id.tvNameDevie);
                tv.setText("Điện thoại "+deviceName);
            }
        });
    }

    public  void init(){
        tvTimeDateSignIn=findViewById(R.id.tvTimeDateSignIn);
        btnBack=findViewById(R.id.btnBack);
        ly_device=(RecyclerView) findViewById(R.id.ly_device);
        listDevice=new ArrayList<>();
        adapterDevice=new DeviceAdapter(DeviceActivity.this, listDevice);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ly_device.setLayoutManager(layoutManager);
        ly_device.setAdapter(adapterDevice);
        adapterDevice.notifyDataSetChanged();

    }
    public  void getDevice(){
//        String url= Server.getDevice;
//        RequestQueue requestQueue = Volley.newRequestQueue(DeviceActivity.this);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        if(response!=null){
//                             listDevice.clear();
//                            for(int i=0 ; i<=response.length();i++){
////                                int idDevice ;
////                                String phoneNumber, nameDevice , timeDateSignIn  ;
//                                try {
//                                    JSONObject device = response.getJSONObject(i);
//                                    device dv  = new device(0,"",device.getString("nameDevice"),device.getString("timeDateSigin"));
//                                    listDevice.add(dv);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//                            }
//
//                        }
//                        adapterDevice.notifyDataSetChanged();
//
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }
//
//        ) ;
//        requestQueue.add(jsonArrayRequest);

        database.getReference("device").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    listDevice.clear();
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                       device dv = snapshot1.getValue(device.class);
                        if (dv.getPhoneNumber().equals(HomeActivity.phoneNumber)){
                            listDevice.add(dv);
                        }


                    }
                    adapterDevice.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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