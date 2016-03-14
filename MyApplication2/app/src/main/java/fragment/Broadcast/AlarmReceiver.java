package fragment.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by XmacZone on 16/3/4.
 * 闹铃广播接收器
 */
public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"闹铃响了",Toast.LENGTH_LONG).show();
    }
}
