package com.example.q97531x.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.tsz.afinal.FinalDb;

import java.util.List;

import model.Password;
import model.User;

public class PasswordActivity extends AppCompatActivity {
    FinalDb db;
    EditText passwordEdit;
    ImageView passwordImg,passwordBack;
    TextView passwordText;
    //ArrayList<String> text = new ArrayList<>();
    int flag = 0,putin = 0;
    boolean haspass = false;
    String password;
    private StringBuffer pass = new StringBuffer("");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        //init
        db = FinalDb.create(PasswordActivity.this);
        passwordEdit = (EditText)findViewById(R.id.passwordEdit);
        passwordImg = (ImageView)findViewById(R.id.passwordImg);
        passwordText = (TextView)findViewById(R.id.passwordText);
        passwordBack = (ImageView)findViewById(R.id.passwordBack);
        passwordBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PasswordActivity.this,FrameActivity.class);
                startActivity(intent);
            }
        });
        passwordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (haspass == false) {
                    flag++;
                    switch (flag) {
                        case 0:
                            passwordImg.setImageResource(R.drawable.cordbg);
                            break;
                        case 1:
                            passwordImg.setImageResource(R.drawable.cord1);
                            break;
                        case 2:
                            passwordImg.setImageResource(R.drawable.cord2);
                            break;
                        case 3:
                            passwordImg.setImageResource(R.drawable.cord3);
                            break;
                        case 4:
                            passwordImg.setImageResource(R.drawable.cord4);
                            break;
                        case 5:
                            passwordImg.setImageResource(R.drawable.cord5);
                            break;
                        case 6:
                            if (putin == 0) {
                                passwordImg.setImageResource(R.drawable.cordbg);
                                pass.append(passwordEdit.getText().toString());
                                Toast.makeText(PasswordActivity.this, "save" + pass, Toast.LENGTH_SHORT).show();
                                password = pass.toString();
                                pass.setLength(0);
                                flag = -1;
                                passwordText.setText("请再次输入六位数密码");
                                passwordEdit.setText("");
                                putin = 1;
                            } else if (putin == 1) {
                                pass.append(passwordEdit.getText().toString());
                                Toast.makeText(PasswordActivity.this, "save" + pass, Toast.LENGTH_SHORT).show();
                                if (password.equals(pass.toString())) {
                                    passwordImg.setImageResource(R.drawable.cord6);
                                    //储存到密码表
                                    List<Password> List = db.findAll(Password.class);
                                    Password pass = new Password();
                                    pass.setPassword(password);
                                    pass.setHasPassword(true);
                                    if(List.size()>0){
                                        db.update(pass,"id like 'i'");
                                    }else {
                                        db.save(pass);
                                    }
                                    //hasPassword haspassword = (hasPassword) getApplication();
                                    //haspassword.setHasPassword(true);
                                    Intent intent = new Intent(PasswordActivity.this, FrameActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(PasswordActivity.this, "密码不符", Toast.LENGTH_SHORT).show();
                                    passwordImg.setImageResource(R.drawable.cordbg);
                                    putin = 0;
                                    passwordText.setText("请输入六位数密码");
                                    password = "";
                                    pass.setLength(0);
                                    flag = -1;
                                }
                            }
                            break;
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_password, menu);
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
