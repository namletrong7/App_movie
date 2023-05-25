package com.example.app_movie.Personal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
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
import com.example.app_movie.Util.Server;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ChangeAvatarActivity extends AppCompatActivity {
     TextView tvUpLoadImg , btnCancel , btnUpdate ;
     RoundedImageView imgNewAvatar ;
     String encode ;
     Uri uriAvatar ;
     Bitmap bitmap;
     ProgressBar loading;
     String phoneNumber ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_avatar);
        phoneNumber= HomeActivity.phoneNumber;
        tvUpLoadImg=findViewById(R.id.tvUpLoadImg);
        btnCancel=findViewById(R.id.btnCancel);
        btnUpdate=findViewById(R.id.btnUpdate);
        imgNewAvatar=findViewById(R.id.imgNewAvatar);
        loading=findViewById(R.id.loading);
        event();
    }
    public void event(){
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            updateAvatar();
            }
        });
        tvUpLoadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(ChangeAvatarActivity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Chọn hình ảnh đại diện!"), 1);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permissionRequest, PermissionToken permissionToken) {

                            }
                        }).check();
            }
        });
    }
   public void updateAvatar(){
        loading.setVisibility(View.VISIBLE);

       RequestQueue requestQueue = Volley.newRequestQueue(ChangeAvatarActivity.this);
       StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.changeAvatar,
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       if (response.equals("1")) {
                           Toast.makeText(ChangeAvatarActivity.this, "Cập nhập thành công", Toast.LENGTH_SHORT).show();
                           loading.setVisibility(View.INVISIBLE);
                       } else {
                           Toast.makeText(ChangeAvatarActivity.this, "Cập nhập thất bại", Toast.LENGTH_SHORT).show();
                           loading.setVisibility(View.INVISIBLE);
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
               params.put("encode", encode);
               params.put("phoneNumber", phoneNumber);
               return params;
           }
       };
       ;
       requestQueue.add(stringRequest);
   }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (requestCode == 1 && resultCode == RESULT_OK && data != null){
             uriAvatar = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uriAvatar);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imgNewAvatar.setImageBitmap(bitmap);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] imageBytes = stream.toByteArray();
                encode = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}