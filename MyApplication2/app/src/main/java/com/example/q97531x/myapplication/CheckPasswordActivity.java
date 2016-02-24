package com.example.q97531x.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import net.tsz.afinal.FinalDb;

import java.util.List;

import model.User;

public class CheckPasswordActivity extends AppCompatActivity {
    FinalDb db;
    EditText passwordEdit;
    ImageView passwordImg;
    int flag = 0;
    MyApplication myApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_password);
        myApplication = (MyApplication) getApplication();
        passwordEdit = (EditText)findViewById(R.id.passwordEdit1);
        passwordImg = (ImageView)findViewById(R.id.passwordImg1);
        db = FinalDb.create(CheckPasswordActivity.this);
        passwordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
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
                            passwordImg.setImageResource(R.drawable.cord6);
                            List<User> userList = db.findAll(User.class);
                            Log.e("save",""+userList.size());
                            if(userList.get(0).getPassword().toString().equals(passwordEdit.getText().toString())){
                                /*hasPassword hasPassword = (hasPassword) getApplication();
                                hasPassword.setHasPassword(false);*/
                                User user = new User();
                                user.setHasPassword(false);
                                db.save(user);

                                Log.e("user",""+userList.get(userList.size()-1).getHasPassword());
                                myApplication.isLocked = false;
                                Intent intent = new Intent(CheckPasswordActivity.this,FrameActivity.class);
                                startActivity(intent);

                            }else{
                                passwordEdit.setText("");
                                flag = -1;
                            }
                        break;
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
        getMenuInflater().inflate(R.menu.menu_check_password, menu);
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
