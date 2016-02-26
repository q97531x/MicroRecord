package com.example.q97531x.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;

import model.Type;
import util.TypeData;

//判断跳转至哪一页面
public class SplashActivity extends Activity {
    private FinalDb db;
    private String[] typeName = {
            "总预算","餐饮","购物","交通","娱乐","居家","医药","进修","人情","投资","其他",
            "生活费","兼职","奖金","分红","报销","工资","补贴","其他"
    };
    private int[] typeIcon = {
        R.drawable.icons_all,R.drawable.icons_food,R.drawable.icons_shop,R.drawable.icons_traffic,
            R.drawable.icons_entertainment,R.drawable.icons_home,R.drawable.icons_health,R.drawable.icons_study,R.drawable.icons_dividend,
            R.drawable.icons_stocks,R.drawable.icons_others,R.drawable.icons_parents,R.drawable.icons_parttimejib,
            R.drawable.icons_prize,R.drawable.icons_dividend,R.drawable.icons_writeoff,R.drawable.icons_work,R.drawable.icons_supply,R.drawable.icons_others
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        db = FinalDb.create(this);
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Message msg = hand.obtainMessage();
                hand.sendMessage(msg);
            }

        }.start();
    }
    Handler hand = new Handler() {
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            if (isFristRun()) {
                Type type = new Type();
                for(int i = 0;i<typeName.length;i++){
                    Log.e("splash",""+typeName.length);
                    if(i<11){
                        type.setOutcomeTypeName(typeName[i]);
                        type.setOutcomeTypeIcon(typeIcon[i]);
                        db.save(type);
                    }else{
                        type.setIncomeTypeName(typeName[i]);
                        type.setIncomeTypeIcon(typeIcon[i]);
                        db.save(type);
                    }
                }

                Intent intent = new Intent(SplashActivity.this,
                        MainActivity.class);
                startActivity(intent);
            } else {

                Intent intent = new Intent(SplashActivity.this,
                        FrameActivity.class);
                startActivity(intent);
            }
            finish();
        }
    };
    private boolean isFristRun() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(
                "share", MODE_PRIVATE);
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!isFirstRun) {
            return false;
        } else {
            editor.putBoolean("isFirstRun", false);

            editor.commit();
            return true;
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {

        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
