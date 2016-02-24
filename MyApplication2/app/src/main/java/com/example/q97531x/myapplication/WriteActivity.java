package com.example.q97531x.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Income;
import model.Outcome;

/**
 * Created by 渡渡鸟 on 2016/1/4.
 */
public class WriteActivity extends BaseActivity implements View.OnClickListener{
    //控件
    private TextView title,confirm;
    private ImageView more;
    private EditText editAmount,editDetail,typeEdit;
    //参数
    private Typeface iconfont;
    ArrayList<View> views;
    private int year,month,day;
    private Calendar calendar;
    private static final int GETTYPE = 23333;
    private Intent intent;
    private String type;
    private FinalDb db;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        db = FinalDb.create(this);
        iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        intent = getIntent();
        type = intent.getStringExtra("type");
        views = new ArrayList<>();
        initToolbar();
        initView();
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                title.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        };
    }
    //返回结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == GETTYPE){
                typeEdit.setBackgroundResource(R.drawable.rect_orange_shape);
                typeEdit.setText(data.getStringExtra("selectType"));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirm:
                //保存数据
                if(type.equals("outcome")){
                    Outcome outcome = new Outcome();
                    outcome.setOutcomeTime(title.getText().toString());
                    if(!editAmount.getText().toString().equals("")) {
                        outcome.setOutcomeAmount(Double.parseDouble(editAmount.getText().toString()));
                        if(!typeEdit.getText().toString().equals("")){
                            outcome.setOutcomeType(typeEdit.getText().toString());
                            if(!editDetail.getText().toString().equals("")){
                                outcome.setOutcomeNote(editDetail.getText().toString());
                                db.save(outcome);
                            }else {
                                outcome.setOutcomeNote("");
                                db.save(outcome);
                            }
                        }else{
                            Toast.makeText(this,"选择类别",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(this,"输入金额",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Income income = new Income();
                    income.setIncomeTime(title.getText().toString());
                    if(!editAmount.getText().toString().equals("")) {
                        income.setIncomeAmount(Double.parseDouble(editAmount.getText().toString()));
                        if(!typeEdit.getText().toString().equals("")){
                            income.setIncomeType(typeEdit.getText().toString());
                            if(!editDetail.getText().toString().equals("")){
                                income.setIncomeNote(editDetail.getText().toString());
                                db.save(income);
                            }else {
                                income.setIncomeNote("");
                                db.save(income);
                            }
                        }else{
                            Toast.makeText(this,"选择类别",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(this,"输入金额",Toast.LENGTH_SHORT).show();
                    }
                }
                finish();
                break;
            case R.id.back:
                finish();
                break;
            case R.id.rl_date:
                new DatePickerDialog(WriteActivity.this, onDateSetListener, year, month, day).show();
                break;
            case R.id.more:
                Intent intent = new Intent(this,SelectTypeActivity.class);
                intent.putExtra("type",type);
                startActivityForResult(intent,GETTYPE);
                break;
            default:
                break;
        }
    }
    public void initToolbar(){
        ImageView back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(this);
        RelativeLayout rl_date = (RelativeLayout)findViewById(R.id.rl_date);
        rl_date.setOnClickListener(this);
        TextView selectTime = (TextView)findViewById(R.id.icon_time_select);
        selectTime.setTypeface(iconfont);
        title = (TextView)findViewById(R.id.title);
        title.setText(year + "-" + (month + 1) + "-" + day);
        //确定

    }
    public void initView(){
        more = (ImageView)findViewById(R.id.more);
        confirm = (TextView)findViewById(R.id.confirm);
        editAmount = (EditText)findViewById(R.id.editAmount);
        editDetail = (EditText)findViewById(R.id.editDetail);
        typeEdit = (EditText)findViewById(R.id.type);

        more.setOnClickListener(this);
        confirm.setOnClickListener(this);

    }
}
