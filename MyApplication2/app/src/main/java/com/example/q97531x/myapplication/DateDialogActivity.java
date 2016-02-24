package com.example.q97531x.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import fragment.ReportFragment;


public class DateDialogActivity extends ActionBarActivity {
    Dialog mdialog;
    Calendar calendar;
    String redate;
    private DatePicker findDatePicker(ViewGroup group) {
        if (group != null) {
            for (int i = 0, j = group.getChildCount(); i < j; i++) {
                View child = group.getChildAt(i);
                if (child instanceof DatePicker) {
                    return (DatePicker) child;
                } else if (child instanceof ViewGroup) {
                    DatePicker result = findDatePicker((ViewGroup) child);
                    if (result != null)
                        return result;
                }
            }
        }
        return null;
    }
    protected Dialog onCreateDialog(int id) { // ��Ӧ�����showDialog(0);//���ڵ�����
        mdialog = null;
        switch (id) {
            case 0:
                calendar = Calendar.getInstance();
                mdialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                //tx.setText(year + "-" + (monthOfYear + 1));
                                //date_year = ""+year;
                                redate = year+"年"+(monthOfYear+1)+"月";
                               /* ReportFragment report = new ReportFragment();
                                report.setArguments(bn);*/
                                Intent intent = new Intent(DateDialogActivity.this,FrameActivity.class);
                                Bundle bn = new Bundle();
                                bn.putInt("msg",-1);
                                bn.putString("date", redate);
                                intent.putExtra("bn",bn);
                                startActivity(intent);
                                Toast.makeText(DateDialogActivity.this,redate,Toast.LENGTH_LONG).show();
                            }
                        }, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                break;
        }
        return mdialog;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_dialog);
        showDialog(0);
        DatePicker dp = findDatePicker((ViewGroup) mdialog.getWindow().getDecorView());
        if (dp != null) {
            ((ViewGroup)((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_date_dialog, menu);
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
