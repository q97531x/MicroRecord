package fragment.Adapter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.q97531x.myapplication.R;

import net.tsz.afinal.FinalDb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fragment.Broadcast.AlarmReceiver;
import model.AlarmClock;

/**
 * Created by XmacZone on 16/3/1.
 */
public class AlarmAdapter extends BaseExpandableListAdapter{
    private Context context;
    private FinalDb db;
    private ArrayList<Integer> alarmRate;
    private List<AlarmClock> alarmList;
    Time time = new Time();
    private int dayOfWeek;
    private DateFormat df = new SimpleDateFormat("HH:mm");
    private Calendar c1 = Calendar.getInstance(),c2 = Calendar.getInstance();
    public static final long DAY = 1000L * 60 * 60 * 24;
    private boolean sameDay = true;
    private long betweenTime;
    public AlarmAdapter(Context context,FinalDb db,ArrayList<Integer> alarmRate) {
        this.context = context;
        this.db = db;
        this.alarmRate = alarmRate;
        alarmList = db.findAll(AlarmClock.class);
        time.setToNow();
        Log.e("time",time.toString());
        dayOfWeek = time.weekDay;
    }

    @Override
    public int getGroupCount() {
        return alarmList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return alarmList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context, R.layout.alarm_parent_item,null);
        }
        TextView alarm_time = (TextView)convertView.findViewById(R.id.alarm_time);
        TextView day = (TextView)convertView.findViewById(R.id.day);
        alarm_time.setText(alarmList.get(groupPosition).getHourOfDay()+":"+alarmList.get(groupPosition).getMinute());

//        for(int i = 0;i<alarmRate.size();i++){
//            //如果当前日期与数据库日期一致,判断时间
//            if(alarmRate.get(i) == dayOfWeek){
//
//                String selectTime = alarmList.get(groupPosition).getAlarmTime();
//                String currentTime = time.hour+":"+time.minute;
//                Log.e("check",selectTime+"dayofWeek"+currentTime);
//                try {
//                    c1.setTime(df.parse(selectTime));
//                    c2.setTime(df.parse(currentTime));
//
//                if(c1.compareTo(c2)<0){
//                    //设置闹钟
//                    if(i==alarmRate.size()){
//                        if(alarmRate.get(i) == 7){
//                            betweenTime = alarmRate.get(0)*DAY-(df.parse(currentTime).getTime()-(df.parse(selectTime)).getTime());
//                        }else{
//                            betweenTime = (alarmRate.get(0)+7-alarmRate.get(i))*DAY-(df.parse(currentTime).getTime()-(df.parse(selectTime)).getTime());
//                        }
//                    }else{
//                        betweenTime = (alarmRate.get(i+1)-alarmRate.get(i))*DAY - (df.parse(currentTime).getTime()-(df.parse(selectTime)).getTime());
//                    }
//                    Log.e("clock","小");
//                }else if(c1.compareTo(c2)>0){
//                    Log.e("clock","大");
//                    betweenTime = (df.parse(selectTime)).getTime()-df.parse(currentTime).getTime();
//                }
//                }catch (ParseException e){
//                    e.printStackTrace();
//                }
//            }
//
//        }
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,alarmList.get(groupPosition).getHourOfDay());
        calendar.set(Calendar.MINUTE,alarmList.get(groupPosition).getMinute());
        calendar.set(Calendar.SECOND,alarmList.get(groupPosition).getSecond());
        calendar.set(Calendar.MILLISECOND,alarmList.get(groupPosition).getMillSecond());
        final Switch switch_btn = (Switch)convertView.findViewById(R.id.switch_btn);
        switch_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(context, AlarmReceiver.class);
                //intent.setAction("alarm");
                PendingIntent sender = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
                if(isChecked){
                    //注册闹铃
                    Log.e("resg","yes"+betweenTime);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), DAY, sender);
//                    switch_btn.setChecked(true);
                }else{
                    //alarmManager.cancel(sender);
//                    switch_btn.setChecked(false);
                }
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
