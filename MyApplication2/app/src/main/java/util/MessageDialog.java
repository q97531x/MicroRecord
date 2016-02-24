package util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.q97531x.myapplication.R;

/**
 * Created by q97531x on 2015/11/14.
 */
public class MessageDialog extends Dialog {
    private Context mContext;
    private static int mTheme = R.style.MyDialog;
    EditText sumName,sumNum;
    ImageView sumConfirm,sumCancel;
    //构造函数
    /*public MessaageDialog(Context context,int theme){
        super(context,theme);
        this.mContext = context;
    }*/
    public MessageDialog(Context context){
        super(context, mTheme);
        this.mContext = context;
    }
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_dialog);
        sumName = (EditText)findViewById(R.id.SmsName);
        sumNum = (EditText)findViewById(R.id.SmsNum);
        sumConfirm = (ImageView)findViewById(R.id.SmsConfirm);
        sumCancel = (ImageView)findViewById(R.id.SmsCancel);

    }
}
