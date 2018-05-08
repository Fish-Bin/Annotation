package com.liubin.fenghui.annotation;

import android.os.Bundle;
import android.util.Log;

import com.liubin.fenghui.annotations.BindAnnotation;


public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

   @BindAnnotation()
    public void get(){
       Log.i(TAG, "get: 成功了$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    }
}
