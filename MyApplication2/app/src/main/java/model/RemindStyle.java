package model;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.Table;

/**
 * Created by q97531x on 2015/11/6.
 */
@Table(name = "RemindStyle")
public class RemindStyle {
    @Id(column="id")
    int id;
    int style;//标识振动，响铃和短信
    boolean vibration;
    boolean ring;
    boolean init;
    boolean Sms;
    String SmsName;
    String SmsPhone;
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getStyle(){
        return style;
    }
    public void setStyle(int style){
        this.style = style;
    }
    public boolean getVibration(){
        return vibration;
    }
    public void setVibration(boolean vibration){
        this.vibration = vibration;
    }
    public boolean init(){
        return init;
    }
    public void setInit(boolean init){
        this.init = init;
    }
    public boolean getRing(){
        return ring;
    }
    public void setRing(boolean ring){
        this.ring = ring;
    }
    public boolean getSms(){
        return Sms;
    }
    public void setSms(boolean  Sms){
        this.Sms = Sms;
    }
    public String getSmsName(){
        return SmsName;
    }
    public void setSmsName(String SmsName){
        this.SmsName = SmsName;
    }
    public String getSmsPhone(){
        return SmsPhone;
    }
    public void setSmsPhone(String SmsPhone){
        this.SmsPhone = SmsPhone;
    }
}
