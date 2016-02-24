package Base;

import android.app.Application;
import android.util.Log;

/**
 * Created by 渡渡鸟 on 2016/1/7.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("init","init");
    }
}
