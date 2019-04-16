package com.example.ljli.text;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public Context mContext=this;
    MyDynamicReceiver myDynamicReceiver=new MyDynamicReceiver();  //动态广播接收器
    private MyThread myThread; //继承子线程类
    private MyServices.mm aa;
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivity.this,"成功绑定",Toast.LENGTH_SHORT);
            aa=(MyServices.mm)service;
            aa.startTest();
           // aa.getProgress();
            aa.showToast(mContext);

        }
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    //onCreate.onRemuse的函数不要放那么多复杂的代码，小心启动页慢。
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Testjava testjava=new Testjava();
        //再java文件中去启动activity
        Button textBtn=(Button)findViewById(R.id.text_btn);
        textBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testjava.intentActivity(getApplicationContext());
            }
        });
    //开始服务
        Button serviseBtn=(Button)findViewById(R.id.textServier_btn);
        serviseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bindIntent=new Intent(MainActivity.this,MyServices.class);
               bindService(bindIntent,connection,BIND_AUTO_CREATE);
                //startService(bindIntent);
            }
        });
       //stop服务
        Button unserviseBtn=(Button)findViewById(R.id.UnServier_btn);
        unserviseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              unbindService(connection);
               // Intent bindIntent=new Intent(MainActivity.this,MyServices.class);
               // stopService(bindIntent);

            }
        });
       //启动intentservice的服务
        Button startIntentService=(Button)findViewById(R.id.IntentServier_btn);
        startIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this,MyIntentService.class));
            }
        });

        //发送广播 接收优先级，动态注册>静态注册
        Button sendReceiver=(Button)findViewById(R.id.sendReceiver_btn);
        sendReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent("hello.i.am.comming");
                sendBroadcast(mIntent);
                //有序广播
                //sendOrderedBroadcast(mIntent,null);
                //本地广播
//                LocalBroadcastManager lbm=LocalBroadcastManager.getInstance(MainActivity.this);
//                lbm.sendBroadcast(mIntent);

            }
        });

        //动态注册广播接收器
        registerReceiver(myDynamicReceiver,new IntentFilter("hello.i.am.comming"));
      //本地广播接收器   lbm.registerReceiver(myDynamicReceiver,new IntentFilter("hello.i.am.comming"));


        //转跳contentProvider实现获取手机通讯录、短信、媒体库等
        Button mContentProvider=(Button)findViewById(R.id.contentProcider_btn);
        mContentProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ContentProviderActivity.class));
            }
        });

// 测试子线程再activity结束下，子线程是否结束
        myThread=new MyThread();
      //  myThread.start();

    }

    public class MyThread extends Thread{
      private boolean stop=false;

        @Override
        public void run() {
            super.run();
            while(!stop){
                try{
                    Thread.sleep(1000);
                    Log.d("子线程","running");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

        public void close(){
            stop=true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myDynamicReceiver);
        //本地广播接收器   lbm.unregisterReceiver(myDynamicReceiver);
        myThread.close();
        Log.d("MainActivity","onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity","onStop");
    }

}
