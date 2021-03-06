package model;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.Table;

/**
 * Created by q97531x on 2015/9/17.
 */
@Table(name = "Remind")
public class Remind {
    @Id(column="id")
    int id;
    int tag;//用于标识使用百分比或余额
    int percent;
    int style;//0标示振动,1标示响铃,2标示短信


    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getTag(){
        return tag;
    }
    public void setTag(int tag){
        this.tag = tag;
    }
    public int getPercent(){
        return percent;
    }
    public void setPercent(int percent){
        this.percent = percent;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }


}
