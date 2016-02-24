package com.example.q97531x.myapplication.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by 渡渡鸟 on 2016/1/22.
 */
public class User extends BaseObservable{
    public  String name;
    public  boolean show;
    public User(String name,boolean isShow) {
        this.name = name;
        this.show = isShow;
    }
    @Bindable
    public boolean Show(){
        return show;
    }
    public void setIsShow(boolean Show){
        this.show = Show;
        notifyAll();
    }
    public void setName(String name){
        this.name = name;
        notifyAll();
    }
    @Bindable
    public String getName(){
        return name;
    }
}
