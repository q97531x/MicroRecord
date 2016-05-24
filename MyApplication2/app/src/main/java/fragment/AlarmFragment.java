package fragment;


import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.q97531x.myapplication.R;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fragment.Adapter.AlarmAdapter;
import model.AlarmClock;


/**
 * Created by XmacZone on 16/3/1.
 * 提醒
 */
public class AlarmFragment extends Fragment implements View.OnClickListener{
    private ExpandableListView clockList;
    private Time t = new Time();
    private int weekDay,hour,minute;
    private AlarmAdapter adapter;
    private FinalDb db;
    private boolean clicked = false;
    private ArrayList<Integer> alarmRate = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        Typeface iconfont = Typeface.createFromAsset(getActivity().getAssets(), "iconfont.ttf");
        db = FinalDb.create(getActivity());
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        clockList = (ExpandableListView)view.findViewById(R.id.clockList);
        t.setToNow();
        TextView add_clock = (TextView)view.findViewById(R.id.add_clock);
        add_clock.setTypeface(iconfont);
        add_clock.setOnClickListener(this);
        t.setToNow();
        for(int i=0;i<7;i++){
            alarmRate.add(i);
        }
        adapter = new AlarmAdapter(getActivity(),db,alarmRate,this);
        clockList.setAdapter(adapter);
        clockList.setGroupIndicator(null);
        /*List<AlarmClock> alarmClocks = db.findAll(AlarmClock.class);
        for (int i = 0;i<alarmClocks.size();i++){
            clockList.expandGroup(i);
        }*/
        clockList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.e("click","click");

//                clockList.expandGroup(groupPosition);
               /* adapter.getGroupView(groupPosition).
                clicked = !clicked;*/
                return false;
            }
        });
        /*clockList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Log.e("Long","LongClick");
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("是否确定删除该闹钟");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteById(AlarmClock.class,adapter.getGroupId(position));
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                return true;
            }
        });*/
        weekDay = t.weekDay;
        hour = t.hour;
        minute = t.minute;
//        Log.e("week",""+t.weekDay+t.monthDay);
        return view;
    }
    public void reflesh(){
        Log.e("tag","reflesh");
        adapter = new AlarmAdapter(getActivity(),db,alarmRate,this);
        //adapter.notifyDataSetChanged();
        clockList.setAdapter(adapter);
    }
    public void closeItem(int groupPosition){
        clockList.collapseGroup(groupPosition);
    }
    public void setTitle(Toolbar toolbar){
        toolbar.setTitle("提醒");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_clock:
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        AlarmClock alarmClock = new AlarmClock();
                        alarmClock.setHourOfDay(hourOfDay);
                        alarmClock.setMinute(minute);
                        alarmClock.setSecond(Calendar.SECOND);
                        alarmClock.setMillSecond(Calendar.MILLISECOND);
                        alarmClock.setIsCheck(false);
                        Log.e("setClock", hourOfDay + ":" + minute);
                        alarmClock.setAlarmRate(alarmRate);
                        db.save(alarmClock);
                    }
                },hour,minute,true);
                timePickerDialog.show();
                timePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
//                        adapter = new AlarmAdapter(getActivity(),db,alarmRate);
                        AlarmFragment amFragment = new AlarmFragment();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentBox, amFragment).commit();
//                        adapter.notifyDataSetChanged();

                    }
                });
                break;
        }
    }
}
