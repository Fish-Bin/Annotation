package com.liubin.fenghui.annotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.liubin.fenghui.annotations.MyAnnotation;

public class HuiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
