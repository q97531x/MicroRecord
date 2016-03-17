package fragment.Broadcast;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by XmacZone on 16/3/4.
 * 闹铃广播接收器
 */
public class AlarmReceiver extends BroadcastReceiver{
//    private Vibrator vibrator;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "闹铃响了", Toast.LENGTH_SHORT).show();
        Vibrator vibrator = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        long [] pattern = {100,400,100,400};   // 停止 开启 停止 开启
        vibrator.vibrate(pattern, 2);           //重复两次上面的pattern 如果只想震动一次，index设为-1
        Notification.Builder builder = new Notification.Builder(context);

        Log.e("alarm","接收到了");
    }

}
