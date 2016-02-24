package model;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.Table;

import java.security.PublicKey;

/**
 * Created by q97531x on 2015/8/14.
 */
@Table(name = "Budget")
public class Budget {
    @Id(column="typeId")
    int typeId;
    String budgetDate;
    String budgetType;
    double budgetAccount;
    public int getTypeId(){
        return typeId;
    }
    public void setTypeId(int typeId){
        this.typeId = typeId;
    }
    public String getBudgetDate(){
        return budgetDate;
    }
    public void setBudgetDate(String budgetDate){
        this.budgetDate = budgetDate;
    }
    public String getBudgetType(){
        return budgetType;
    }
    public void setBudgetType(String budgetType){
        this.budgetType = budgetType;
    }
    public double getBudgetAccount(){
        return budgetAccount;
    }
    public void setBudgetAccount(double budgetAccount){
        this.budgetAccount = budgetAccount;
    }
}
