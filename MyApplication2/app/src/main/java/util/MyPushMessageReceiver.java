package util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 渡渡鸟 on 2015/12/29.
 */
public class MyPushMessageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("msg")){
            Log.e("bmob", "客户端收到推送内容：" + intent.getStringExtra("msg"));
//            Toast.makeText(,intent.getStringExtra("msg"),Toast.LENGTH_SHORT).show();
        }
    }
}
