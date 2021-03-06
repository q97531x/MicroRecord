package com.example.q97531x.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.q97531x.myapplication.Bean.FileInfo;

import java.io.File;
import java.util.ArrayList;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import model.Bmob.Person;
import util.GlideCircleTransform;
import util.Utils;

/**
 * Created by XmacZone on 16/3/24.
 */
public class AvatarActivity extends BaseActivity implements View.OnClickListener{
    private TextView nick,sex,complete;
    private ImageView camera;
    private RelativeLayout rl_sex;
    private Typeface iconfont;
    private final int RESULT_REQUEST_PHOTO = 1005;
    private ArrayList<FileInfo> uri;
    private String objectId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_avatar);
        Intent intent = getIntent();
        objectId = intent.getStringExtra("objectId");
        iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        initToolbar();
        initView();
    }
    private void initToolbar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back01);
        toolbar.setTitle("完善资料");
        toolbar.setTitleTextColor(Color.parseColor("#FAF4ED"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        camera = (ImageView)findViewById(R.id.camera);
        nick = (TextView)findViewById(R.id.nick);
        sex = (TextView)findViewById(R.id.sex);
        complete = (TextView)findViewById(R.id.complete);
        rl_sex = (RelativeLayout)findViewById(R.id.rl_sex);
        camera.setOnClickListener(this);
        complete.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
    }

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RESULT_REQUEST_PHOTO){
            if(resultCode == RESULT_OK){
                uri = data.getParcelableArrayListExtra("data");
                Glide.with(this).load(uri.get(0).getUri()).transform(new GlideCircleTransform(AvatarActivity.this)).into(camera);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.camera:
                //跳转选择图片
                /*Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_REQUEST_PHOTO);*/
                Intent intent = new Intent(this,PickPhotoActivity.class);
                startActivityForResult(intent,RESULT_REQUEST_PHOTO);
                break;
            case R.id.complete:
                if(!nick.getText().toString().equals("")&&!sex.getText().toString().equals("")) {
                    String picPath = uri.get(0).getFilePath();
                    final BmobFile bmobFile = new BmobFile(new File(picPath));
                    bmobFile.uploadblock(AvatarActivity.this, new UploadFileListener() {
                        @Override
                        public void onSuccess() {
                            //bmobFile.getFileUrl(context) url
                            Person person = new Person();
                            person.setAvatar(bmobFile.getFileUrl(AvatarActivity.this));
                            person.update(AvatarActivity.this, objectId, new UpdateListener() {
                                @Override
                                public void onSuccess() {
                                    finish();
                                }

                                @Override
                                public void onFailure(int i, String s) {

                                }
                            });

                        }

                        @Override
                        public void onProgress(Integer value) {
                            super.onProgress(value);
//                        Utils.toast(value+"%");
                        }

                        @Override
                        public void onFailure(int i, String s) {

                        }
                    });
                }else{
                    Toast.makeText(AvatarActivity.this,"请填写完整信息",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rl_sex:
                String[] sex1 = {"男","女"};
                AlertDialog.Builder builder = new AlertDialog.Builder(AvatarActivity.this);
                builder.setItems(sex1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            sex.setText("男");
                        }else {
                            sex.setText("女");
                        }
                    }
                }).show();
        }
    }
}
