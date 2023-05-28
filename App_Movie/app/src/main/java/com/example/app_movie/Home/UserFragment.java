package com.example.app_movie.Home;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.app_movie.Login.LoginActivity;
import com.example.app_movie.Login.SignUpActivity;
import com.example.app_movie.Personal.ChangeAvatarActivity;
import com.example.app_movie.Personal.DeviceActivity;
import com.example.app_movie.Personal.InformationAccountActivity;
import com.example.app_movie.Personal.ListFavoriteActivity;
import com.example.app_movie.R;
import com.example.app_movie.Util.Server;
import com.google.firebase.database.FirebaseDatabase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class UserFragment extends Fragment {
     TextView tvDevice,tvPhoneNumber,tvMyfavorite ,tvInforAccount,tvSignOut, tvUserName;
    View view ;
    ImageView imgChangeAvatar ;
    RoundedImageView imgAvatarUser ;
    Bitmap bitmap;
    String encodeImage1;
    Uri uriNewAvatar ;
    FirebaseDatabase database ;
   static String phoneNumber;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_user, container, false);
        database=FirebaseDatabase.getInstance();
        init();
        event();
        getUserInfor();
        return view ;
    }
    public void init(){
        imgChangeAvatar=view.findViewById(R.id.imgChangeAvatar);
        imgAvatarUser=view.findViewById(R.id.imgAvatarUser);
        tvUserName=view.findViewById(R.id.tvUserName);
        phoneNumber=HomeActivity.phoneNumber;
        tvDevice=view.findViewById(R.id.tvDevice);
        tvSignOut=view.findViewById(R.id.tvSignOut);
        tvPhoneNumber=view.findViewById(R.id.tvPhoneNumber);
        tvPhoneNumber.setText(phoneNumber);
        tvMyfavorite=view.findViewById(R.id.tvMyfavorite);
        tvInforAccount=view.findViewById(R.id.tvInforAccount);
    }
    public void event(){
        imgChangeAvatar.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), ChangeAvatarActivity.class));
        });
        tvDevice.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DeviceActivity.class);
            startActivity(intent);
        });
        tvMyfavorite.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ListFavoriteActivity.class);
            startActivity(intent);
        });
        tvInforAccount.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), InformationAccountActivity.class);
            startActivity(intent);
        });
        tvSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut(150);
            }
        });
    }
    public void signOut(int gravity){
        String linkChangeName = Server.changeName;
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sigout); // xét content view cho dialog
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        TextView btnCancel = dialog.findViewById(R.id.btnCancel);
        TextView btnOK = dialog.findViewById(R.id.btnOK);

        btnCancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        btnOK.setOnClickListener(v -> {
            TiViFragment.linkChannel="https://vips-livecdn.fptplay.net/hda1/vtv1hd_vhls.smil/chunklist_b2500000.m3u8";
             database.getReference("device").child(LoginActivity.dv1.getIdDevice()).removeValue();
             getActivity().finish();
             Intent intent = new Intent(getContext(), LoginActivity.class);
             startActivity(intent);
        });


        window.setAttributes(windowAttributes);
        dialog.show();
    }
    public void getUserInfor() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = Server.getUserInfor;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i <= jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String userName= jsonObject.getString("nameUser");
                                tvUserName.setText(userName);
                                String avatar= jsonObject.getString("avatar");
                                String linkAvatar = Server.getAvatar + avatar;
                                Picasso.get().load(linkAvatar).into(imgAvatarUser);
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

    @Override
    public void onResume() {
        super.onResume();
        getUserInfor();
        Log.d("USER", "onresume");
    }
}