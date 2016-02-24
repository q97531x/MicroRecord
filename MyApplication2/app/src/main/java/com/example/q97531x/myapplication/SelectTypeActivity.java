package com.example.q97531x.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.TypeSelectAdapter;

public class SelectTypeActivity extends AppCompatActivity {
    private ArrayList<String> typeData;
    private ArrayList<Integer> typeIcon;
    private String type;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);
        intent = getIntent();
        type = intent.getStringExtra("type");
        setTypeData(type);
        initToolbar();
        initView();
    }
    public void initToolbar(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back01);
        toolbar.setTitle("选择类别");
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void initView(){
        ListView typeList = (ListView)findViewById(R.id.typeList);
        TypeSelectAdapter adapter = new TypeSelectAdapter(this,typeData,typeIcon);
        typeList.setAdapter(adapter);
        typeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SelectTypeActivity.this,WriteActivity.class);
                Log.e("typeData",typeData.get(position));
                intent.putExtra("selectType",typeData.get(position));
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
    public void setTypeData(String type){
        if(type.equals("outcome")){
            typeData = new ArrayList<>();
            typeIcon = new ArrayList<>();
            typeData.add("餐饮");
            typeData.add("购物");
            typeData.add("交通");
            typeData.add("娱乐");
            typeData.add("居家");
            typeData.add("医药");
            typeData.add("进修");
            typeData.add("人情");
            typeData.add("投资");
            typeData.add("其他");
            typeIcon.add(R.drawable.icons_food);
            typeIcon.add(R.drawable.icons_shop);
            typeIcon.add(R.drawable.icons_traffic);
            typeIcon.add(R.drawable.icons_entertainment);
            typeIcon.add(R.drawable.icons_home);
            typeIcon.add(R.drawable.icons_health);
            typeIcon.add(R.drawable.icons_study);
            typeIcon.add(R.drawable.icons_dividend);
            typeIcon.add(R.drawable.icons_stocks);
            typeIcon.add(R.drawable.icons_others);
        }else{
            typeData = new ArrayList<>();
            typeData.add("餐饮");
            typeData.add("购物");
            typeData.add("交通");
            typeData.add("娱乐");
            typeData.add("居家");
            typeData.add("医药");
            typeData.add("进修");
            typeData.add("人情");
            typeData.add("投资");
            typeData.add("其他");
        }
    }
}
