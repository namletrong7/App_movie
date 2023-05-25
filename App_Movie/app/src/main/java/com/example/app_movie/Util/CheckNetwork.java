package com.example.app_movie.Util;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.example.app_movie.R;

public class CheckNetwork extends BroadcastReceiver {
    // kiểm tra kết nối internet
     Boolean check  ;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Common.haveNetworkConnection(context)) {
            Toast.makeText(context, "Mất kết nối internet", Toast.LENGTH_SHORT).show();

            if (Common.haveNetworkConnection(context)){
                Toast.makeText(context, "Khôi phục kết nối mạng", Toast.LENGTH_SHORT).show();
            }
        }


//    public void thongBao(int gravity, Context context){
//        Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.dialog_no_internet); // xét content view cho dialog
//        Window window = dialog.getWindow();
//        if (window == null) {
//            return;
//        }
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        WindowManager.LayoutParams windowAttributes = window.getAttributes();
//        windowAttributes.gravity = gravity;
//        window.setAttributes(windowAttributes);
//        dialog.show();
//    }
    }
}