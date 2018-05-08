package com.liubin.fenghui.annotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.liubin.fenghui.processors.Bind;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bind.getInstance().setOnBinListener(new BinImpl());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
