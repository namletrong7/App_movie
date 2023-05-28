package com.example.app_movie.Home;

import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_movie.Adapter.TvAdapter;
import com.example.app_movie.Adapter.channelAdapter;
import com.example.app_movie.Model.channel;
import com.example.app_movie.Model.movie;
import com.example.app_movie.PLayVideo.PlayChannelActivity;
import com.example.app_movie.R;
import com.example.app_movie.Util.Server;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class TiViFragment extends Fragment {

    public static RecyclerView ry_Categorychannel,ry_channel,ry_AllChannel;
    View view ;
    ArrayList<String> listCategory ;
    TvAdapter adapterTV ;
    Spinner spinner_category ;
    public static  SimpleExoPlayer player;
    public static  PlayerView playerView;
    public static  String linkChannel ="https://vips-livecdn.fptplay.net/hda1/vtv1hd_vhls.smil/chunklist_b2500000.m3u8";
    public  HlsMediaSource mediaSource;
    public static ArrayList<channel> listAllChannel, listChannel ;
    public static   channelAdapter adapterChannel, adapterAllChannel ;
    public static  String categoryChannel ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ti_vi, container, false);
        init();
        event();
        getDataChannel();
        return view ;
    }
    public void  event(){
        view.findViewById(R.id.imgFullScreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlayChannelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("linkChannel",linkChannel);
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });
    }
    public void init(){
        player = new SimpleExoPlayer.Builder(getContext()).build();
        playerView = view.findViewById(R.id.playerView);
        playerView.setUseController(false);
        listCategory=new ArrayList<>();
        listCategory.add("VTV");
        listCategory.add("HTV");
        listCategory.add("VTC");
        listCategory.add("THVL");
        listCategory.add("Tất cả các kênh");
        adapterTV=new TvAdapter(getContext(), listCategory);
        ry_Categorychannel=view.findViewById(R.id.ry_Categorychannel);
        ry_Categorychannel.setAdapter(adapterTV);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        ry_Categorychannel.setLayoutManager(layoutManager);


        // phần channel
        ry_channel= view.findViewById(R.id.ry_channel); ry_channel.setVisibility(View.GONE);
        listChannel=new ArrayList<>();
        adapterChannel=new channelAdapter(getContext(), listChannel);
        ry_channel.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ry_channel.setAdapter(adapterChannel);
       // phần tất cả các kênh
        ry_AllChannel= view.findViewById(R.id.ry_AllChannel);
        listAllChannel=new ArrayList<>();
        adapterAllChannel=new channelAdapter(getContext(), listAllChannel);
        ry_AllChannel.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ry_AllChannel.setAdapter(adapterAllChannel);
       // phát kênh


    }
     public static  void playChannel(Context context){
         player = new SimpleExoPlayer.Builder(context).build();
         playerView.setPlayer(player);

         DataSource.Factory dataSourceFactory = new DefaultHttpDataSourceFactory();
//         HlsMediaSource mediaSource = new HlsMediaSource.Factory(dataSourceFactory).
//                 createMediaSource(MediaItem.fromUri(linkChannel));
         MediaItem mediaItem = MediaItem.fromUri(linkChannel);

// Cập nhật đối tượng MediaItem đang được phát
         player.setMediaItem(mediaItem);
     //    player.setMediaSource(mediaSource);
         player.prepare();
         player.setPlayWhenReady(true);
     }
    public static void setTypeChannel(){
          switch (categoryChannel){
            case "vtv":{  // chỉ lấy danh sách kênh VTV
                 listChannel.clear();
                 for(channel Channel: listAllChannel){
                     if(Channel.getTypeChannel().equals("vtv")){
                         listChannel.add(Channel);
                     }
                 }
                 break ;

            }
              case "htv":{  // chỉ lấy danh sách kênh VTC
                  listChannel.clear();
                  for(channel Channel: listAllChannel){
                      if(Channel.getTypeChannel().equals("htv")){
                          listChannel.add(Channel);
                      }
                  }
             break ;
              }
              case "vtc":{  // chỉ lấy danh sách kênh VTC
                  listChannel.clear();
                  for(channel Channel: listAllChannel){
                      if(Channel.getTypeChannel().equals("vtc")){
                          listChannel.add(Channel);
                      }
                  }
                  break ;
              }
              case "thvl":{  // chỉ lấy danh sách kênh VTC
                  listChannel.clear();
                  for(channel Channel: listAllChannel){
                      if(Channel.getTypeChannel().equals("thvl")){
                          listChannel.add(Channel);
                      }
                  }
                  break ;
              }


        }

    }
    public void getDataChannel(){
        String url = Server.getChannel;
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listAllChannel.clear();
                         if(response!=null){
                             for(int i = 0 ; i<= response.length();i++){
                                 try {
                                     JSONObject jsonObject = response.getJSONObject(i);
//                                     int id = jsonObject.getInt("idChannel");
//                                     String nameChannel=jsonObject.getString("nameChannel");
//                                     String thumbnailChannel=jsonObject.getString("thumbnailChannel");
//                                     String linkChannel=jsonObject.getString("linkChannel");
//                                     String typeChannel=jsonObject.getString("typeChannel");
//                                     String contentChannel=jsonObject.getString("contentChannel");
//                                     channel Channel = new channel(id, nameChannel,thumbnailChannel,linkChannel,typeChannel,contentChannel);
                                     channel Channel = new channel();
                                     Gson gson = new Gson();
                                     Channel = gson.fromJson(jsonObject.toString(), channel.class);
                                     listAllChannel.add(Channel);
                                 } catch (JSONException e) {
                                     e.printStackTrace();
                                 }

                             }
                             adapterAllChannel.notifyDataSetChanged();
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
    public static void releasePlayer() {
        if (player != null) {

            player.release();
            player = null;
            playerView.setPlayer(/* player= */ null);
        }
        
        playerView.getAdViewGroup().removeAllViews();
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onDestroy() {
        releasePlayer();
        super.onDestroy();
    }


}