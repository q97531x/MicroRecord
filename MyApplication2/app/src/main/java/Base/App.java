package Base;

import android.app.Application;

import net.tsz.afinal.FinalDb;

/**
 * Created by 渡渡鸟 on 2015/12/29.
 */
public class App extends Application{
    private static int user = 0;
    public static int getUser(){
        return user;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
