package com.example.q97531x.myapplication;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import com.example.q97531x.myapplication.Bean.FileInfo;

import java.util.ArrayList;

import adapter.GridViewBaseAdapter;

/**
 * Created by q97531x on 2016/5/2.
 */
public class PickPhotoActivity extends BaseActivity {
    private GridView gridView;
    private GridViewBaseAdapter adapter;
    Uri mUri = Uri.parse("content://media/external/images/media");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_local_pic);
        initToolbar();
        initView();
        final MyHandler handler = new MyHandler();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                queryAllImage();
                adapter = new GridViewBaseAdapter(PickPhotoActivity.this, queryAllImage());
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("图片选择");
        toolbar.setNavigationIcon(R.drawable.back01);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initView() {
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //显示大图

            }
        });
    }

    private ArrayList<FileInfo> queryAllImage() {
        ArrayList<FileInfo> images = new ArrayList<>();
        ContentResolver resolver = this.getContentResolver();
        Cursor cursor = null;
        try {
            cursor = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.MediaColumns.DATE_ADDED + " DESC");
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    FileInfo image = new FileInfo();
                    image.setId(cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID)));
                    image.setFilePath(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
                    image.setFileName(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)));
                    image.setUri(Uri.withAppendedPath(mUri, "" + cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID))));
                    images.add(image);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return images;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pick_photo,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_pick){
            //传递参数
            Intent intent = new Intent();
            intent.putParcelableArrayListExtra("data",adapter.getCheckUri());
            setResult(Activity.RESULT_OK,intent);
        }
        finish();
        return super.onOptionsItemSelected(item);
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            gridView.setAdapter(adapter);
        }
    }
}
