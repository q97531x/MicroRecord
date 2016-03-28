package com.example.q97531x.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by XmacZone on 16/3/24.
 */
public class AvatarActivity extends BaseActivity implements View.OnClickListener{
    private TextView nick,sex,complete;
    private ImageView camera;
    private RelativeLayout rl_sex;
    private Typeface iconfont;
    private final int RESULT_REQUEST_PHOTO = 1005;
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
//        camera = (ImageView)findViewById(R.id.camera);
        nick = (TextView)findViewById(R.id.nick);
        sex = (TextView)findViewById(R.id.sex);
        complete = (TextView)findViewById(R.id.complete);
        rl_sex = (RelativeLayout)findViewById(R.id.rl_sex);
        camera.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           /* case R.id.camera:
                //跳转选择图片
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_REQUEST_PHOTO);
                break;*/
            case R.id.complete:

                break;
        }
    }
}
