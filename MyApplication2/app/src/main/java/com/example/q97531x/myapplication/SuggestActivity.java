package com.example.q97531x.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SuggestActivity extends AppCompatActivity {
    EditText suggest;
    Button support;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
        support = (Button)findViewById(R.id.support);
        suggest = (EditText)findViewById(R.id.suggest);
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //由于暂时没有服务端与服务端程序，故用此方法代替
                if(suggest.getText().toString()!= ""){
                suggest.setText("");}else{
                    Toast.makeText(SuggestActivity.this,"请留下您宝贵的意见",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_suggest, menu);
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
