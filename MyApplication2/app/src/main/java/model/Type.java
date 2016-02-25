package model;

import net.tsz.afinal.annotation.sqlite.Table;

/**
 * Created by XmacZone on 16/2/25.
 */
@Table(name = "Type")
public class Type {
    int id;
    String outcomeTypeName;
    int outcomeTypeIcon;
    String incomeTypeName;
    int incomeTypeIcon;
    public int getId(){
        return id;
    }
    public void setId(){
        this.id = id;
    }
    public String getOutcomeTypeName(){
        return outcomeTypeName;
    }
    public void setOutcomeTypeName(){
        this.outcomeTypeName = outcomeTypeName;
    }
    public int getOutcomeTypeIcon(){
        return outcomeTypeIcon;
    }
    public void setOutcomeTypeIcon(int outcomeTypeIcon){
        this.outcomeTypeIcon = outcomeTypeIcon;
    }
    public String getIncomeTypeName(){
        return incomeTypeName;
    }
    public void setIncomeTypeName(){
        this.incomeTypeName = incomeTypeName;
    }
    public int getIncomeTypeIcon(){
        return incomeTypeIcon;
    }
    public void setIncomeTypeIcon(int incomeTypeIcon){
        this.incomeTypeIcon = incomeTypeIcon;
    }
}
