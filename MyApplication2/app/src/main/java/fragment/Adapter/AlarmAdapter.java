package fragment.Adapter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.q97531x.myapplication.Broadcast.RemindBroadcast;
import com.example.q97531x.myapplication.R;

import net.tsz.afinal.FinalDb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//import fragment.Broadcast.AlarmReceiver;
import fragment.AlarmFragment;
import fragment.Broadcast.AlarmReceiver;
import model.AlarmClock;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by XmacZone on 16/3/1.
 */
public class AlarmAdapter extends BaseExpandableListAdapter {
    private Context context;
    private FinalDb db;
    private ArrayList<Integer> alarmRate;
    private List<AlarmClock> alarmList;
    Time time = new Time();
    private int dayOfWeek;
    private DateFormat df = new SimpleDateFormat("HH:mm");
    private LayoutInflater inflater;
    TextView day;
    private RelativeLayout rl_group;
    AlarmFragment fragment;
    private ArrayList<String> rateList = new ArrayList<>();
    Observable<ArrayList<String>> myObservable;
    Subscriber<ArrayList<String>> mySubscriber;
    private Calendar c1 = Calendar.getInstance(), c2 = Calendar.getInstance();
    public static final long DAY = 1000L * 60 * 60 * 24;
    private boolean sameDay = true;
    private Typeface iconfont;
    private long betweenTime;

    public AlarmAdapter(Context context, FinalDb db, ArrayList<Integer> alarmRate, AlarmFragment fragment) {
        this.context = context;
        this.db = db;
        this.alarmRate = alarmRate;
        this.inflater = LayoutInflater.from(context);
        this.fragment = fragment;
        alarmList = db.findAll(AlarmClock.class);
        time.setToNow();
        Observerble(rateList);
//        Log.e("time",time.toString());
        dayOfWeek = time.weekDay;
        iconfont = Typeface.createFromAsset(context.getAssets(), "iconfont.ttf");
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
        return 0;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.alarm_parent_item, null);
        }
        rl_group = (RelativeLayout) convertView.findViewById(R.id.rl_group);
        TextView alarm_time = (TextView) convertView.findViewById(R.id.alarm_time);
        day = (TextView) convertView.findViewById(R.id.day);
        if (alarmList.get(groupPosition).getMinute() < 10) {
            alarm_time.setText(alarmList.get(groupPosition).getHourOfDay() + ":" + "0" + alarmList.get(groupPosition).getMinute());
        } else {
            alarm_time.setText(alarmList.get(groupPosition).getHourOfDay() + ":" + alarmList.get(groupPosition).getMinute());
        }

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
//        Subscriber(day);
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, alarmList.get(groupPosition).getHourOfDay());
        calendar.set(Calendar.MINUTE, alarmList.get(groupPosition).getMinute());
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        final Switch switch_btn = (Switch) convertView.findViewById(R.id.switch_btn);
        if (alarmList.get(groupPosition).isCheck()) {
            switch_btn.setChecked(true);
        } else {
            switch_btn.setChecked(false);
        }
        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        switch_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intent = new Intent(context, AlarmReceiver.class);
                intent.setAction("alarm");
                PendingIntent sender = PendingIntent.getBroadcast(context, groupPosition, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                List<AlarmClock> list = db.findAll(AlarmClock.class);
                if (isChecked) {
                    //注册闹铃
//                    Log.e("resg", "yes" + calendar.getTimeInMillis());
                    //如果设置时间小于当前时间,则延后一天
                    if (calendar.getTimeInMillis() - System.currentTimeMillis() < 0) {
                        calendar.roll(Calendar.DATE, 1);
                    }
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), DAY, sender);

                    AlarmClock alarmClock = db.findById(list.get(groupPosition).getAlarmId(), AlarmClock.class);
                    alarmClock.setIsCheck(true);
                    db.update(alarmClock);
