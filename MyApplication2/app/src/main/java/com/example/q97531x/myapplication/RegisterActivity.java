package com.example.q97531x.myapplication;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import model.Bmob.Person;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    //控件
    private EditText userName,email,password,confirm_password;
    private TextView register;
    private Toolbar toolbar;
    //参数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initToolbar();
        initView();
    }

    public void initToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("注册");
        toolbar.setTitleTextColor(Color.parseColor("#FAF4ED"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back01);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initView(){
//        userName = (EditText)findViewById(R.id.userName);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
//        confirm_password = (EditText)findViewById(R.id.confirm_password);
        register = (TextView)findViewById(R.id.register);
        register.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                if(!email.getText().toString().equals("")&&!password.getText().toString().equals("")) {
                    BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
                    //查询playerName叫“比目”的数据
                    bmobQuery.addWhereEqualTo("email", email.getText().toString());
                    bmobQuery.findObjects(this, new FindListener<Person>() {
                        @Override
                        public void onSuccess(List<Person> list) {
                            Log.e("size",list.size()+"");
                            if(list.size()>0){
                                Toast.makeText(RegisterActivity.this,"该邮箱已被注册",Toast.LENGTH_SHORT).show();
                            }else {
                                final Person person = new Person();
//                    person.setUserName(userName.getText().toString());
                                person.setemail(email.getText().toString());
                                person.setpassword(password.getText().toString());
                                person.save(RegisterActivity.this, new SaveListener() {
                                    @Override
                                    public void onSuccess() {
//                            Toast.makeText(RegisterActivity.this, "添加数据成功，返回objectId为：" + person.getObjectId(), Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegisterActivity.this, AvatarActivity.class);
                                        intent.putExtra("objectId",person.getObjectId());
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(int i, String s) {
                                        Toast.makeText(RegisterActivity.this, "添加数据失败,请重试！", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onError(int i, String s) {

                        }
                    });

                }
                break;
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
    }*/
}
