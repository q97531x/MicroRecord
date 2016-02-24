package model.Bmob;

import cn.bmob.v3.BmobObject;

/**
 * Created by 渡渡鸟 on 2015/12/27.
 */
public class Person extends BmobObject{
    private String userName;    //用户名
    private String password;    //密码
    private String email;       //邮箱
    private String phoneNumber; //手机号码
    //getter&&setter
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getpassword(){
        return password;
    }
    public void setpassword(String password){
        this.password = password;
    }
    public String getemail(){
        return email;
    }
    public void setemail(String email){
        this.email = email;
    }
    public String getphoneNumber(){
        return phoneNumber;
    }
    public void setphoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
}
