package fragment.Broadcast;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Vibrator;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.example.q97531x.myapplication.FrameActivity;
import com.example.q97531x.myapplication.R;

import Base.App;

/**
 * Created by XmacZone on 16/3/4.
 * 闹铃广播接收器
 */
public class AlarmReceiver extends BroadcastReceiver{
//    private Vibrator vibrator;
    public final static String ACTION_BTN = "btn";
    private final static String PAUSE = "pause";
    private final static String CANCEL = "cancel";
    private static boolean alarm = true;
    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context, "闹铃响了", Toast.LENGTH_SHORT).show();
        String action = intent.getAction();
        if(action.equals(PAUSE)){
            //闹钟暂停5分钟后再响
            Log.e("pause","pause");
        }else {
            ring(context);
            Notification.Builder builder = new Notification.Builder(context);
            Intent it = new Intent(context, FrameActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, it, 0);
            builder.setContentIntent(pendingIntent);
            builder.setSmallIcon(R.drawable.icon_main);
            builder.setAutoCancel(true);
            builder.setContentTitle("闹钟");
            builder.setFullScreenIntent(pendingIntent, true);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.alarm_notification);
            /*remoteViews.setTextViewText(R.id.text, "闹钟");
            Intent pause = new Intent(context,NotificationBroadcast.class);
//            pause.setAction(PAUSE);
            PendingIntent pendingPause = PendingIntent.getBroadcast(context, 1, pause, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.pause, pendingPause);*/
            Notification notification = builder.build();
            notification.contentView = remoteViews;
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(2, notification);
            Log.e("alarm", "接收到了");
        }
    }
    public void ring(Context context){
        Vibrator vibrator = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        long [] pattern = {0, 200, 3000, 500, 2000, 1000};   // 停止 开启 停止 开启
        vibrator.vibrate(pattern, 4);           //重复两次上面的pattern 如果只想震动一次，index设为-1
    }
    private void intiReceiver() {
//        mReceiver = new NotificationBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_BTN);
        App.context.registerReceiver(this, intentFilter);
    }
}