//                    switch_btn.setChecked(true);
                } else {
                    AlarmClock alarmClock = db.findById(list.get(groupPosition).getAlarmId(), AlarmClock.class);
                    alarmClock.setIsCheck(false);
                    db.update(alarmClock);
                    alarmManager.cancel(sender);
                }
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_alarm_child, parent, false);
            holder = new ViewHolder();
            holder.icon_rubbish = (TextView) convertView.findViewById(R.id.icon_rubbish);
            holder.icon_confirm = (TextView) convertView.findViewById(R.id.icon_confirm);
            holder.tx_zero = (TextView) convertView.findViewById(R.id.tx_zero);
            holder.tx_one = (TextView) convertView.findViewById(R.id.tx_one);
            holder.tx_two = (TextView) convertView.findViewById(R.id.tx_two);
            holder.tx_three = (TextView) convertView.findViewById(R.id.tx_three);
            holder.tx_four = (TextView) convertView.findViewById(R.id.tx_four);
            holder.tx_five = (TextView) convertView.findViewById(R.id.tx_five);
            holder.tx_six = (TextView) convertView.findViewById(R.id.tx_six);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.icon_rubbish.setTypeface(iconfont);
        holder.icon_confirm.setTypeface(iconfont);
        holder.icon_rubbish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("click", "rubbish");
                List<AlarmClock> list = db.findAll(AlarmClock.class);
                if (list.size() > 0) {
                    db.deleteById(AlarmClock.class, list.get(groupPosition).getAlarmId());
                    fragment.reflesh();
                }
            }
        });
        holder.tx_zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.tx_zero.getCurrentTextColor() == context.getResources().getColor(R.color.lightgrey)) {
                    holder.tx_zero.setTextColor(context.getResources().getColor(R.color.white));
                    holder.tx_zero.setBackgroundResource(R.drawable.grey_select_circle);
                    rateList.add("日");
                } else {
                    holder.tx_zero.setTextColor(context.getResources().getColor(R.color.lightgrey));
                    holder.tx_zero.setBackgroundResource(R.color.white);
                    rateList.remove("日");
                }
                Observerble(rateList);
                Subscriber(day);
            }
        });
        holder.tx_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.tx_one.getCurrentTextColor() == context.getResources().getColor(R.color.lightgrey)) {
                    holder.tx_one.setTextColor(context.getResources().getColor(R.color.white));
                    holder.tx_one.setBackgroundResource(R.drawable.grey_select_circle);
                    rateList.add("一");
                } else {
                    holder.tx_one.setTextColor(context.getResources().getColor(R.color.lightgrey));
                    holder.tx_one.setBackgroundResource(R.color.white);
                    rateList.remove("一");
                }
                Observerble(rateList);
                Subscriber(day);
            }
        });
        holder.tx_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.tx_two.getCurrentTextColor() == context.getResources().getColor(R.color.lightgrey)) {
                    holder.tx_two.setTextColor(context.getResources().getColor(R.color.white));
                    holder.tx_two.setBackgroundResource(R.drawable.grey_select_circle);
                    rateList.add("二");
                } else {
                    holder.tx_two.setTextColor(context.getResources().getColor(R.color.lightgrey));
                    holder.tx_two.setBackgroundResource(R.color.white);
                    rateList.remove("二");
                }
                Observerble(rateList);
                Subscriber(day);
            }
        });
        holder.tx_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.tx_three.getCurrentTextColor() == context.getResources().getColor(R.color.lightgrey)) {
                    holder.tx_three.setTextColor(context.getResources().getColor(R.color.white));
                    holder.tx_three.setBackgroundResource(R.drawable.grey_select_circle);
                    rateList.add("三");
                } else {
                    holder.tx_three.setTextColor(context.getResources().getColor(R.color.lightgrey));
                    holder.tx_three.setBackgroundResource(R.color.white);
                    rateList.remove("三");
                }
                Observerble(rateList);
                Subscriber(day);
            }
        });
        holder.tx_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.tx_four.getCurrentTextColor() == context.getResources().getColor(R.color.lightgrey)) {
                    holder.tx_four.setTextColor(context.getResources().getColor(R.color.white));
                    holder.tx_four.setBackgroundResource(R.drawable.grey_select_circle);
                    rateList.add("四");
                } else {
                    holder.tx_four.setTextColor(context.getResources().getColor(R.color.lightgrey));
                    holder.tx_four.setBackgroundResource(R.color.white);
                    rateList.remove("四");
                }
                Observerble(rateList);
                Subscriber(day);
            }
        });
        holder.tx_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.tx_five.getCurrentTextColor() == context.getResources().getColor(R.color.lightgrey)) {
                    holder.tx_five.setTextColor(context.getResources().getColor(R.color.white));
                    holder.tx_five.setBackgroundResource(R.drawable.grey_select_circle);
                    rateList.add("五");
                } else {
                    holder.tx_five.setTextColor(context.getResources().getColor(R.color.lightgrey));
                    holder.tx_five.setBackgroundResource(R.color.white);
                    rateList.remove("五");
                }
                Observerble(rateList);
                Subscriber(day);
            }
        });
        holder.tx_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.tx_six.getCurrentTextColor() == context.getResources().getColor(R.color.lightgrey)) {
                    holder.tx_six.setTextColor(context.getResources().getColor(R.color.white));
                    holder.tx_six.setBackgroundResource(R.drawable.grey_select_circle);
                    rateList.add("六");
                } else {
                    holder.tx_six.setTextColor(context.getResources().getColor(R.color.lightgrey));
                    holder.tx_six.setBackgroundResource(R.color.white);
                    rateList.remove("六");
                }
                Observerble(rateList);
                Subscriber(day);
            }
        });
        holder.icon_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.closeItem(groupPosition);
            }
        });
        return convertView;
    }

    private void Observerble(final ArrayList<String> s) {
        myObservable = Observable.create(new Observable.OnSubscribe<ArrayList<String>>() {
            @Override
            public void call(Subscriber<? super ArrayList<String>> subscriber) {
                subscriber.onNext(s);
                subscriber.onCompleted();
            }
        });
    }

    //订阅者
    private void Subscriber(final TextView txt) {
        mySubscriber = new Subscriber<ArrayList<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(ArrayList<String> strings) {
                String s = "";
                String[] week = {"日", "一", "二", "三", "四", "五", "六"};
                if (strings.size() == 7) {
                    s = "每天";
                } else if (strings.size() == 1) {
                    s = s + strings.get(0);
                } else {
                    //排序
                    for (int j = 0; j < week.length; j++) {
                        for (int i = 0; i < strings.size(); i++) {
                            if (strings.get(i).equals(week[j])) {
                                if (j == week.length - 1) {
                                    s = s + strings.get(i);
                                } else {
                                    s = s + strings.get(i) + "、";
                                }
                            }
                        }
                    }

                }

                txt.setText(s);
            }
        };
        myObservable.subscribe(mySubscriber);
    }

    public void setBack(boolean b){
        if(!b) {
            rl_group.setBackgroundResource(R.color.white);
        }else {
            rl_group.setBackgroundResource(R.color.background);
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public static class ViewHolder {
        TextView icon_rubbish;
        TextView icon_confirm;
        TextView tx_zero, tx_one, tx_two, tx_three, tx_four, tx_five, tx_six;

    }
}
