package com.example.q97531x.myapplication;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_local_pic);
        initView();
        final MyHandler handler = new MyHandler();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                queryAllImage();
                adapter = new GridViewBaseAdapter(PickPhotoActivity.this,queryAllImage());
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    public void initView() {
       gridView = (GridView)findViewById(R.id.gridView);
    }

    private ArrayList<FileInfo> queryAllImage() {
        ArrayList<FileInfo> images = new ArrayList<>();
        ContentResolver resolver = this.getContentResolver();
        Cursor cursor = null;
        try {
            cursor = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    FileInfo image = new FileInfo();
                    image.setId(cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID)));
                    image.setFilePath(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
                    image.setFileName(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)));
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

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            gridView.setAdapter(adapter);
        }
    }
}
