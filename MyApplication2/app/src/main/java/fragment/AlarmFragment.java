package fragment;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.q97531x.myapplication.R;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.Calendar;

import model.AlarmClock;


/**
 * Created by XmacZone on 16/3/1.
 * 提醒
 */
public class AlarmFragment extends Fragment implements View.OnClickListener{
    private ListView clockList;
    private Time t = new Time("GMT+8");
    private int weekDay,hour,minute;
    private FinalDb db;
    private ArrayList<Integer> alarmRate = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Typeface iconfont = Typeface.createFromAsset(getActivity().getAssets(), "iconfont.ttf");
        db = FinalDb.create(getActivity());
        View view = inflater.inflate(R.layout.fragment_alarm,container,false);
        clockList = (ListView)view.findViewById(R.id.clockList);
        TextView add_clock = (TextView)view.findViewById(R.id.add_clock);
        add_clock.setTypeface(iconfont);
        add_clock.setOnClickListener(this);
        t.setToNow();
        for(int i=1;i<8;i++){
            alarmRate.add(i);
        }
        weekDay = t.weekDay;
        hour = t.hour;
        minute = t.minute;
        Log.e("week",""+t.weekDay+t.monthDay);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_clock:
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        AlarmClock alarmClock = new AlarmClock();
                        alarmClock.setAlarmTime(hourOfDay+":"+minute);
                        alarmClock.setAlarmRate(alarmRate);
                        db.save(alarmClock);
                    }
                },hour,minute,true);
                timePickerDialog.show();
                break;
        }
    }
}
