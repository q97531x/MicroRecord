package com.example.q97531x.customview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Base.App;
import model.Type;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager viewPager;
    DbUtils db;
    /*DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName("test")
            .setDbDir(new File("/sdcard"))
            .setDbVersion(2)
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                    // TODO: ...
                    // db.addColumn(...);
                    // db.dropTable(...);
                    // ...
                }
            });*/
    String temp = "";
    ImageView[] imageViews = new ImageView[4];
    List<Type> typeList;
    String type[] = {"0","1","2","3","4","5","6","7","8"};
    boolean isFirst = true;
    ArrayList<View> viewContainter = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initDb();
        initView();
    }
    public void DbSet(){

        try {
            db = DbUtils.create(MainActivity.this);
//                db.delete(Type.class);
            Log.e("isFirst", "" + isFirst);
            SharedPreferences sharedPreferences = getSharedPreferences("boolean",this.MODE_PRIVATE);
            isFirst = sharedPreferences.getBoolean("isFirst",true);
            Log.e("ss",""+isFirst);
            if(isFirst) {
                for (int i = 0; i < type.length; i++) {
                    Type type = new Type();
                    type.setresId(R.drawable.ic_launcher);
                    db.save(type);
                }
                Type type = new Type();
                type.setresId(R.drawable.postimg_add);
                db.save(type);
                isFirst = false;
                SharedPreferences preferences = getSharedPreferences("boolean",this.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isFirst",isFirst);
                editor.commit();
            }

            typeList = db.findAll(Type.class);
//                Log.e("ll",""+typeList.size());
            LayoutInflater inflater = getLayoutInflater().from(this);

            for(int i=0;i<(typeList.size()+1)/4+1;i++){
                View view = inflater.inflate(R.layout.typelayout, null);
                imageViews[0] = (ImageView)view.findViewById(R.id.view1);
                imageViews[1] = (ImageView)view.findViewById(R.id.view2);
                imageViews[2] = (ImageView)view.findViewById(R.id.view3);
                imageViews[3] = (ImageView)view.findViewById(R.id.view4);
                imageViews[0].setOnClickListener(this);
                imageViews[1].setOnClickListener(this);
                imageViews[2].setOnClickListener(this);
                imageViews[3].setOnClickListener(this);
                if((i * 4 )<typeList.size()) {
                    imageViews[0].setImageResource(typeList.get(i * 4 ).getresId());
                }
                if((i * 4 + 1)<typeList.size()) {
                    imageViews[1].setImageResource(typeList.get(i * 4 + 1).getresId());
                }
                if((i * 4 + 2)<typeList.size()) {
                    imageViews[2].setImageResource(typeList.get(i * 4 + 2).getresId());
                }
                if((i * 4 + 3)<typeList.size()) {
                    final int typeNum = i*4+3;
                    imageViews[3].setImageResource(typeList.get(i * 4 + 3).getresId());
                    /*imageViews[3].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (typeList.get(typeNum).getresId() == R.drawable.postimg_add) {
                                Intent intent = new Intent(MainActivity.this, AddTypeActivity.class);
                                startActivity(intent);
                            }
                        }
                    });*/
                }
                viewContainter.add(view);
            }
            Log.e("viewcom",viewContainter.size()+"");
            viewPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return viewContainter.size();
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView(viewContainter.get(position));
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    container.addView(viewContainter.get(position));
                    return viewContainter.get(position);
                }
            });
        }catch (DbException e){

        }
    }
    public void initView(){
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        DbSet();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view1:
                for (int i = 0;i<typeList.size()/4+1;i++){
                    if(typeList.get(i*4).getresId() == R.drawable.postimg_add){
                        Intent intent = new Intent(MainActivity.this, AddTypeActivity.class);
                        startActivity(intent);
                    }
                }
                break;
            case R.id.view2:

                for (int i = 0;i<typeList.size()/4+1;i++){
//                    Log.e("view",""+(typeList.get(i*4+1).getresId() == R.drawable.postimg_add));
                    if(typeList.get(i*4+1).getresId() == R.drawable.postimg_add){
                        Intent intent = new Intent(MainActivity.this, AddTypeActivity.class);
                        startActivity(intent);
                    }
                }
                break;
            case R.id.view3:
                for (int i = 0;i<typeList.size()/4+1;i++){
                    Log.e("view",""+(typeList.get(i*4+2).getresId() == R.drawable.postimg_add));
                    if(typeList.get(i*4+2).getresId() == R.drawable.postimg_add){

                        Intent intent = new Intent(MainActivity.this, AddTypeActivity.class);
                        startActivity(intent);
                    }
                }
                break;
            case R.id.view4:
                for (int i = 0;i<typeList.size()/4+1;i++){
                    Log.e("view",""+(typeList.get(i*4+3).getresId() == R.drawable.postimg_add));
                    if(typeList.get(i*4+3).getresId() == R.drawable.postimg_add){
                        Intent intent = new Intent(MainActivity.this, AddTypeActivity.class);
                        startActivity(intent);
                    }
                }
                break;
        }
    }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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
