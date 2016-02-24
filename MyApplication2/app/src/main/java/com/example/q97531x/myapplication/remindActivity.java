package com.example.q97531x.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.tsz.afinal.FinalDb;

import java.util.List;

import model.Remind;
import model.RemindStyle;
import util.BalanceDialog;
import util.PercentDialog;
import util.SmsDialog;

public class remindActivity extends AppCompatActivity {
    TextView percentText,balanceText;
    ImageView percentView,balanceView,vibration,ring,Sms,back_more,dateRemind;
    Spinner spinner;
    String[] RemindType = {
            "百分比","余额"
    };
    boolean flag1 = true,flag2 = true,select = true;
    Remind remind;
    String SmsText,SmsPhone;
    int per=0,bal=0,tag = 0;
    int count,count1,count2,a,b,c = 0;
    FinalDb db;
    LinearLayout ll;
    List<Remind> remindList;
    List<RemindStyle> remindStyleList;
    EditText SmsName,SmsNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);
        db = FinalDb.create(remindActivity.this);
        init();
        back_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(remindActivity.this, FrameActivity.class);
                startActivity(intent);
            }
        });
        percentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //flag1判断百分比开关状态
                if (flag1 == true) {
                    PercentDialog percentDialog = new PercentDialog(remindActivity.this, new PercentDialog.OnCustomDialogListener() {
                        @Override
                        public void back(int percent) {
                            //判定percent值是否大于50小于100，大于50小于100显示，小于50提示重新设置。
                            if (percent > 50&&percent<100) {
                                percentText.setText(percent + "%");
                                remindList = db.findAll(Remind.class);
                                remind = new Remind();
                                //remind.setId(0);
                                remind.setTag(0);
                                remind.setPercent(percent);
                                //判断保存还是更新
                                if(remindList.size() == 0){//数据库未保存任何字段，save；
                                    db.save(remind);
                                    Toast.makeText(remindActivity.this,"save"+remindList.size(),Toast.LENGTH_SHORT).show();
                                }
                                else if(remindList.size()>0){
                                    for (int i =0;i<remindList.size();i++){//遍历数据库
                                        if(remindList.get(i).getTag() == 0){//如果有tag等于1的字段，则update，否则save
                                            db.update(remind,"id like 'i'");
                                            Toast.makeText(remindActivity.this,"update"+i,Toast.LENGTH_SHORT).show();
                                        }else{
                                            db.save(remind);
                                            Toast.makeText(remindActivity.this,"save1",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                percentView.setImageResource(R.drawable.btn1);
                            }else if(percent<=50&&percent>0){
                                Toast.makeText(remindActivity.this,"请设置大于50的数值",Toast.LENGTH_LONG).show();
                                percentView.setImageResource(R.drawable.btn2);
                            }else if(percent>=100){
                                Toast.makeText(remindActivity.this,"请设置小于100的数值",Toast.LENGTH_LONG).show();
                                percentView.setImageResource(R.drawable.btn2);
                            }else if(percent == -1||percent == -2){//点击取消按钮的标志
                                percentView.setImageResource(R.drawable.btn2);
                            }
                        }
                    });
                    percentDialog.show();
                    flag1 = false;
                }else if(flag1 == false){
                    percentView.setImageResource(R.drawable.btn2);
                    percentText.setText("");
                    flag1 = true;
                }
            }
        });
        balanceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag2 == true){//用于标志开关状态
                    BalanceDialog balanceDialog = new BalanceDialog(remindActivity.this, new BalanceDialog.OnCustomDialogListener() {
                        @Override
                        public void back(int balance) {
                            if(balance>=0&&balance<100000){//余额为有效数值
                                balanceText.setText(balance+"¥");
                                remindList = db.findAll(Remind.class);
                                //保存数据库
                                remind = new Remind();
                                //remind.setId(0);
                                remind.setTag(1);
                                remind.setBalance(balance);
                                //判断是保存还是更新
                                if(remindList.size() == 0){//数据库未保存任何字段，save；
                                    db.save(remind);
                                    Toast.makeText(remindActivity.this,"savere"+remindList.size(),Toast.LENGTH_SHORT).show();
                                }
                                else if(remindList.size()>0){
                                    for (int i =0;i<remindList.size();i++){//遍历数据库
                                        if(remindList.get(i).getTag() == 1){//如果有tag等于1的字段，则update，否则save
                                            db.update(remind,"id like 'i'");
                                            Toast.makeText(remindActivity.this,"update"+i,Toast.LENGTH_SHORT).show();
                                        }else{
                                            db.save(remind);
                                            Toast.makeText(remindActivity.this,"save1",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                balanceView.setImageResource(R.drawable.btn1);
                            }else if(balance==-1){
                                balanceView.setImageResource(R.drawable.btn2);
                            }else if(balance>=100000){
                                balanceView.setImageResource(R.drawable.btn2);
                                Toast.makeText(remindActivity.this,"余额设置过多",Toast.LENGTH_SHORT).show();
                            }
                            //Toast.makeText(remindActivity.this,""+balance,Toast.LENGTH_SHORT).show();
                        }
                    });
                    balanceDialog.show();
                    flag2 = false;
                }else if(flag2 == false) {
                    balanceView.setImageResource(R.drawable.btn2);
                    balanceText.setText("");
                    flag2 = true;
                }
            }
        });

       //振动
        vibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count == 0){//未选中振动
                    vibration.setImageResource(R.drawable.choosed);
                    RemindStyle remindStyle = new RemindStyle();
                    List<RemindStyle> remindStyleList = db.findAll(RemindStyle.class);
                    if(remindStyleList.size()>0){
                        for(int i = 0;i<remindStyleList.size();i++){
                            if(remindStyleList.get(i).getStyle() == 0){//bool型无法正确标识状态，修改成int型
                                //remindStyle.setStyle(0);
                                remindStyle.setVibration(true);
                                db.update(remindStyle, "id like 'i'");
                                Toast.makeText(remindActivity.this,"update"+0+remindStyleList.get(i).getVibration(),Toast.LENGTH_SHORT).show();
                            }else{
                                remindStyle.setStyle(0);
                                remindStyle.setVibration(true);
                                db.save(remindStyle);
                                //a++;
                                Toast.makeText(remindActivity.this,"save"+0,Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else{
                        remindStyle.setStyle(0);
                        remindStyle.setVibration(true);
                        db.save(remindStyle);
                        Toast.makeText(remindActivity.this,"save"+1,Toast.LENGTH_SHORT).show();
                    }
                    count = 1;
                }else if(count == 1){
                    vibration.setImageResource(R.drawable.choose);
                    RemindStyle remindStyle = new RemindStyle();
                    List<RemindStyle> remindStyleList = db.findAll(RemindStyle.class);
                    if(remindStyleList.size()>0){
                        for(int i = 0;i<remindStyleList.size();i++){
                            if(remindStyleList.get(i).getStyle() == 0){
                                //remindStyle.setStyle(0);
                                remindStyle.setVibration(false);
                                db.update(remindStyle, "id like 'i'");
                                Toast.makeText(remindActivity.this,"update"+1+remindStyleList.get(i).getVibration(),Toast.LENGTH_SHORT).show();
                            }else
                            {   remindStyle.setStyle(0);
                                remindStyle.setVibration(false);
                                db.save(remindStyle);
                                Toast.makeText(remindActivity.this,"save"+1,Toast.LENGTH_SHORT).show();
                            }
                        }
                        //Toast.makeText(remindActivity.this,"update"+1,Toast.LENGTH_SHORT).show();
                    }else{
                        remindStyle.setStyle(0);
                        remindStyle.setVibration(false);
                        db.save(remindStyle);
                    }
                    count = 0;
                }
            }
        });
        //响铃
        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count1 == 0){
                    ring.setImageResource(R.drawable.choosed);
                    RemindStyle remindStyle = new RemindStyle();
                    //remindStyle.setRing(true);
                    List<RemindStyle> remindStyleList = db.findAll(RemindStyle.class);
                    if(remindStyleList.size()>0){
                        for(int i = 0;i<remindStyleList.size();i++){
                            if(remindStyleList.get(i).getStyle() == 1){
                                remindStyle.setRing(true);
                                db.update(remindStyle,"id like 'i'");
                            }else{
                                remindStyle.setStyle(1);
                                remindStyle.setRing(true);
                                //b++;
                                db.save(remindStyle);
                            }
                        }
                        Toast.makeText(remindActivity.this,"update"+0,Toast.LENGTH_SHORT).show();
                    }else{
                        remindStyle.setStyle(1);
                        remindStyle.setRing(true);
                        db.save(remindStyle);
                    }
                    count1 = 1;
                }else if(count1 == 1){
                    ring.setImageResource(R.drawable.choose);
                    RemindStyle remindStyle = new RemindStyle();
                    remindStyle.setRing(false);
                    List<RemindStyle> remindStyleList = db.findAll(RemindStyle.class);
                    if(remindStyleList.size()>0){
                        for(int i = 0;i<remindStyleList.size();i++){
                            if(remindStyleList.get(i).getStyle() == 1){
                                remindStyle.setRing(true);
                                db.update(remindStyle,"id like 'i'");
                            }else{
                                remindStyle.setStyle(1);
                                remindStyle.setRing(true);
                                db.save(remindStyle);
                            }
                        }
                        Toast.makeText(remindActivity.this,"update"+0,Toast.LENGTH_SHORT).show();
                    }else{
                        remindStyle.setStyle(1);
                        remindStyle.setRing(true);
                        db.save(remindStyle);
                    }
                    count1 = 0;
                }
            }
        });
        //短信提醒
        Sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消短信提醒
                if(count2 == 1) {
                    RemindStyle remindStyle = new RemindStyle();
                    for(int i = 0;i<remindStyleList.size();i++) {
                        if (remindStyleList.get(i).getStyle() == 2) {
                            remindStyle.setSms(false);
                            remindStyle.setSmsName("null");
                            remindStyle.setSmsPhone("null");
                            db.update(remindStyle, "id like 'i'");
                        }
                    }
                    Sms.setImageResource(R.drawable.choose);
                    count2 = 0;
                }else if(count2 == 0){//设置短信提醒
                    count2 = 1;
                   SmsDialog smsDialog = new SmsDialog(remindActivity.this, new SmsDialog.OnCustomDialogListener() {
                       @Override
                       public void backName(String SmsName, String SmsNum) {
                           //如果姓名和号码有未填写项
                           if(SmsName.equals("null")||SmsNum.equals("null")){
                               Toast.makeText(remindActivity.this,"请填写联系人资料",Toast.LENGTH_SHORT).show();
                               Sms.setImageResource(R.drawable.choose);
                           }else{
                               RemindStyle remindStyle = new RemindStyle();
                               remindStyleList = db.findAll(RemindStyle.class);
                               //判断是保存还是更新
                               if(remindStyleList.size() == 0){//数据库未保存任何字段，save；
                                   remindStyle.setStyle(2);
                                   remindStyle.setSms(true);
                                   remindStyle.setSmsName(SmsName);
                                   remindStyle.setSmsPhone(SmsNum);
                                   db.save(remindStyle);
                                   Toast.makeText(remindActivity.this,"save"+remindStyleList.size(),Toast.LENGTH_SHORT).show();
                               }
                               else if(remindStyleList.size()>0){
                                   for(int i = 0;i<remindStyleList.size();i++){
                                       if(remindStyleList.get(i).getStyle() == 2){
                                           //remindStyle.setStyle(2);
                                           remindStyle.setSms(true);
                                           remindStyle.setSmsName(SmsName);
                                           remindStyle.setSmsPhone(SmsNum);
                                           db.update(remindStyle, "id like 'i'");
                                           //Toast.makeText(remindActivity.this,"update"+remindStyleList.size()+remindStyleList.get(0).getVibration(),Toast.LENGTH_SHORT).show();
                                       }else{
                                           remindStyle.setStyle(2);
                                           remindStyle.setSms(true);
                                           remindStyle.setSmsName(SmsName);
                                           remindStyle.setSmsPhone(SmsNum);
                                           db.save(remindStyle);
                                           Toast.makeText(remindActivity.this,"save"+remindStyleList.get(0).getStyle()+remindStyleList.get(1).getStyle()+remindStyleList.get(2).getStyle()+remindStyleList.get(3).getStyle(),Toast.LENGTH_SHORT).show();
                                       }
                                   }
                                   }
                               Sms.setImageResource(R.drawable.choosed);
                           }
                       }
                   });
                    smsDialog.show();
                }
            }
        });
        dateRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void init(){
        //初始化界面
        percentText = (TextView)findViewById(R.id.percentText);
        balanceText = (TextView)findViewById(R.id.balanceText);
        percentView = (ImageView)findViewById(R.id.percentView);
        balanceView = (ImageView)findViewById(R.id.balanceView);
        vibration = (ImageView)findViewById(R.id.vibration);
        ring = (ImageView)findViewById(R.id.ring);
        Sms = (ImageView)findViewById(R.id.sms);
        dateRemind = (ImageView)findViewById(R.id.dateRemind);
        back_more = (ImageView)findViewById(R.id.back_more);
        //获取数据库信息，改变按钮状态
        //remind数据库
        remindList = db.findAll(Remind.class);
        if(remindList.size()>0) {
            for (int i = 0; i < remindList.size(); i++) {
                if (remindList.get(i).getTag() == 0) {
                    percentView.setImageResource(R.drawable.btn1);
                    percentText.setText("" + remindList.get(i).getPercent());
                    flag1 = false;
                } else if (remindList.get(i).getTag() == 1) {
                    balanceView.setImageResource(R.drawable.btn1);
                    balanceText.setText("" + remindList.get(i).getBalance());
                    flag2 = false;
                }
            }
        }
        //remindStyle
        remindStyleList = db.findAll(RemindStyle.class);
        Toast.makeText(remindActivity.this,""+remindStyleList.size(),Toast.LENGTH_SHORT).show();
        if(remindStyleList.size()>0) {
            for(int i = 0;i<remindStyleList.size();i++) {
                if(remindStyleList.get(i).getStyle()==0){
                    if (remindStyleList.get(i).getVibration() == true) {
                        vibration.setImageResource(R.drawable.choosed);
                        count = 1;
                    } else {
                        vibration.setImageResource(R.drawable.choose);
                        count = 0;
                    }
                }else if(remindStyleList.get(i).getStyle() == 1){
                    if (remindStyleList.get(i).getRing() == true) {
                        ring.setImageResource(R.drawable.choosed);
                        count1 = 1;
                    } else {
                        ring.setImageResource(R.drawable.choose);
                        count1 = 0;
                    }
                }else if(remindStyleList.get(i).getStyle() == 2){
                    if (remindStyleList.get(i).getSms() == true) {
                        Sms.setImageResource(R.drawable.choosed);
                        count2 = 1;
                    } else {
                        Sms.setImageResource(R.drawable.choose);
                        count2 = 0;
                    }
                }
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_remind, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
