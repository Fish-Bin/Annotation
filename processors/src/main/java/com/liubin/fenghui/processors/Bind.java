package com.liubin.fenghui.processors;

/**
 * Created by Administrator on 2017/12/8.
 */

public class Bind {

    public static Bind mInstance;

    public static Bind getInstance(){

        if(mInstance==null){
            mInstance=new Bind();
        }
        return mInstance;
    }

    public IBin mBin;

    public void setOnBinListener(IBin bin){
        this.mBin=bin;
    }
    public void init(){
        if(mBin!=null){
            mBin.run();
        }
    }
}
