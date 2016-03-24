package com.example.q97531x.myapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by XmacZone on 16/3/24.
 */
public class AvatarActivity extends BaseActivity{
    private TextView camera,nick,sex,complete;
    private RelativeLayout rl_sex;
    private Typeface iconfont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_avatar);
        iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        initToolbar();
        initView();
    }
    private void initToolbar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.icons_back_s);
        toolbar.setTitle("完善资料");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {

    }
}
