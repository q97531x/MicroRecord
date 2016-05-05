package model;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.Table;

/**
 * Created by Weeboos on 2015/7/27.
 */
@Table(name = "Income")
public class Income {
    @Id(column="incomeId")
         //用户id
    int incomeId; 		//主键，收入id
    String incomeType;      //收入类型
    String incomeTime;		 //收入时间
    String incomeMonth;//月
    String incomeWeek;//周
    String reIncomeTime;
    String incomeHour;
    float incomeAmount;		//收入金额
    String incomeNote;      //收入备注

    public int getIncomeId(){
        return incomeId;
    }
    public void setIncomeId(int incomeId){
        this.incomeId = incomeId;
    }
    public String getIncomeType(){
        return incomeType;
    }
    public void  setIncomeType(String incomeType){
        this.incomeType = incomeType;
    }
    public String getIncomeTime() {
        return incomeTime;
    }
    public void setIncomeTime(String incomeTime) {
        this.incomeTime = incomeTime;
    }
    public String getReIncomeTime() {
        return reIncomeTime;
    }
    public void setReIncomeTime(String reIncomeTime) {
        this.reIncomeTime = reIncomeTime;
    }
    public String getIncomeHour(){
        return incomeHour;
    }
    public void setIncomeHour(String incomeHour){
        this.incomeHour = incomeHour;
    }
    public float getIncomeAmount() {
        return incomeAmount;
    }
    public void setIncomeAmount(float incomeAmount) {
        this.incomeAmount = incomeAmount;
    }
    public String getIncomeNote() {
        return incomeNote;
    }
    public void setIncomeNote(String incomeNote) {
        this.incomeNote = incomeNote;
    }

    public String getIncomeMonth() {
        return incomeMonth;
    }

    public void setIncomeMonth(String incomeMonth) {
        this.incomeMonth = incomeMonth;
    }

    public String getIncomeWeek() {
        return incomeWeek;
    }

    public void setIncomeWeek(String incomeWeek) {
        this.incomeWeek = incomeWeek;
    }
}