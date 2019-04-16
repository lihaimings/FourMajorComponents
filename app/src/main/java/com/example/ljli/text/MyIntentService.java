package com.example.ljli.text;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by LJli on 2019/4/15.
 */

public class MyIntentService extends IntentService {

    public MyIntentService(){
        super("MyIntentService");  //必须有这个
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //这里执行服务的耗时操作
        //先看看他的线程名字
       long  name=Thread.currentThread().getId();
        Log.d("子线程名字",""+name);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("调用","onDestory");
    }
}
