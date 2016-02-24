/*
package com.example.q97531x.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import adapter.TypePagerAdapter;
import model.Income;
import model.Outcome;
import model.Remind;
import util.TypeData;

*
 * Created by Administrator on 2015/7/29.


public class RecordActivity extends BaseActivity {


    LinearLayout dateBtn;
    TextView dateText,recordIncome,recordOutcome;
    ViewPager mViewPager;
    View view1,view2,view3,view4;
    EditText editAmount,editDetail;
    ImageView outitem1,outitem2,outitem3,outitem4,outitem5,outitem6,outitem7,outitem8,outitem9,outitem10;
    ImageView initem1,initem2,initem3,initem4,initem5,initem6,initem7,initem8,initem9,initem10;
    int year,month,day,hour,minutes,flag = 0,reid = -1,reflag ;
    String date,detail,type,redate,time;
    List<View> outViewList,inViewList;
    List<Outcome> OutcomeAccountList;
    List<Remind> RemindList;
    //控件
    private EditText editAmount;

    //参数
    double amount;
    FinalDb db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        db = FinalDb.create(this);
        initToolbar();
        initView();
        initListener();
Intent it = getIntent();
        Bundle bd = it.getBundleExtra("bd");
        reid = bd.getInt("id");
        reflag = bd.getInt("flag");
        if(reid!=-1){
            if(reflag == 0) {
                dateText.setText(db.findById(reid,Outcome.class).getOutcomeTime());
                editAmount.setText(String.valueOf(db.findById(reid, Outcome.class).getOutcomeAmount()));
                editDetail.setText(db.findById(reid, Outcome.class).getOutcomeNote());
            }else if(reflag == 1){
                dateText.setText(db.findById(reid,Income.class).getIncomeTime());
                editAmount.setText(String.valueOf(db.findById(reid, Income.class).getIncomeAmount()));
                editDetail.setText(db.findById(reid, Income.class).getIncomeNote());
            }
        }

        outViewList = new ArrayList<View>();
        outViewList.add(view1);
        outViewList.add(view2);
        viewPager(outViewList);
    }
    public void initToolbar(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.icons_back_s);
        TextView title = (TextView)findViewById(R.id.title);
        title.setText("我要记");
        TextView icon_confirm = (TextView)findViewById(R.id.icon_confirm);
        icon_confirm.setTypeface(iconfont);
        icon_confirm.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    //初始化控件
    private void initView() {
        dateBtn = (LinearLayout)findViewById(R.id.dateBtn);
        dateText = (TextView)findViewById(R.id.dateText);
//        item1 = (TextView)findViewById(R.id.item1);
//        item2 = (TextView)findViewById(R.id.item2);
        mViewPager = (ViewPager)findViewById(R.id.viewPager);
        TypePagerAdapter typePagerAdapter = new TypePagerAdapter(this.getSupportFragmentManager());
        mViewPager.setAdapter(typePagerAdapter);
        editAmount = (EditText)findViewById(R.id.editAmount);
        editDetail = (EditText)findViewById(R.id.editDetail);
        recordIncome = (TextView)findViewById(R.id.recordIncome);
        recordOutcome = (TextView)findViewById(R.id.recordOutcome);

recordBack = (ImageView)findViewById(R.id.recordBack);
        recordConfirm = (ImageView)findViewById(R.id.recordConfirm);

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR);
        minutes = c.get(Calendar.MINUTE);
        dateText.setText(year + "年" + (month+1) + "月" + day + "日");

        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.outcometypelayout1,null);
        view2 = inflater.inflate(R.layout.outcometypelayout2, null);
        view3 = inflater.inflate(R.layout.incometypelayout1,null);
        view4 = inflater.inflate(R.layout.incometypelayout2,null);
        outitem1 = (ImageView)view1.findViewById(R.id.foodview);
        outitem2 = (ImageView)view1.findViewById(R.id.buyview);
        outitem3 = (ImageView)view1.findViewById(R.id.trafficview);
        outitem4 = (ImageView)view1.findViewById(R.id.disportview);
        outitem5 = (ImageView)view1.findViewById(R.id.homeview);
        outitem6 = (ImageView)view2.findViewById(R.id.medicineview);
        outitem7 = (ImageView)view2.findViewById(R.id.learnview);
        outitem8 = (ImageView)view2.findViewById(R.id.foverview);
        outitem9 = (ImageView)view2.findViewById(R.id.investview);
        outitem10 = (ImageView)view2.findViewById(R.id.otherview);
        initem1 = (ImageView)view3.findViewById(R.id.wagesview);
        initem2 = (ImageView)view3.findViewById(R.id.businessview);
        initem3 = (ImageView)view3.findViewById(R.id.bonusview);
        initem4 = (ImageView)view3.findViewById(R.id.sharesview);
        initem5 = (ImageView)view3.findViewById(R.id.reimburseview);
        initem6 = (ImageView)view4.findViewById(R.id.payView);
        initem7 = (ImageView)view4.findViewById(R.id.allowanceview);
        initem8 = (ImageView)view4.findViewById(R.id.otherview);
    }
    //初始化监听器
    private void initListener(){
        recordOutcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                recordOutcome.setBackgroundResource(R.drawable.recordin_bg2);
                recordIncome.setBackgroundResource(R.drawable.recordout_bg1);
                outViewList = new ArrayList<View>();
                outViewList.add(view1);
                outViewList.add(view2);
                viewPager(outViewList);
                //Toast.makeText(RecordActivity.this,""+flag,Toast.LENGTH_SHORT).show();
            }
        });
        recordIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordOutcome.setBackgroundResource(R.drawable.recordin_bg1);
                recordIncome.setBackgroundResource(R.drawable.recordout_bg2);
                flag = 1;
                inViewList = new ArrayList<View>();
                inViewList.add(view3);
                inViewList.add(view4);
                viewPager(inViewList);
                //Toast.makeText(RecordActivity.this,""+flag,Toast.LENGTH_SHORT).show();
            }
        });
        type = "餐饮";
        outitem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outitem1.setImageResource(R.drawable.icons_food2);
                outitem2.setImageResource(R.drawable.icons_shop);
                outitem3.setImageResource(R.drawable.icons_traffic);
                outitem4.setImageResource(R.drawable.icons_entertainment);
                outitem5.setImageResource(R.drawable.icons_home);
                outitem6.setImageResource(R.drawable.icons_health);
                outitem7.setImageResource(R.drawable.icons_study);
                outitem8.setImageResource(R.drawable.icons_beauty);
                outitem9.setImageResource(R.drawable.icons_travel);
                outitem10.setImageResource(R.drawable.icons_others);
                type = "餐饮";
            }
        });
        outitem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {outitem1.setImageResource(R.drawable.icons_food);
                outitem2.setImageResource(R.drawable.icons_shop2);
                outitem3.setImageResource(R.drawable.icons_traffic);
                outitem4.setImageResource(R.drawable.icons_entertainment);
                outitem5.setImageResource(R.drawable.icons_home);
                outitem6.setImageResource(R.drawable.icons_health);
                outitem7.setImageResource(R.drawable.icons_study);
                outitem8.setImageResource(R.drawable.icons_beauty);
                outitem9.setImageResource(R.drawable.icons_travel);
                outitem10.setImageResource(R.drawable.icons_others);
                type = "购物";
            }
        });
        outitem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outitem1.setImageResource(R.drawable.icons_food);
                outitem2.setImageResource(R.drawable.icons_shop);
                outitem3.setImageResource(R.drawable.icons_traffic2);
                outitem4.setImageResource(R.drawable.icons_entertainment);
                outitem5.setImageResource(R.drawable.icons_home);
                outitem6.setImageResource(R.drawable.icons_health);
                outitem7.setImageResource(R.drawable.icons_study);
                outitem8.setImageResource(R.drawable.icons_beauty);
                outitem9.setImageResource(R.drawable.icons_travel);
                outitem10.setImageResource(R.drawable.icons_others);
                type = "交通";
            }
        });
        outitem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outitem1.setImageResource(R.drawable.icons_food);
                outitem2.setImageResource(R.drawable.icons_shop);
                outitem3.setImageResource(R.drawable.icons_traffic);
                outitem4.setImageResource(R.drawable.icons_entertainment2);
                outitem5.setImageResource(R.drawable.icons_home);
                outitem6.setImageResource(R.drawable.icons_health);
                outitem7.setImageResource(R.drawable.icons_study);
                outitem8.setImageResource(R.drawable.icons_beauty);
                outitem9.setImageResource(R.drawable.icons_travel);
                outitem10.setImageResource(R.drawable.icons_others);
                type = "娱乐";
            }
        });
        outitem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outitem1.setImageResource(R.drawable.icons_food);
                outitem2.setImageResource(R.drawable.icons_shop);
                outitem3.setImageResource(R.drawable.icons_traffic);
                outitem4.setImageResource(R.drawable.icons_entertainment);
                outitem5.setImageResource(R.drawable.icons_home2);
                outitem6.setImageResource(R.drawable.icons_health);
                outitem7.setImageResource(R.drawable.icons_study);
                outitem8.setImageResource(R.drawable.icons_beauty);
                outitem9.setImageResource(R.drawable.icons_travel);
                outitem10.setImageResource(R.drawable.icons_others);
                type = "居家";
            }
        });
        outitem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outitem1.setImageResource(R.drawable.icons_food);
                outitem2.setImageResource(R.drawable.icons_shop);
                outitem3.setImageResource(R.drawable.icons_traffic);
                outitem4.setImageResource(R.drawable.icons_entertainment);
                outitem5.setImageResource(R.drawable.icons_home);
                outitem6.setImageResource(R.drawable.icons_health2);
                outitem7.setImageResource(R.drawable.icons_study);
                outitem8.setImageResource(R.drawable.icons_beauty);
                outitem9.setImageResource(R.drawable.icons_travel);
                outitem10.setImageResource(R.drawable.icons_others);
                type = "医药";
            }
        });
        outitem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outitem1.setImageResource(R.drawable.icons_food);
                outitem2.setImageResource(R.drawable.icons_shop);
                outitem3.setImageResource(R.drawable.icons_traffic);
                outitem4.setImageResource(R.drawable.icons_entertainment);
                outitem5.setImageResource(R.drawable.icons_home);
                outitem6.setImageResource(R.drawable.icons_health);
                outitem7.setImageResource(R.drawable.icons_study2);
                outitem8.setImageResource(R.drawable.icons_beauty);
                outitem9.setImageResource(R.drawable.icons_travel);
                outitem10.setImageResource(R.drawable.icons_others);
                type = "进修";
            }
        });
        outitem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outitem1.setImageResource(R.drawable.icons_food);
                outitem2.setImageResource(R.drawable.icons_shop);
                outitem3.setImageResource(R.drawable.icons_traffic);
                outitem4.setImageResource(R.drawable.icons_entertainment);
                outitem5.setImageResource(R.drawable.icons_home);
                outitem6.setImageResource(R.drawable.icons_health);
                outitem7.setImageResource(R.drawable.icons_study);
                outitem8.setImageResource(R.drawable.icons_beauty2);
                outitem9.setImageResource(R.drawable.icons_travel);
                outitem10.setImageResource(R.drawable.icons_others);
                type = "美妆";
            }
        });
        outitem9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outitem1.setImageResource(R.drawable.icons_food);
                outitem2.setImageResource(R.drawable.icons_shop);
                outitem3.setImageResource(R.drawable.icons_traffic);
                outitem4.setImageResource(R.drawable.icons_entertainment);
                outitem5.setImageResource(R.drawable.icons_home);
                outitem6.setImageResource(R.drawable.icons_health);
                outitem7.setImageResource(R.drawable.icons_study);
                outitem8.setImageResource(R.drawable.icons_beauty);
                outitem9.setImageResource(R.drawable.icons_travel2);
                outitem10.setImageResource(R.drawable.icons_others);
                type = "旅游";
            }
        });
        outitem10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outitem1.setImageResource(R.drawable.icons_food);
                outitem2.setImageResource(R.drawable.icons_shop);
                outitem3.setImageResource(R.drawable.icons_traffic);
                outitem4.setImageResource(R.drawable.icons_entertainment);
                outitem5.setImageResource(R.drawable.icons_home);
                outitem6.setImageResource(R.drawable.icons_health);
                outitem7.setImageResource(R.drawable.icons_study);
                outitem8.setImageResource(R.drawable.icons_beauty);
                outitem9.setImageResource(R.drawable.icons_travel);
                outitem10.setImageResource(R.drawable.icons_others2);
                type = "其他";
            }
        });
        initem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initem1.setImageResource(R.drawable.icons_parents2);
                initem2.setImageResource(R.drawable.icons_parttimejib);
                initem3.setImageResource(R.drawable.icons_prize);
                initem4.setImageResource(R.drawable.icons_dividend);
                initem5.setImageResource(R.drawable.icons_writeoff);
                initem6.setImageResource(R.drawable.icons_work);
                initem7.setImageResource(R.drawable.icons_supply);
                initem8.setImageResource(R.drawable.icons_others);
                type = "生活费";
            }
        });
        initem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initem1.setImageResource(R.drawable.icons_parents);
                initem2.setImageResource(R.drawable.icons_parttimejob2);
                initem3.setImageResource(R.drawable.icons_prize);
                initem4.setImageResource(R.drawable.icons_dividend);
                initem5.setImageResource(R.drawable.icons_writeoff);
                initem6.setImageResource(R.drawable.icons_work);
                initem7.setImageResource(R.drawable.icons_supply);
                initem8.setImageResource(R.drawable.icons_others);
                type = "兼职";
            }
        });
        initem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initem1.setImageResource(R.drawable.icons_parents);
                initem2.setImageResource(R.drawable.icons_parttimejib);
                initem3.setImageResource(R.drawable.icons_prize2);
                initem4.setImageResource(R.drawable.icons_dividend);
                initem5.setImageResource(R.drawable.icons_writeoff);
                initem6.setImageResource(R.drawable.icons_work);
                initem7.setImageResource(R.drawable.icons_supply);
                initem8.setImageResource(R.drawable.icons_others);
                type = "奖金";
            }
        });
        initem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initem1.setImageResource(R.drawable.icons_parents);
                initem2.setImageResource(R.drawable.icons_parttimejib);
                initem3.setImageResource(R.drawable.icons_prize);
                initem4.setImageResource(R.drawable.icons_dividend2);
                initem5.setImageResource(R.drawable.icons_writeoff);
                initem6.setImageResource(R.drawable.icons_work);
                initem7.setImageResource(R.drawable.icons_supply);
                initem8.setImageResource(R.drawable.icons_others);
                type = "分红";
            }
        });
        initem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initem1.setImageResource(R.drawable.icons_parents);
                initem2.setImageResource(R.drawable.icons_parttimejib);
                initem3.setImageResource(R.drawable.icons_prize);
                initem4.setImageResource(R.drawable.icons_dividend);
                initem5.setImageResource(R.drawable.icons_writeoff2);
                initem6.setImageResource(R.drawable.icons_work);
                initem7.setImageResource(R.drawable.icons_supply);
                initem8.setImageResource(R.drawable.icons_others);
                type = "报销";
            }
        });
        initem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initem1.setImageResource(R.drawable.icons_parents);
                initem2.setImageResource(R.drawable.icons_parttimejib);
                initem3.setImageResource(R.drawable.icons_prize);
                initem4.setImageResource(R.drawable.icons_dividend);
                initem5.setImageResource(R.drawable.icons_writeoff);
                initem6.setImageResource(R.drawable.icons_work2);
                initem7.setImageResource(R.drawable.icons_supply);
                initem8.setImageResource(R.drawable.icons_others);
                type = "工资";
            }
        });
        initem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initem1.setImageResource(R.drawable.icons_parents);
                initem2.setImageResource(R.drawable.icons_parttimejib);
                initem3.setImageResource(R.drawable.icons_prize);
                initem4.setImageResource(R.drawable.icons_dividend);
                initem5.setImageResource(R.drawable.icons_writeoff);
                initem6.setImageResource(R.drawable.icons_work);
                initem7.setImageResource(R.drawable.icons_supply2);
                initem8.setImageResource(R.drawable.icons_others);
                type = "补贴";
            }
        });
        initem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initem1.setImageResource(R.drawable.icons_parents);
                initem2.setImageResource(R.drawable.icons_parttimejib);
                initem3.setImageResource(R.drawable.icons_prize);
                initem4.setImageResource(R.drawable.icons_dividend);
                initem5.setImageResource(R.drawable.icons_writeoff);
                initem6.setImageResource(R.drawable.icons_work);
                initem7.setImageResource(R.drawable.icons_supply);
                initem8.setImageResource(R.drawable.icons_others2);
                type = "其他";
            }
        });
item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "食物";
            }
        });
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "购物";
            }
        });

        //提醒判断

        recordBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecordActivity.this,FrameActivity.class);
                startActivity(intent);
            }
        });
        recordConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordConfirm.setImageResource(R.drawable.icons_ok_b1);
                date = dateText.getText().toString();
                time = hour+":"+minutes;
                redate = year+"年"+(month+1)+"月";
                if(editAmount.getText().toString().equals("")){
                    amount = 0;
                }else{
                    amount = Double.parseDouble(editAmount.getText().toString());
                }
                detail = editDetail.getText().toString();

                if(flag == 0) {
                    Outcome outcome = new Outcome();
                    outcome.setOutcomeTime(date);
                    outcome.setReOutcomeTime(redate);
                    outcome.setOutcomeHour(time);
                    outcome.setOutcomeType(type);
                    outcome.setOutcomeAmount(amount);
                    outcome.setOutcomeNote(detail);
                    if (reid == -1) {
                        db.save(outcome);
                    } else {
                        db.update(outcome);
                    }
                    Log.v("save", date + redate + type + amount + detail);
                }else if(flag == 1){
                    Income income = new Income();
                    income.setIncomeTime(date);
                    income.setReIncomeTime(redate);
                    income.setIncomeHour(time);
                    income.setIncomeType(type);
                    income.setIncomeAmount(amount);
                    income.setIncomeNote(detail);
                    if(reid == -1) {
                        db.save(income);
                    } else {
                        db.update(income);
                    }
                }
                Intent intent = new Intent(RecordActivity.this,FrameActivity.class);
                startActivity(intent);
            }
        });



        //为按钮添加日期dialog
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 DatePickerDialog.OnDateSetListener Datelistener=new DatePickerDialog.OnDateSetListener()
                {

                    @Override
                    public void onDateSet(DatePicker view, int myyear, int monthOfYear,int dayOfMonth) {


                        //修改year、month、day的变量�?，以便以后单击按钮时，DatePickerDialog上显示上�?��修改后的�?
                        year=myyear;
                        month=monthOfYear;
                        day=dayOfMonth;
                        //更新日期
                        updateDate();

                    }
                    //当DatePickerDialog关闭时，更新日期显示
                    private void updateDate()
                    {
                        //在TextView上显示日�?
                        dateText.setText(year + "年" + (month+1) + "月" + day + "日");
                    }
                };
Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                Dialog dialog = new DatePickerDialog(RecordActivity.this,  Datelistener, year, month, day);
                dialog.show();
            }
        });
    }
    public void remind(){
        double outAccount=0;  //支出金额
        OutcomeAccountList = db.findAll(Outcome.class);
        RemindList = db.findAll(Remind.class);

        for(int i = 0;i<OutcomeAccountList.size();i++){
            outAccount = outAccount+OutcomeAccountList.get(i).getOutcomeAmount();
        }
        if(RemindList.get(0).getTag() == 0){

        }
    }
    private void viewPager(final List<View> list){
        PagerAdapter pager = new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(list.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(list.get(position));


                return list.get(position);
            }
        };
        mViewPager.setAdapter(pager);
    }
}
*/
