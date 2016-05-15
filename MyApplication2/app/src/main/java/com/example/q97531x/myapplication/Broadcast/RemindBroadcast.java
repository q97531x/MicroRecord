package com.example.q97531x.myapplication.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;

import net.tsz.afinal.FinalDb;

import java.io.IOException;
import java.util.List;

import model.Contact;
import util.Utils;

/**
 * Created by XmacZone on 16/3/22.
 */
public class RemindBroadcast extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("getIntent","intent");
        final MediaPlayer mMediaPlayer;
        String action = intent.getAction();
        if(action.equals("Vibrator")){
//            Utils.toast("振动");
            Vibrator vibrator = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
            long [] pattern = {0, 200, 3000, 500, 2000, 1000};   // 停止 开启 停止 开启
            vibrator.vibrate(pattern, 4);           //重复两次上面的pattern 如果只想震动一次，index设为-1
            Utils.toast(context,"您预算超支啦,该好好规划一下支出啦");
        }else if(action.equals("Ring")){
            /*final MediaPlayer mMediaPlayer = MediaPlayer.create(context,RingtoneManager.getActualDefaultRingtoneUri(context,RingtoneManager.TYPE_RINGTONE));
            mMediaPlayer.setLooping(true);
            try {
                mMediaPlayer.prepare();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mMediaPlayer.start();
            new Handler().postDelayed(new Runnable(){
                public void run() {
                    //execute the task
                    mMediaPlayer.stop();
                }
            }, 3000);*/
            try {
                Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setDataSource(context, alert);
//final AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
                mMediaPlayer.setLooping(true);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        //execute the task
                        mMediaPlayer.stop();
                    }
                }, 3000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Utils.toast(context,"您预算超支啦,该好好规划一下支出啦");
        }else {
            FinalDb db = FinalDb.create(context);
            List<Contact> contacts = db.findAll(Contact.class);
            for(int i = 0;i<contacts.size();i++){
                sendSms(contacts.get(i).getPhoneNumber(),contacts.get(i).getName());
            }
//            sendSms(intent.getStringExtra("phoneNum"),intent.getStringExtra("phoneName"));
        }
    }
    //发送短信
    public void sendSms(String phoneNumber,String phoneName){
        //获取短信管理器
        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
        //拆分短信内容（手机短信长度限制）
        smsManager.sendTextMessage(phoneNumber, null,phoneName+"预算超支啦!" , null,null);
    }
}
