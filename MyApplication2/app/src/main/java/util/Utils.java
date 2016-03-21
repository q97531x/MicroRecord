package util;

import android.text.TextPaint;
import android.widget.TextView;
import android.widget.Toast;

import Base.App;

/**
 * Created by XmacZone on 16/3/19.
 */
public class Utils {
    //Toast显示
    public static void toast(String toast){
        Toast.makeText(App.context,toast,Toast.LENGTH_SHORT);
    }
    //加粗中文字体
    public static void boldText(TextView textView){
        TextPaint tp = textView.getPaint();
        tp.setFakeBoldText(true);
    }
}
