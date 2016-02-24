package com.example.q97531x.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import net.tsz.afinal.FinalDb;

import java.util.List;

import cn.bmob.v3.Bmob;
import model.User;

public class BaseActivity extends AppCompatActivity {
    MyApplication myApplication;
    public boolean hasPassword = false;//判断是否设置了密码
    FinalDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this,"ceb49bcc137f6f2ec5de0326822a33b3");
    }

    protected void onResume() {
        super.onResume();
        /*db = FinalDb.create(this);
        List<User> userList = db.findAll(User.class);
        if (userList.size() > 0) {
            hasPassword = userList.get(0).getHasPassword();
        }
        myApplication = (MyApplication) getApplication();
        Toast.makeText(BaseActivity.this, "" + hasPassword, Toast.LENGTH_SHORT).show();
        if (myApplication.isLocked && hasPassword) {//判断是否跳转到输入密码界面
            Intent intent = new Intent(this, CheckPasswordActivity.class);
            startActivity(intent);
        }*/

    }
}
