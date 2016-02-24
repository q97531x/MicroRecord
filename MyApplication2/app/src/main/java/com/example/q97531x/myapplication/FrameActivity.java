package com.example.q97531x.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.tsz.afinal.FinalDb;

import Base.App;
import cn.bmob.push.BmobPush;
import cn.bmob.v3.BmobInstallation;
import fragment.BudgetFragment;
import fragment.DetailFragment;
import fragment.MoreFragment;
import fragment.ReportFragment;

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
    ImageView detailImage,reportImage,remindImage,budgetImage,moreImage;
    TextView detailText,reportText,remindText,budgetText,moreText;
    int msg = 0;
    int flag =0;
    Intent it2;
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
//        initClick();
    }
    public void initToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("明细");
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
        //文字初始化
        detailText = (TextView)findViewById(R.id.detail);
        reportText = (TextView)findViewById(R.id.report);
        remindText = (TextView)findViewById(R.id.remind);
        budgetText = (TextView)findViewById(R.id.budget);
        moreText = (TextView)findViewById(R.id.more);

        fragmentBox = (LinearLayout)findViewById(R.id.fragmentBox);
        ll_login.setOnClickListener(this);
        ll_detail.setOnClickListener(this);
        ll_report.setOnClickListener(this);
        ll_remind.setOnClickListener(this);
        ll_budget.setOnClickListener(this);
        ll_more.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_login:
                if (App.getUser()==0) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.register:
                break;
            case R.id.forget_password:
                break;
            case R.id.ll_detail:
                changeColor(detailImage,detailText,R.drawable.detail02);
                DetailFragment deFragment = new DetailFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentBox, deFragment).commit();
                drawerLayout.closeDrawers();
                break;
            case R.id.ll_report:
                changeColor(reportImage,reportText,R.drawable.report02);
                ReportFragment reFragment = new ReportFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentBox, reFragment).commit();
                drawerLayout.closeDrawers();
                break;
            case R.id.ll_remind:
                changeColor(remindImage,remindText,R.drawable.remind02);
//                RemindFragment rmFragment = new RemindFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentBox, rmFragment).commit();
                drawerLayout.closeDrawers();
                break;
            case R.id.ll_budget:
                changeColor(budgetImage,budgetText,R.drawable.budget02);
                BudgetFragment budgetFragment = new BudgetFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentBox,budgetFragment).commit();
                drawerLayout.closeDrawers();
                break;
            case R.id.ll_more:
                changeColor(moreImage,moreText,R.drawable.more02);
                MoreFragment moreFragment = new MoreFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentBox,moreFragment).commit();
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
    /*private void initClick(){
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detail.setBackgroundResource(R.drawable.icon_graybg);
                report.setBackgroundResource(R.drawable.icon_graybg2);
                budget.setBackgroundResource(R.drawable.icon_graybg2);
                more.setBackgroundResource(R.drawable.icon_graybg2);
                DetailFragment deFragment = new DetailFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentBox, deFragment).commit();
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detail.setBackgroundResource(R.drawable.icon_graybg2);
                report.setBackgroundResource(R.drawable.icon_graybg);
                budget.setBackgroundResource(R.drawable.icon_graybg2);
                more.setBackgroundResource(R.drawable.icon_graybg2);
                Log.v("click", "click1");
                ReportFragment reFragment = new ReportFragment();
                Bundle report = new Bundle();
                report.putString("redate", redate);
                reFragment.setArguments(report);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentBox, reFragment).commit();
            }
        });
        budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detail.setBackgroundResource(R.drawable.icon_graybg2);
                report.setBackgroundResource(R.drawable.icon_graybg2);
                budget.setBackgroundResource(R.drawable.icon_graybg);
                more.setBackgroundResource(R.drawable.icon_graybg2);
                BudgetFragment buFragment = new BudgetFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentBox, buFragment).commit();
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detail.setBackgroundResource(R.drawable.icon_graybg2);
                report.setBackgroundResource(R.drawable.icon_graybg2);
                budget.setBackgroundResource(R.drawable.icon_graybg2);
                more.setBackgroundResource(R.drawable.icon_graybg);
                MoreFragment moFragment = new MoreFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentBox, moFragment).commit();
            }
        });

    }*/
    @Override
    protected void onResume() {
        super.onResume();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
