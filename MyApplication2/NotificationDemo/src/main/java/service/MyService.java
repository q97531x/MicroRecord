package service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

/**
 * Created by q97531x on 2015/11/22.
 */
public class MyService extends Service {
    String ns;
    NotificationManager mNotificationManager;
    Notification notification = new Notification();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ns = Context.NOTIFICATION_SERVICE;
        mNotificationManager = (NotificationManager)getSystemService(ns);
        RemoteViews notificationView = new RemoteViews(getPackageName(), R.layout.notification_layout);
        notification.contentView = notificationView;
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        notification.setLatestEventInfo(this, "title", "main",
                pi);
        mNotificationManager.notify(1, notification);
        Toast.makeText(this,"service",Toast.LENGTH_SHORT).show();

    }
}
