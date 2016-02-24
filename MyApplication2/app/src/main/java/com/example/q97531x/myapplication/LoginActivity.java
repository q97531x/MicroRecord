package com.example.q97531x.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import model.Bmob.Person;

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private EditText login_userName;
    private EditText login_password;
    private TextView btn_login,register;
    //参数
    private boolean login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }
    public void initView(){
        login_userName = (EditText)findViewById(R.id.login_userName);
        login_password = (EditText)findViewById(R.id.login_password);
        btn_login = (TextView)findViewById(R.id.btn_login);
        register = (TextView)findViewById(R.id.register);
        TextView forget_password = (TextView)findViewById(R.id.forget_password);
        btn_login.setOnClickListener(this);
        register.setOnClickListener(this);
        forget_password.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                BmobQuery<Person> query = new BmobQuery<Person>();
                query.addWhereEqualTo("userName",login_userName.getText().toString());
                query.findObjects(this, new FindListener<Person>() {
                    @Override
                    public void onSuccess(List<Person> list) {
                        if (login_password.getText().toString().equals(list.get(0).getpassword())) {
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onError(int i, String s) {
                        Toast.makeText(LoginActivity.this, "用户名未查询到", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.register:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.forget_password:
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
