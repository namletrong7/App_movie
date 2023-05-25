package com.example.app_movie.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.example.app_movie.R;
import com.example.app_movie.Util.CheckNetwork;

public class ListMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);
    }
    CheckNetwork checkNetWork = new CheckNetwork();

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(checkNetWork,filter);
        super.onStart();
    }
}
