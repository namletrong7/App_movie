package com.example.app_movie.Personal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_movie.Adapter.DeviceAdapter;
import com.example.app_movie.Model.device;
import com.example.app_movie.R;
import com.example.app_movie.Util.Server;
import com.jaredrummler.android.device.DeviceName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DeviceActivity extends AppCompatActivity {
   public    RecyclerView ly_device ;
      public ArrayList<device> listDevice ;
      public DeviceAdapter adapterDevice ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        init(); //ánh xạ
        getDevicePresent();//// lấy tên thiết bị hiện tại
        Handler handler = new Handler();
        getDevice();
    }




    private void getDevicePresent() {  // lấy tên thiết bị hiện tại
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
        ly_device=(RecyclerView) findViewById(R.id.ly_device);
        listDevice=new ArrayList<>();
        String idDevice , phoneNumber, nameDevice, codeDevice , timeDateSignIn  ;
        adapterDevice=new DeviceAdapter(DeviceActivity.this, listDevice);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ly_device.setLayoutManager(layoutManager);
        ly_device.setAdapter(adapterDevice);
        adapterDevice.notifyDataSetChanged();

    }
    public  void getDevice(){
        String url= Server.getDevice;
        RequestQueue requestQueue = Volley.newRequestQueue(DeviceActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response!=null){
                             listDevice.clear();
                            for(int i=0 ; i<=response.length();i++){
//                                int idDevice ;
//                                String phoneNumber, nameDevice , timeDateSignIn  ;
                                try {
                                    JSONObject device = response.getJSONObject(i);
                                    device dv  = new device(0,"",device.getString("nameDevice"),device.getString("timeDateSigin"));
                                    listDevice.add(dv);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                        }
                        adapterDevice.notifyDataSetChanged();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        ) ;
        requestQueue.add(jsonArrayRequest);
    }
}