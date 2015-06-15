package com.example.anik.internetbroadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {
    private static boolean firstConnection = true;
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        // network not available, not connected
        if(networkInfo == null || !networkInfo.isConnected()){
            firstConnection = true;
            Log.v("NETWORK BROADCAST disconnection", "" + Math.random());
            showToast(context, "no network available");

        } else{
            // network is connected
            // handle the multiple broadcast.
            // don't listen to more than one broadcast
            if(firstConnection){
                showToast(context, "Network active, do operation");
                Log.v("NETWORK BROADCAST Connection", "" + Math.random());
                firstConnection = false;
            }
        }
    }

    private void showToast(final Context context, final String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
