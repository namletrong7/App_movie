package com.example.app_movie.Home;

import android.app.VoiceInteractor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_movie.Adapter.TvAdapter;
import com.example.app_movie.Adapter.channelAdapter;
import com.example.app_movie.Model.channel;
import com.example.app_movie.R;
import com.example.app_movie.Util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class TiViFragment extends Fragment {

    RecyclerView ry_Categorychannel,ry_channel;
    View view ;
    ArrayList<String> listCategory ;
    TvAdapter adapterTV ;
    ArrayList<channel> listChannel ;
    channelAdapter adapterChannel ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ti_vi, container, false);
        init();
        getDataChannel();
        return view ;
    }
    public void init(){
        listCategory=new ArrayList<>();
        listCategory.add("VTV");
        listCategory.add("HTV");
        listCategory.add("VTVAcab");
        listCategory.add("VTV");
        listCategory.add("HTVC");
        listCategory.add("THVL");
        listCategory.add("Khác");
        listCategory.add("Địa phương");
        adapterTV=new TvAdapter(getContext(), listCategory);
        ry_Categorychannel=view.findViewById(R.id.ry_Categorychannel);
        ry_Categorychannel.setAdapter(adapterTV);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        ry_Categorychannel.setLayoutManager(layoutManager);

        // phần channel
        ry_channel= view.findViewById(R.id.ry_channel);
        listChannel=new ArrayList<>();
        adapterChannel=new channelAdapter(getContext(), listChannel);
        ry_channel.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ry_channel.setAdapter(adapterChannel);
    }
    public void getDataChannel(){
        String url = Server.getChannel;
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                         listChannel.clear();
                         if(response!=null){
                             for(int i = 0 ; i<= response.length();i++){
                                 try {
                                     JSONObject jsonObject = response.getJSONObject(i);
                                     int id = jsonObject.getInt("idChannel");
                                     String nameChannel=jsonObject.getString("nameChannel");
                                     String thumbnailChannel=jsonObject.getString("thumbnailChannel");
                                     String linkChannel=jsonObject.getString("linkChannel");
                                     String typeChannel=jsonObject.getString("typeChannel");
                                     String contentChannel=jsonObject.getString("contentChannel");
                                     channel Channel = new channel(id, nameChannel,thumbnailChannel,linkChannel,typeChannel,contentChannel);
                                     listChannel.add(Channel);
                                 } catch (JSONException e) {
                                     e.printStackTrace();
                                 }

                             }
                             adapterChannel.notifyDataSetChanged();
                         }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}