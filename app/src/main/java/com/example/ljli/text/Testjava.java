package com.example.ljli.text;

import android.content.Context;
import android.content.Intent;

/**
 * Created by LJli on 2019/4/14.
 */

public class Testjava {
    public void intentActivity(Context context){
        Intent intent=new Intent(context,SecondActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
