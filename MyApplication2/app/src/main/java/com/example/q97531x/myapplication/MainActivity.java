package com.example.q97531x.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import net.tsz.afinal.FinalDb;

import adapter.MainPagerAdapter;
import model.User;

public class MainActivity extends FragmentActivity {
    ViewPager mainPager;
    FinalDb db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = FinalDb.create(this);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /*User user = new User();
        user.setHasPassword(false);
        db.save(user);*/
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView(){
        mainPager = (ViewPager)findViewById(R.id.mainPager);
        MainPagerAdapter myAdapter = new MainPagerAdapter(
                this.getSupportFragmentManager(), MainActivity.this);
        mainPager.setAdapter(myAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
