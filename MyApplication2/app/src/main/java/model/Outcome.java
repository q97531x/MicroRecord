package model;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.Table;

/**
 * Created by Weeboos on 2015/7/27.
 */
@Table(name = "Outcome")
public class Outcome {
    @Id(column="outcomeId")
    int userId;
    //int outcomeId;
    String outcomeType;
    String outcomeTime;
    String reOutcomeTime;
    String outcomeHour;//记录的时间
    double outcomeAmount;
    String outcomeNote;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
//    public int getOutcomeId(){
//        return outcomeId;
//    }
//    public void setOutcomeId(int outcomeId){
//        this.outcomeId = outcomeId;
//    }
    public String getOutcomeType(){
        return outcomeType;
    }
    public void setOutcomeType(String outcomeType){
        this.outcomeType = outcomeType;
    }
    public String getOutcomeTime() {
        return outcomeTime;
    }
    public void setOutcomeTime(String outcomeTime) {
        this.outcomeTime = outcomeTime;
    }
    public String getReOutcomeTime() {
        return reOutcomeTime;
    }
    public void setReOutcomeTime(String reOutcomeTime) {
        this.reOutcomeTime = reOutcomeTime;
    }
    public String getOutcomeHour(){
        return outcomeHour;
    }
    public void setOutcomeHour(String outcomeHour){
        this.outcomeHour = outcomeHour;
    }
    public double getOutcomeAmount() {
        return outcomeAmount;
    }
    public void setOutcomeAmount(double outcomeAmount) {
        this.outcomeAmount = outcomeAmount;
    }
    public String getOutcomeNote() {
        return outcomeNote;
    }
    public void setOutcomeNote(String outcomeNote) {
        this.outcomeNote = outcomeNote;
    }

}
