package model;


import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by 渡渡鸟 on 2016/1/7.
 */
@Table(name = "Type")
public class Type {
    int id;
    int resId;
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getresId(){
        return resId;
    }
    public void setresId(int resId){
        this.resId = resId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
