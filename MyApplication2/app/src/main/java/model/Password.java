package model;

import net.tsz.afinal.annotation.sqlite.Table;

/**
 * Created by q97531x on 2015/11/21.
 */
@Table(name = "User")
public class Password {
    int id;//id
    String password;
    Boolean hasPassword;//判断是否设置密码
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public Boolean getHasPassword(){
        return hasPassword;
    }
    public void setHasPassword(Boolean hasPassword){
        this.hasPassword = hasPassword;
    }
}
