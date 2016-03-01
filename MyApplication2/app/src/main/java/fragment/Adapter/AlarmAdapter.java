package fragment.Adapter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.q97531x.myapplication.R;

import net.tsz.afinal.FinalDb;

import java.util.List;

import model.AlarmClock;

/**
 * Created by XmacZone on 16/3/1.
 */
public class AlarmAdapter extends BaseExpandableListAdapter{
    private Context context;
    private FinalDb db;
    private List<AlarmClock> alarmList;
    public AlarmAdapter(Context context,FinalDb db) {
        this.context = context;
        this.db = db;
        alarmList = db.findAll(AlarmClock.class);
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
        final Switch switch_btn = (Switch)convertView.findViewById(R.id.switch_btn);
        switch_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //注册闹铃
                    AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent("alarm");
                    PendingIntent sender = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);

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
