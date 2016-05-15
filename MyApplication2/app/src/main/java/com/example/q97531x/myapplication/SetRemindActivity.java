/*
package com.example.q97531x.myapplication;


import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.tsz.afinal.FinalDb;

import java.util.List;

import Widget.ContactsDialog;
import model.Remind;

*/
/**
 * Created by XmacZone on 16/3/21.
 *//*

public class SetRemindActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout rl_threshold,rl_style;
    private TextView threshold,style;//阈值,样式
    private String[] percents = {"50%","60%","70%","80%","90%","100%"};
    private String[] styles = {"振动","响铃","短信提醒"};
    private String phoneNum,phoneName;
    private List<Remind> reminds;
    private boolean isSet = false;
    private FinalDb db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_remind);
        db = FinalDb.create(this);
        reminds = db.findAll(Remind.class);
        if(reminds.size()>0){
            isSet = true;
        }else{
            isSet = false;
        }
        initToolbar();
        initView();
    }
    public void initToolbar(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.iconfont_fanhui);
        toolbar.setTitle("提醒设置");
        setSupportActionBar(toolbar);


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int percent = Integer.parseInt(threshold.getText().toString().substring(0,threshold.getText().toString().length()-1));

                if(item.getItemId() == R.id.action_save){
                    if(!style.getText().toString().equals("短信提醒")){
                        Remind remind = new Remind();
                        remind.setId(1);
                        remind.setPercent(percent);
                        if(style.getText().toString().equals("振动")) {
                            remind.setStyle(0);
                        }else{
                            remind.setStyle(1);
                        }
                        remind.setPhoneNumber(phoneNum);
                        remind.setName(phoneName);
                        Log.e("verble","percent:"+percent+style.getText().toString());
                        if(!isSet) {
                            Log.e("save","save");
                            db.save(remind);
                        }else {
                            Log.e("update","update");
                            db.update(remind," id=\"" + 1 + "\"");
                        }
                        finish();
                    }else {
                        Log.e("isSet",""+isSet);
                        Remind remind = new Remind();
                        remind.setId(1);
                        remind.setPercent(percent);
                        remind.setStyle(2);
                        remind.setPhoneNumber(phoneNum);
                        remind.setName(phoneName);
                        if(!isSet) {
                            db.save(remind);
                        }else {
//                            db.update(remind);
                            db.update(remind," id=\"" + 1 + "\"");
                        }
                        finish();
                    }
                }
                return false;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initView() {
        rl_threshold = (RelativeLayout)findViewById(R.id.rl_threshold);
        rl_style = (RelativeLayout)findViewById(R.id.rl_style);
        threshold = (TextView)findViewById(R.id.threshold);
        style = (TextView)findViewById(R.id.style);
//        Log.e("isSet",""+isSet+reminds.size()+reminds.get(reminds.size()-1).getPercent());
        if(isSet){
            threshold.setText(reminds.get(0).getPercent() + "%");
            if(reminds.get(0).getStyle() == 0) {
                style.setText("振动");
            }else if(reminds.get(0).getStyle()==1){
                style.setText("响铃");
            }else {
                style.setText("短信提醒");
            }
            phoneNum = reminds.get(0).getPhoneNumber();
        }
        rl_threshold.setOnClickListener(this);
        rl_style.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SetRemindActivity.this);
        switch (v.getId()){
            case R.id.rl_threshold:
                builder.setTitle("选择百分比");
                builder.setSingleChoiceItems(percents, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        threshold.setText(percents[which]);
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
            case R.id.rl_style:
                builder.setTitle("选择提醒方式");
                builder.setSingleChoiceItems(styles, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {
                        if(styles[which].equals("短信提醒")) {
                            dialog.dismiss();
                            final ContactsDialog contactsDialog = new ContactsDialog(SetRemindActivity.this);
                            contactsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            contactsDialog.show();
                            contactsDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    phoneNum = contactsDialog.getNumber();
                                    phoneName = contactsDialog.getPhone_name();
                                    style.setText(styles[which]);
                                }
                            });
                        }else{
                            dialog.dismiss();
                            style.setText(styles[which]);
                        }
                    }
                });
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                */
/*builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (style.getText().equals("短信提醒")) {
                            final ContactsDialog contactsDialog = new ContactsDialog(SetRemindActivity.this);
                            contactsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            contactsDialog.show();
                            contactsDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    phoneNum = contactsDialog.getNumber();
                                }
                            });
                        }
                    }
                });*//*

                builder.show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_remind, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
*/
