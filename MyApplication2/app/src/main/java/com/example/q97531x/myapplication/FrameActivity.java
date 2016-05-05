package com.example.q97531x.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import Base.App;
import cn.bmob.push.BmobPush;
import cn.bmob.v3.BmobInstallation;
import fragment.AlarmFragment;
import fragment.BudgetFragment;
import fragment.DetailFragment;
import fragment.MoreFragment;
import fragment.ReportFragment;
import util.GlideCircleTransform;
import util.Utils;

/**
 * Created by Administrator on 2015/7/27.
 */
public class FrameActivity extends BaseActivity implements View.OnClickListener{
    //Context ctx;
    FinalDb db;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    LinearLayout fragmentBox;
    RelativeLayout ll_detail,ll_report,ll_remind,ll_budget,ll_more;
    LinearLayout ll_login;
    ImageView detailImage,reportImage,remindImage,budgetImage,moreImage,icon_login;
    TextView detailText,reportText,remindText,budgetText,moreText,tx_login;
    Calendar calendar;
    int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = FinalDb.create(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        // 使用推送服务时的初始化操作
        BmobInstallation.getCurrentInstallation(this).save();
        // 启动推送服务
        BmobPush.startWork(this, "ceb49bcc137f6f2ec5de0326822a33b3");

        initToolbar();
        DetailFragment deFragment = new DetailFragment();
        initView();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentBox, deFragment).commit();
        calendar = Calendar.getInstance();
//        initClick();
        Log.e("FrameActivity", ""+calendar.DAY_OF_MONTH+calendar.MONTH);
    }
    public void initToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("明细");
        //toolbar.setSubtitle("2016.2.25");
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,
                R.string.drawer_close);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void initView(){
        ll_login = (LinearLayout)findViewById(R.id.ll_login);
        ll_detail = (RelativeLayout)findViewById(R.id.ll_detail);
        ll_report = (RelativeLayout)findViewById(R.id.ll_report);
        ll_remind = (RelativeLayout)findViewById(R.id.ll_remind);
        ll_budget = (RelativeLayout)findViewById(R.id.ll_budget);
        ll_more = (RelativeLayout)findViewById(R.id.ll_more);
        //图片初始化
        detailImage = (ImageView)findViewById(R.id.icon_detail);
        reportImage = (ImageView)findViewById(R.id.icon_report);
        remindImage = (ImageView)findViewById(R.id.icon_remind);
        budgetImage = (ImageView)findViewById(R.id.icon_budget);
        moreImage = (ImageView)findViewById(R.id.icon_more);
        icon_login = (ImageView)findViewById(R.id.icon_login);
        //文字初始化
        detailText = (TextView)findViewById(R.id.detail);
        reportText = (TextView)findViewById(R.id.report);
        remindText = (TextView)findViewById(R.id.remind);
        budgetText = (TextView)findViewById(R.id.budget);
        moreText = (TextView)findViewById(R.id.more);
        tx_login = (TextView)findViewById(R.id.tx_login);

        fragmentBox = (LinearLayout)findViewById(R.id.fragmentBox);
        ll_login.setOnClickListener(this);
        ll_detail.setOnClickListener(this);
        ll_report.setOnClickListener(this);
        ll_remind.setOnClickListener(this);
        ll_budget.setOnClickListener(this);
        ll_more.setOnClickListener(this);
        loadAvatar();
    }
    private void loadAvatar(){
        if(!getSharedPreferences("User",MODE_PRIVATE).getString("nick","").equals("")){
            flag = 1;
        }
        Glide.with(this).load(getSharedPreferences("User",MODE_PRIVATE).getString("avatar","")).transform(new GlideCircleTransform(FrameActivity.this)).into(icon_login);
        tx_login.setText(getSharedPreferences("User",MODE_PRIVATE).getString("nick","请登录"));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_login:
                if (App.getUser()==0&&flag == 0) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    /*Intent intent = new Intent(this,PickPhotoActivity.class);
                    startActivity(intent);*/
                }
                break;
            case R.id.register:
                break;
            case R.id.forget_password:
                break;
            case R.id.ll_detail:
                changeColor(detailImage, detailText, R.drawable.detail02);
                DetailFragment deFragment = new DetailFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentBox, deFragment).commit();
                deFragment.setTitle(toolbar);
//                toolbar.setTitle("明细");
                drawerLayout.closeDrawers();
                break;
            case R.id.ll_report:
                changeColor(reportImage, reportText, R.drawable.report02);
                ReportFragment reFragment = new ReportFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentBox, reFragment).commit();
                reFragment.setTitle(toolbar);
//                toolbar.setTitle("报表");
                drawerLayout.closeDrawers();
                break;
            case R.id.ll_remind:
                changeColor(remindImage, remindText, R.drawable.remind02);
                AlarmFragment amFragment = new AlarmFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentBox, amFragment).commit();
                amFragment.setTitle(toolbar);
//                toolbar.setTitle("提醒");
                drawerLayout.closeDrawers();
                break;
            case R.id.ll_budget:
                changeColor(budgetImage, budgetText, R.drawable.budget02);
                BudgetFragment budgetFragment = new BudgetFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentBox,budgetFragment).commit();
                budgetFragment.setTitle(toolbar);
//                toolbar.setTitle("预算");
                drawerLayout.closeDrawers();
                break;
            case R.id.ll_more:
                changeColor(moreImage, moreText, R.drawable.more02);
                MoreFragment moreFragment = new MoreFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentBox,moreFragment).commit();
                moreFragment.setTitle(toolbar);
//                toolbar.setTitle("更多");
                drawerLayout.closeDrawers();
                break;
        }
    }
    //点击切换图片和文字颜色
    public void changeColor(ImageView imageView,TextView textView,int resId){
        detailImage.setBackgroundResource(R.drawable.detail01);
        detailText.setTextColor(getResources().getColor(R.color.dark_white));
        reportImage.setBackgroundResource(R.drawable.report01);
        reportText.setTextColor(getResources().getColor(R.color.dark_white));
        remindImage.setBackgroundResource(R.drawable.remind01);
        remindText.setTextColor(getResources().getColor(R.color.dark_white));
        budgetImage.setBackgroundResource(R.drawable.budget01);
        budgetText.setTextColor(getResources().getColor(R.color.dark_white));
        moreImage.setBackgroundResource(R.drawable.more01);
        moreText.setTextColor(getResources().getColor(R.color.dark_white));
        imageView.setBackgroundResource(resId);
        textView.setTextColor(getResources().getColor(R.color.white));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAvatar();
        /*if(Utils.notification) {
            it = getIntent();
            Utils.toast("frame");
            if (it.getAction().equals("pause")) {
                Utils.stopVibrator(this);
                Utils.notification = false;
            }
        }*/
    }

    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    // 连续按2次退出
    private static Boolean isExit = false;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if (!isExit) {
                isExit = true;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 2000);
                return false;
            } else {
                finish();
                System.exit(0);
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}
