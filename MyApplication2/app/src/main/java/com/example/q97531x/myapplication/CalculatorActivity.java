package com.example.q97531x.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.tsz.afinal.FinalDb;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import model.Budget;


public class CalculatorActivity extends ActionBarActivity {
    TextView result,add,remove,multiply,divide,num_one,num_two,num_three,num_four,num_five,num_six,num_seven,num_eight,num_nine,num_zero,point,equal,
            clear,backspace,confirm,minus;
    ImageView back;
    String command = null,type,budgetDate;
    int tag,year,month;
    BigDecimal base ;
    private StringBuffer str_num = new StringBuffer("");
    double num1 = 0,num2 = 0;
    int count1 = 0,count2 = 0,id=0;
    Boolean haspoint = false,flag = false;
    FinalDb db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        db = FinalDb.create(this);
        Intent it = getIntent();
        Bundle bundle = it.getBundleExtra("bd");
        type = bundle.getString("type");
        Log.e("type", type);
        tag = bundle.getInt("tag");
        Log.e("tag",""+tag);
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        budgetDate = year+"年"+(month+1)+"月";
        initView();
        initListener();
    }
    //��ʼ���ؼ�
    public void initView(){
        result = (TextView)findViewById(R.id.result);
        add = (TextView)findViewById(R.id.add);
        remove = (TextView)findViewById(R.id.remove);
        multiply = (TextView)findViewById(R.id.multiply);
        divide = (TextView)findViewById(R.id.divide);
        num_one = (TextView)findViewById(R.id.num_one);
        num_two = (TextView)findViewById(R.id.num_two);
        num_three = (TextView)findViewById(R.id.num_three);
        num_four = (TextView)findViewById(R.id.num_four);
        num_five = (TextView)findViewById(R.id.num_five);
        num_six = (TextView)findViewById(R.id.num_six);
        num_seven = (TextView)findViewById(R.id.num_seven);
        num_eight = (TextView)findViewById(R.id.num_eight);
        num_nine = (TextView)findViewById(R.id.num_nine);
        num_zero = (TextView)findViewById(R.id.num_zero);
        point = (TextView)findViewById(R.id.point);
        equal = (TextView)findViewById(R.id.equal);
        clear = (TextView)findViewById(R.id.clear);
        backspace = (TextView)findViewById(R.id.backspace);
        confirm = (TextView)findViewById(R.id.confirm);
        minus =  (TextView)findViewById(R.id.minus);
        back = (ImageView)findViewById(R.id.back);
    }
   //
    public void initListener(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculatorActivity.this,FrameActivity.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == false) {
                    if(command==null) {
                        if (str_num.toString() != "") {
                            num1 = Double.parseDouble(str_num.toString());
                            str_num = new StringBuffer("");
                        } else if (str_num.toString() == "") {
                            num1 = 0.0;
                            str_num = new StringBuffer("");
                        }
                    }
                }else {
                    flag = true;
                }
                command = "+";
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == false) {
                    if(command==null) {
                        if (str_num.toString() != "") {
                            num1 = Double.parseDouble(str_num.toString());
                            str_num = new StringBuffer("");
                        } else if (str_num.toString() == "") {
                            num1 = 0.0;
                            str_num = new StringBuffer("");
                        }
                    }
                }else {
                    flag = true;
                }
                command = "-";
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == false) {
                    if(command==null) {
                        if (str_num.toString() != "") {
                            num1 = Double.parseDouble(str_num.toString());
                            str_num = new StringBuffer("");
                        } else if (str_num.toString() == "") {
                            num1 = 0.0;
                            str_num = new StringBuffer("");
                        }
                    }
                }else if(flag == true){
                    flag = false;
                }
                command = "*";
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == false) {
                    if(command==null) {
                        if (str_num.toString() != "") {
                            num1 = Double.parseDouble(str_num.toString());
                            str_num = new StringBuffer("");
                        } else if (str_num.toString() == "") {
                            num1 = 0.0;
                            str_num = new StringBuffer("");
                        }
                    }
                }else {
                    flag = true;
                }
                command = "/";
            }
        });
        num_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str_num.length()<9) {
                    str_num.append("1");
                }
                result.setText(str_num.toString());
            }
        });
        num_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str_num.length()<9) {
                    str_num.append("2");
                }
                result.setText(str_num.toString());
            }
        });
        num_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str_num.length()<9) {
                    str_num.append("3");
                }
                result.setText(str_num.toString());
            }
        });
        num_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str_num.length()<9) {
                    str_num.append("4");
                }
                result.setText(str_num.toString());
            }
        });
        num_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str_num.length()<9) {
                    str_num.append("5");
                }
                result.setText(str_num.toString());
            }
        });
        num_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str_num.length()<9) {
                    str_num.append("6");
                }
                result.setText(str_num.toString());
            }
        });
        num_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str_num.length()<9) {
                    str_num.append("7");
                }
                result.setText(str_num.toString());
            }
        });
        num_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str_num.length()<9) {
                    str_num.append("8");
                }
                result.setText(str_num.toString());
            }
        });
        num_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str_num.length()<9) {
                    str_num.append("9");
                }
                result.setText(str_num.toString());
            }
        });
        num_zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str_num.length()<9&&Double.parseDouble(str_num.toString())>0) {
                    str_num.append("0");
                }
                result.setText(str_num.toString());
            }
        });
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(haspoint == false){
                    haspoint = true;
                    if(str_num.length()<9) {
                        str_num.append(".");
                    }
                    result.setText(str_num.toString());
                }
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //��������
                if(str_num.toString()!=""){
                    num2 = Double.parseDouble(str_num.toString());
                }else{
                    num2 = 0;
                }
                if(command.equals("+")){
                    num1 = num1+num2;
                }else if(command.equals("-")){
                    num1 = num1-num2;
                }else if(command.equals("*")){
                    num1 = num1*num2;
                }else if(command.equals("/")){
                    if(num2 == 0){
                        num1 = 0;
                        Toast.makeText(CalculatorActivity.this,"0不能被整除",Toast.LENGTH_LONG).show();
                    }else {
                        num1 = num1 / num2;
                    }
                }else if(command == null){
                    num1 = num1+0;
                }
                flag = true;
                str_num = new StringBuffer("");
                result.setText(""+String.valueOf((num1)));
                //��ʼ�����б���
                count1 = 0;
                count2 = 0;
                num2 = 0;
                haspoint = false;
                command = null;
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //��ʼ��
                count1 = 0;
                count2 = 0;
                str_num = new StringBuffer("");
                num1 = 0;
                num2 = 0;
                haspoint = false;
                command = null;
                result.setText(""+0);
            }
        });
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str_num.length()>1){
                    str_num = new StringBuffer( str_num.substring(0,str_num.length()-1));
                    Log.e("str",str_num.substring(0,str_num.length()-1));
                    result.setText(str_num.toString());
                }else if(str_num.length()<=1){
                    str_num.delete(0,str_num.length());
                    result.setText("0");
                }
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str_num.toString()=="") {
                    if (result.getText().equals("0")) {
                        result.setText("-0");
                    } else if (result.getText().equals("-0")) {
                        result.setText("0");
                    }
                }else {
                    String a = str_num.substring(0,1);
                    if (a.equals("-")) {
                        result.setText(str_num.delete(0, 1));
                    } else if (!a.equals("-")) {
                        result.setText(str_num.insert(0, "-").toString());
                    }
                }
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Budget budget = new Budget();
                budget.setBudgetType(type);
                budget.setBudgetDate(budgetDate);
                budget.setBudgetAccount(Double.parseDouble(result.getText().toString()));
                if(tag == 1) {
                    db.update(budget," budgetType=\"" +type + "\"");
                    Log.e("update","update");
                }else if(tag == -1){
                    db.save(budget);
                    Log.e("save","save");
                }
                Log.e("DB", "" + budget.getBudgetAccount());
                Intent intent = new Intent(CalculatorActivity.this,FrameActivity.class);
                startActivity(intent);
            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
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
