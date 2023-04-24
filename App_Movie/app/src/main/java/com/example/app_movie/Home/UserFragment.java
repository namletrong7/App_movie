package com.example.app_movie.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app_movie.Personal.DeviceActivity;
import com.example.app_movie.R;


public class UserFragment extends Fragment {

    TextView tvDevice ;
 View view ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_user, container, false);
        init();
        event();
        return view ;
    }
    public void init(){
        tvDevice=view.findViewById(R.id.tvDevice);

    }
    public void event(){
        tvDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DeviceActivity.class);
                startActivity(intent);
            }
        });
    }
}