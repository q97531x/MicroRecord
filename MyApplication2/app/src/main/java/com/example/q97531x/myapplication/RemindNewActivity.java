package com.example.q97531x.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.tsz.afinal.FinalDb;

import java.util.List;

import Widget.ContactsDialog;
import adapter.RemindAdapter;
import model.Contact;
import model.Remind;
import util.PercentDialog;

/**
 * Created by q97531x on 2016/5/14.
 */
public class RemindNewActivity extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout rl_limit,rl_object;
    private TextView limit;
    private ListView list;
    private Toolbar toolbar;
    List<Remind> remindList;
    List<Contact> contactList;
    FinalDb db;
    private String[] percents = {"50%","60%","70%","80%","90%","100%"};
    private boolean isSet = false;
    static RemindAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_remind);
        db = FinalDb.create(RemindNewActivity.this);
        //获取数据库信息，改变按钮状态
        //remind数据库
        remindList = db.findAll(Remind.class);
        contactList = db.findAll(Contact.class);
        if(remindList.size()>0){
            isSet = true;
        }else{
            isSet = false;
        }
        initToolbar();
        initView();
    }

    private void initView() {
        rl_limit = (RelativeLayout)findViewById(R.id.rl_limit);
        rl_object = (RelativeLayout)findViewById(R.id.rl_object);
        limit = (TextView)findViewById(R.id.limit);
        list = (ListView)findViewById(R.id.remindList);
        rl_limit.setOnClickListener(this);
        adapter = new RemindAdapter(this,db,RemindNewActivity.this);
        list.setAdapter(adapter);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RemindNewActivity.this);
                builder.setMessage("是否确定删除该联系人");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteById(Contact.class, position + 1);
                        refleshAdapter();
                        dialog.dismiss();
                    }
                });
               builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                   }
               });
                builder.show();
                return false;
            }
        });
        if(isSet){
            limit.setText(remindList.get(0).getPercent() + "%");
        }
    }

    private void initToolbar() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("提醒");
        toolbar.setTitleTextColor(Color.parseColor("#FAF4ED"));
        toolbar.setNavigationIcon(R.drawable.back01);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public  void refleshAdapter(){
        Log.e("ss","ss");
        adapter = new RemindAdapter(RemindNewActivity.this,db,RemindNewActivity.this);
        list.setAdapter(adapter);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_limit:
                AlertDialog.Builder builder = new AlertDialog.Builder(RemindNewActivity.this);
                builder.setTitle("选择百分比");
                builder.setSingleChoiceItems(percents, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        limit.setText(percents[which]);
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;


        }
    }

    @Override
    protected void onResume() {
//        adapter.notifyDataSetChanged();
        super.onResume();
    }
}
