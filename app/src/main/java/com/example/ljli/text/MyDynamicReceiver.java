package com.example.ljli.text;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by LJli on 2019/4/15.
 */

public class MyDynamicReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("hello.i.am.comming")){
            Toast.makeText(context,"测试中...",Toast.LENGTH_LONG).show();
        }

    }
}
