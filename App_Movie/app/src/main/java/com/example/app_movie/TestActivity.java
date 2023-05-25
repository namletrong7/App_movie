package com.example.app_movie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_movie.Adapter.SliderAdapter;
import com.example.app_movie.Model.movie;
import com.example.app_movie.Model.video;
import com.example.app_movie.Util.Server;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestActivity extends AppCompatActivity {
   private VideoView videoView ;
   private Button btnClick,btnUpload ;
   private Uri urlVideo ;
    String encodedVideo;
    ProgressBar loading ;
    TextView tvBase ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        videoView=findViewById(R.id.videoView);
        btnClick=findViewById(R.id.btnClick);
        btnUpload=findViewById(R.id.btnUpload);
        tvBase=findViewById(R.id.tvBase);
        loading=findViewById(R.id.loading); loading.setVisibility(View.INVISIBLE);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(TestActivity.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                Intent intent = new Intent();
                                intent.setType("video/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(intent,1);

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {



                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permissionRequest, PermissionToken permissionToken) {

                            }

                        }).check();
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         //       tvBase.setText(getBase64FromVideoUri(urlVideo));
                loading.setVisibility(View.VISIBLE);
                String url="http://192.168.1.101:8080/movie/upLoadVideo.php" ;
                RequestQueue requestQueue = Volley.newRequestQueue(TestActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                  if(response.equals("1")){
                                      Toast.makeText(TestActivity.this, "up load video thành công", Toast.LENGTH_SHORT).show();
                                      loading.setVisibility(View.INVISIBLE);
                                  }else if(response.equals("0")){
                                      Toast.makeText(TestActivity.this, "up load video thất bại", Toast.LENGTH_SHORT).show();
                                      loading.setVisibility(View.INVISIBLE);
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
                        params.put("encodedVideo", encodedVideo);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);

            }
        });

    }
    public String getRealPathFromURI(Uri uri) {  // phương thức này giúp
        String filePath = "";
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            filePath = cursor.getString(columnIndex);
            cursor.close();
        }
        return filePath;
    }
    public String getBase64FromVideoUri(Uri videoUri) {
        InputStream inputStream = null;
        try {
            inputStream = getContentResolver().openInputStream(videoUri);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] videoBytes = outputStream.toByteArray();
            return Base64.encodeToString(videoBytes, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            urlVideo = data.getData();
            videoView.setVideoURI(urlVideo);
            videoView.requestFocus();
            videoView.start();
            encodedVideo = android.util.Base64.encodeToString(UploadHelper.getFileDataFromDrawable(TestActivity.this,urlVideo),Base64.DEFAULT);
       //      Lấy đường dẫn tuyệt đối của tập tin video
//            String videoPath= getRealPathFromURI(urlVideo);
//            File videoFile = new File(videoPath);

// Đọc dữ liệu từ tập tin video và mã hóa thành chuỗi Base64
//            try {
//                InputStream inputStream = new FileInputStream(videoFile);
//                byte[] bytes = new byte[inputStream.available()];
//                inputStream.read(bytes);
//             //   encodedVideo = android.util.Base64.encodeToString(bytes, Base64.DEFAULT);
//                String videoData="";
//                //Converting bytes into base64
//                videoData = Base64.encodeToString(bytes, Base64.DEFAULT);
//                Log.d("VideoData**>  " , videoData);
//
//                String sinSaltoFinal2 = videoData.trim();
//                String sinsinSalto2 = sinSaltoFinal2.replaceAll("\n", "");
//                Log.d("VideoData**>  " , sinsinSalto2);
//
//                encodedVideo = sinsinSalto2;
//                tvBase.setText(encodedVideo);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }








        }
    }
}