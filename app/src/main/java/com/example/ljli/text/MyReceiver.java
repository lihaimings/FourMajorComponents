package com.example.ljli.text;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //接收到广播要进行耗时的操作，开通子线程，因为广播的生命周期很短，所以要到服务中操作
        context.startService(new Intent(context,MyServices.class));
    }
}
