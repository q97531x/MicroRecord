package util;

import android.content.Context;
import android.os.Vibrator;
import android.text.TextPaint;
import android.widget.TextView;
import android.widget.Toast;

import Base.App;

/**
 * Created by XmacZone on 16/3/19.
 */
public class Utils {
    private static Vibrator vibrator;
    public static boolean notification = false;
    //Toast显示
    public static void toast(String toast){
        Toast.makeText(App.context,toast,Toast.LENGTH_SHORT).show();
    }
    //带context的Toast
    public static void toast(Context context,String toast){
        Toast.makeText(context,toast,Toast.LENGTH_SHORT).show();
    }
    //加粗中文字体
    public static void boldText(TextView textView){
        TextPaint tp = textView.getPaint();
        tp.setFakeBoldText(true);
    }
    //振动
    public static void ring(Context context){
        Vibrator vibrator = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        long [] pattern = {0, 200, 3000, 500, 2000, 1000};   // 停止 开启 停止 开启
        vibrator.vibrate(pattern, 4);           //重复两次上面的pattern 如果只想震动一次，index设为-1
    }
    public static void stopVibrator(Context context){
        vibrator = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.cancel();
    }
}
