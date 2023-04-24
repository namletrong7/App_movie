package com.example.app_movie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_movie.Model.device;
import com.example.app_movie.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DeviceAdapter extends  RecyclerView.Adapter<DeviceAdapter.deviceViewHolder> {
    Context context ;
    ArrayList<device> list ;

    public DeviceAdapter(Context context, ArrayList<device> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public deviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate
                (R.layout.item_device, parent, false);
        return new deviceViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull deviceViewHolder holder, int position) {
        device dv=list.get(position);
        holder.tvNameDevice.setText("Điện thoại "+dv.getNameDevice());
        holder.tvTimeDateSignIn.setText(dv.getTimeDateSignIn());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class deviceViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNameDevice , tvTimeDateSignIn ;

        public deviceViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameDevice=itemView.findViewById(R.id.tvNameDevie);
            tvTimeDateSignIn=itemView.findViewById(R.id.tvTimeDateSignIn);

        }
    }
}
