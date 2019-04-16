package com.example.ljli.text;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyServices extends Service {
  Context mContext=this;
    //stsrt服务的生命周期   onCreate()->onStartCommand->onDestory()
    //bind服务的生命周期   onCreate()->onBind->onUnBind()->onDestory()
    private mm mBinder=new mm();
    class mm extends Binder{
        public void startTest(){
            String name=Thread.currentThread().getName();
            Log.d("这个线程的名字",name);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String name=Thread.currentThread().getName();
                    Log.d("这个子线程的名字",name);
                }
            }).start();


//            Intent secondAct=new Intent(mContext,SecondActivity.class);
//           // secondAct.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            mContext.startActivity(secondAct);
        }

        public void showToast(Context context){
            Toast.makeText(context,"服务开始",Toast.LENGTH_LONG).show();
        }
    }


    public MyServices() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("调用","unbind");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String name=Thread.currentThread().getName();
        Log.d("这个线程的名字",name);  //主线程中运行
        new Thread(new Runnable() {
            @Override
            public void run() {
                String name=Thread.currentThread().getName();
                Log.d("这个子线程的名字",name);   //子线程中运行
            }
        }).start();
        Intent secondAct=new Intent(this,SecondActivity.class);
       // secondAct.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        this.startActivity(secondAct);
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("调用","onDestroy");
        super.onDestroy();
    }
}
