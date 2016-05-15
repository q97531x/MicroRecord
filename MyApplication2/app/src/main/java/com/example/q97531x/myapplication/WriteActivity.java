package com.example.q97531x.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import com.example.q97531x.myapplication.Broadcast.RemindBroadcast;

import net.tsz.afinal.FinalDb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Budget;
import model.Contact;
import model.Income;
import model.Outcome;
import model.Remind;
import util.Utils;

/**
 * Created by 渡渡鸟 on 2016/1/4.
 */
public class WriteActivity extends BaseActivity implements View.OnClickListener {
    //控件
    private TextView title, confirm;
    private ImageView more;
    private EditText editAmount, editDetail;
    private TextView typeEdit;
    //参数
    private Typeface iconfont;
    ArrayList<View> views;
    private int year, month, day, weekofMonth, dayofWeek;
    private Calendar calendar;
    private boolean isComplete = false;
    private static final int GETTYPE = 23333;
    private Intent intent, it;
    private String type, aim, id;
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
        dayofWeek = calendar.get(Calendar.DAY_OF_WEEK);
        weekofMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        intent = getIntent();
        if (intent.getStringExtra("id") != null) {
            id = intent.getStringExtra("id");
        }
        type = intent.getStringExtra("type");
        Log.e("type",type);
        aim = intent.getStringExtra("aim");
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
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GETTYPE) {
//                typeEdit.setBackgroundResource(R.drawable.rect_orange_shape);
                typeEdit.setText(data.getStringExtra("selectType"));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm:
                if (aim.equals("create")) {
                    //保存数据
                    if (type.equals("outcome")) {
                        Outcome outcome = new Outcome();
                        outcome.setOutcomeTime(title.getText().toString());
                        outcome.setReOutcomeTime(year + "-" + (month + 1));
                        outcome.setOutcomeMonth(year + "-" + (month + 1));
                        outcome.setOutcomeWeek(weekofMonth + "");
                        if (!editAmount.getText().toString().equals("")) {
                            outcome.setOutcomeAmount((float) Double.parseDouble(editAmount.getText().toString()));
                            if (!typeEdit.getText().toString().equals("")) {
                                outcome.setOutcomeType(typeEdit.getText().toString());
                                if (!editDetail.getText().toString().equals("")) {
                                    outcome.setOutcomeNote(editDetail.getText().toString());
                                    db.save(outcome);
                                    isComplete = true;
                                } else {
                                    outcome.setOutcomeNote("");
                                    db.save(outcome);
                                    isComplete = true;
                                }
                            } else {
                                Toast.makeText(this, "选择类别", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(this, "输入金额", Toast.LENGTH_SHORT).show();
                        }
                        isOverSpend(db);
                        jumpToFrame();
                    } else {
                        Income income = new Income();
                        income.setIncomeTime(title.getText().toString());
                        income.setReIncomeTime(year + "-" + (month + 1));
                        income.setIncomeMonth(year + "-" + (month + 1));
                        income.setIncomeWeek(weekofMonth + "");
                        Log.e("date",year+"-"+(month+1));
                        if (!editAmount.getText().toString().equals("")) {
                            income.setIncomeAmount((float)Double.parseDouble(editAmount.getText().toString()));
                            if (!typeEdit.getText().toString().equals("")) {
                                income.setIncomeType(typeEdit.getText().toString());
                                if (!editDetail.getText().toString().equals("")) {
                                    income.setIncomeNote(editDetail.getText().toString());
                                    db.save(income);
                                    Log.e("save","save");
                                    isComplete = true;
                                } else {
                                    income.setIncomeNote("");
                                    db.save(income);
                                    isComplete = true;
                                }
                            } else {
                                Toast.makeText(this, "选择类别", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(this, "输入金额", Toast.LENGTH_SHORT).show();
                        }
                        jumpToFrame();
                    }
                } else {
                    if (type.equals("outcome")) {
                        Outcome outcome = new Outcome();
                        outcome.setOutcomeTime(title.getText().toString());
                        outcome.setReOutcomeTime(year + "-" + (month + 1));
                        outcome.setOutcomeMonth(year + "-" + (month + 1));
                        outcome.setOutcomeWeek(weekofMonth + "");
                        if (!editAmount.getText().toString().equals("")) {
                            outcome.setOutcomeAmount((float) Double.parseDouble(editAmount.getText().toString()));
                            if (!typeEdit.getText().toString().equals("")) {
                                outcome.setOutcomeType(typeEdit.getText().toString());
                                if (!editDetail.getText().toString().equals("")) {
                                    outcome.setOutcomeNote(editDetail.getText().toString());
                                    db.update(outcome, " outcomeId=\"" + id + "\"");
                                    Log.e("update", "update" + id);
                                    isComplete = true;
                                } else {
                                    outcome.setOutcomeNote("");
                                    db.update(outcome, " outcomeId=\"" + id + "\"");
                                    Log.e("update", "update" + id);
                                    isComplete = true;
                                }
                            } else {
                                Toast.makeText(this, "选择类别", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(this, "输入金额", Toast.LENGTH_SHORT).show();
                        }
                        isOverSpend(db);
                        jumpToFrame();
                    } else {
                        Income income = new Income();
                        income.setIncomeTime(title.getText().toString());
                        income.setReIncomeTime(year + "-" + (month + 1));
                        income.setIncomeMonth(year + "-" + (month + 1));
                        income.setIncomeWeek(weekofMonth + "");
                        if (!editAmount.getText().toString().equals("")) {
                            income.setIncomeAmount((float)Double.parseDouble(editAmount.getText().toString()));
                            if (!typeEdit.getText().toString().equals("")) {
                                income.setIncomeType(typeEdit.getText().toString());
                                if (!editDetail.getText().toString().equals("")) {
                                    income.setIncomeNote(editDetail.getText().toString());
                                    db.update(income);
                                    isComplete = true;
                                } else {
                                    income.setIncomeNote("");
                                    db.update(income);
                                    isComplete = true;
                                }
                            } else {
                                Toast.makeText(this, "选择类别", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(this, "输入金额", Toast.LENGTH_SHORT).show();
                        }
                        jumpToFrame();
                    }
                }
                if (isComplete) {
                    finish();
                }
                break;
            case R.id.back:
                finish();
                break;
            case R.id.rl_date:
                new DatePickerDialog(WriteActivity.this, onDateSetListener, year, month, day).show();
                break;
            case R.id.more:
                Intent intent = new Intent(this, SelectTypeActivity.class);
                intent.putExtra("type", type);
                startActivityForResult(intent, GETTYPE);
                break;
            default:
                break;
        }
    }

    public void initToolbar() {
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        RelativeLayout rl_date = (RelativeLayout) findViewById(R.id.rl_date);
        rl_date.setOnClickListener(this);
        TextView selectTime = (TextView) findViewById(R.id.icon_time_select);
        selectTime.setTypeface(iconfont);
        title = (TextView) findViewById(R.id.title);
        title.setText(year + "-" + (month + 1) + "-" + day);
        //确定

    }

    public void initView() {
        more = (ImageView) findViewById(R.id.more);
        confirm = (TextView) findViewById(R.id.confirm);
        editAmount = (EditText) findViewById(R.id.editAmount);
        editDetail = (EditText) findViewById(R.id.editDetail);
        typeEdit = (TextView) findViewById(R.id.type);
        if (id != null) {
            if(type.equals("outcome")) {
                Outcome outcome = db.findById(id, Outcome.class);
                if (aim.equals("update")) {
                    editAmount.setText(outcome.getOutcomeAmount() + "");
                    typeEdit.setText(outcome.getOutcomeType());
                    editDetail.setText(outcome.getOutcomeNote());
                    typeEdit.setBackgroundResource(R.color.orange);
                }
            }else {
                Income income = db.findById(id,Income.class);
                if(aim.equals("update")){
                    editAmount.setText(income.getIncomeAmount() + "");
                    typeEdit.setText(income.getIncomeType());
                    editDetail.setText(income.getIncomeNote());
                    typeEdit.setBackgroundResource(R.color.orange);
                }
            }
        }else{
            if(type.equals("outcome")){
                typeEdit.setText("餐饮");
            }else {
                typeEdit.setText("生活费");
            }
        }
        more.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }
    //跳转
    public void jumpToFrame(){
        it = new Intent();
        setResult(Activity.RESULT_OK, intent);
    }
    //判断是否超支
    public void isOverSpend(FinalDb db) {
        //获得设置百分比
        float budget = 0, outcome = 0;
        List<Remind> reminds = db.findAll(Remind.class);
        if (reminds.size() > 0) {
            float percent = reminds.get(0).getPercent() / 100;
            float py;
            List<Budget> budgets = db.findAll(Budget.class);
            List<Outcome> outcomes = db.findAll(Outcome.class);
            if (budgets.size() > 0 && outcomes.size() > 0) {
                for (int i = 0; i < budgets.size(); i++) {
                    budget = budget + budgets.get(i).getBudgetAccount();
                }
                for (int i = 0; i < outcomes.size(); i++) {
                    outcome = outcome + outcomes.get(i).getOutcomeAmount();
                }
                py = outcome / budget;
                if (py >= percent) {
                    //超支
                    Intent intent = new Intent(this, RemindBroadcast.class);
                    intent.setAction("Sms");

                    /*switch (reminds.get(0).getStyle()) {
                        case 0:
                            Log.e("vb", "vb");
                            intent.setAction("Vibrator");
                            sendBroadcast(intent);

                            break;
                        case 1:
                            intent.setAction("Ring");
                            sendBroadcast(intent);
                            break;
                        case 2:
                            intent.setAction("Sms");
                           *//* intent.putExtra("phoneNum", reminds.get(0).getPhoneNumber());
                            intent.putExtra("phoneName", reminds.get(0).getName());*//*
                            sendBroadcast(intent);
//                        sendSms(reminds.get(0).getPhoneNumber(),reminds.get(0).getName());
//                        finish();
                            break;
                    }*/
                }
            }
        }
    }
}
