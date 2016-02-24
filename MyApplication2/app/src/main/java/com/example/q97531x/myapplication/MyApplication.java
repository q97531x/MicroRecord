package com.example.q97531x.myapplication;

import android.app.Application;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 * Created by q97531x on 2015/11/21.
 */
public class MyApplication extends Application{

    public boolean isLocked = true;//判断是否锁定
    public String password;//密码字符串

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
