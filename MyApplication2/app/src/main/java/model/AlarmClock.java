package model;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.Table;

import java.util.ArrayList;

/**
 * Created by XmacZone on 16/3/1.
 */
@Table(name = "AlarmClock")
public class AlarmClock {
    @Id(column="alarmId")
    int alarmId;
    //闹铃日期
    String alarmTime;
    //闹铃频率
    ArrayList<Integer> alarmRate;
    public int getAlarmId(){
        return alarmId;
    }
    public void setAlarmId(int alarmId){
        this.alarmId = alarmId;
    }
    public String getAlarmTime(){
        return alarmTime;
    }
    public void setAlarmTime(String alarmTime){
        this.alarmTime = alarmTime;
    }
    public ArrayList<Integer> getAlarmRate(){
        return alarmRate;
    }
    public void setAlarmRate(ArrayList<Integer> alarmRate){
        this.alarmRate = alarmRate;
    }
}
