package com.example.ljli.text;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContentProviderActivity extends AppCompatActivity {

    public List<String> contactList = new ArrayList<>(); //转载信息
    public ArrayAdapter<String> adapter;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        //数组适配器
        adapter = new ArrayAdapter<String>(ContentProviderActivity.this, android.R.layout.simple_list_item_1
                , contactList);
        ListView listView = (ListView) findViewById(R.id.listview_view);
        listView.setAdapter(adapter);
        //通过contentProvider获取通讯录信息
        Button mAddressBook = (Button) findViewById(R.id.addressBook_btn);
        mAddressBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ContentProviderActivity.this, Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ContentProviderActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
                } else {
                    showAddress();
                }
            }
        });

        //通过contentProvider获取短信
        Button mSms = (Button) findViewById(R.id.sms_btn);
        mSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ContentProviderActivity.this, Manifest.permission.READ_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ContentProviderActivity.this, new String[]{Manifest.permission.READ_SMS}, 2);
                } else {
                    showSms();
                }
            }
        });

        //通过contentProvider获取本地音乐
        Button mMedia = (Button) findViewById(R.id.media_btn);
        mMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ContentProviderActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ContentProviderActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                } else {
                    showMedia();
                }
            }
        });


    }

    public void showAddress() {
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String contacts = name + ":" + number;
                    Log.d("测试", contacts);
                    contactList.add(contacts);
                    adapter.notifyDataSetChanged();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }



    public void showSms(){
                Cursor cursor = null;
                Uri uri = Uri.parse("content://sms");
                String[] line = {"address", "date", "body"};
                try {
                    cursor = getContentResolver().query(uri,
                            null, null, null, null);
                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            String name = cursor.getString(cursor.getColumnIndex("address"));
                            String number = cursor.getString(cursor.getColumnIndex("body"));
                            String contacts = name + ":" + number;
                            contactList.add(contacts);
                            adapter.notifyDataSetChanged();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }




    public void showMedia(){
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    String number = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String contacts = name + ":" + number;
                    contactList.add(contacts);
                    adapter.notifyDataSetChanged();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }





//查看是否之后打开权限，并提醒
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                }else {
                    Toast.makeText(mContext,"请打开联系人权限",Toast.LENGTH_LONG).show();
                }
            case 2:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                }else {
                    Toast.makeText(mContext,"请打开短信权限",Toast.LENGTH_LONG).show();
                }
            case 3:
                if(grantResults.length>0 && grantResults[2]==PackageManager.PERMISSION_GRANTED){

                }else {
                    Toast.makeText(mContext,"请打开文件权限",Toast.LENGTH_LONG).show();
                }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ContentProvider","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ContentProvider","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ContentProvider","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ContentProvider","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ContentProvider","onDestroy");
    }
}
