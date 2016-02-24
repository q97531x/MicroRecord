package com.example.q97531x.myapplication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.layouts.DataBindingInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.example.q97531x.myapplication.databinding.ActivityMainBinding;
import com.example.q97531x.myapplication.model.User;

public class MainActivity extends AppCompatActivity implements Runnable{
    private User user;
    private ActivityMainBinding binding;
    private Button btn;
//    private boolean isFirst= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setName("weibo");
//                binding.executePendingBindings();
                Log.e("set",""+user.getName());
            }
        });
        user = new User("weeboos",false);
        binding.setUser(user);
        user.setName("wee");
    }

    @Override
    public void run() {

    }

    @Override
    protected void onResume() {
        super.onResume();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
