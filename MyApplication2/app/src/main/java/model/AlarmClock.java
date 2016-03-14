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
    int hourOfDay;
    int minute;
    int second;
    int millSecond;
//    String alarmTime;
//    闹铃频率
    ArrayList<Integer> alarmRate;
    public int getAlarmId(){
        return alarmId;
    }
    public void setAlarmId(int alarmId){
        this.alarmId = alarmId;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    public int getMillSecond() {
        return millSecond;
    }

    public void setMillSecond(int millSecond) {
        this.millSecond = millSecond;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public ArrayList<Integer> getAlarmRate(){
        return alarmRate;
    }
    public void setAlarmRate(ArrayList<Integer> alarmRate){
        this.alarmRate = alarmRate;
    }
}
