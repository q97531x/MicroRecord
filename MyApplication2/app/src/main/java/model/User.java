package model;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.Table;

/**
 * Created by Weeboos on 2015/7/27.
 */
@Table(name = "User")
public class User {
    @Id(column="userId")
    private int userId;
    String userName;		//用户名
    String userSex;		//用户性别
    int userType;		//用户类型
    String userEmail;		//用户邮箱
    String registerTime;		//注册时间
    String lastLoginTime;		//最后登录时间
    String Password;		//密码
    String userState;		//籍贯
    boolean hasPassword;

    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getSex() {
        return userSex;
    }


    public void setSex(String sex) {
        this.userSex = sex;
    }


    public int getUserType() {
        return userType;
    }


    public void setUserType(int userType) {
        this.userType = userType;
    }


    public String getEmail() {
        return userEmail;
    }


    public void setEmail(String email) {
        this.userEmail = email;
    }


    public String getRegisterTime() {
        return registerTime;
    }


    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }


    public String getLastLoginTime() {
        return lastLoginTime;
    }


    public void setLastLoginTime(String LastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }


    public String getPassword() {
        return Password;
    }


    public void setPassword(String Password) {
        this.Password = Password;
    }


    public String getUserState() {
        return userState;
    }


    public void setUserState(String userState) {
        this.userState = userState;
    }
    public boolean getHasPassword(){
        return hasPassword;
    }
    public void setHasPassword(boolean hasPassword){
        this.hasPassword = hasPassword;
    }
}
