package util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.q97531x.myapplication.R;

/**
 * Created by q97531x on 2015/9/18.
 */
public class SmsDialog extends Dialog {
    String SmsName,SmsNum;
    EditText SmsEdit,SmsNumEdit;
    ImageView SmsConfirm,SmsCancel;
    private OnCustomDialogListener customDialogListener;
    //private OnCustomDialogListener1 customDialogListener1;
    //定义回调事件
    public interface OnCustomDialogListener{
        public void backName(String SmsName,String SmsNum);
        //public void backNum(String SmsNum);
    }
    /*public interface OnCustomDialogListener1{
        public void backNum(String SmsNum);
    }*/
    public SmsDialog(Context context,OnCustomDialogListener customDialogListener) {
        super(context);
        this.SmsName = SmsName;
        this.SmsNum = SmsNum;
        this.customDialogListener = customDialogListener;
        //this.customDialogListener1 = customDialogListener1;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_dialog);
        //设置标题
        setTitle("提醒设置");
        SmsEdit = (EditText)findViewById(R.id.SmsName);
        SmsNumEdit = (EditText)findViewById(R.id.SmsNum);
        SmsConfirm = (ImageView)findViewById(R.id.SmsConfirm);
        SmsCancel = (ImageView)findViewById(R.id.SmsCancel);
        SmsConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsConfirm.setImageResource(R.drawable.ok2);
                //控制EditTExt的值符合要求
                customDialogListener.backName(SmsEdit.getText().toString(), SmsNumEdit.getText().toString());
                //customDialogListener.backNum(SmsNumEdit.getText().toString());
                SmsDialog.this.dismiss();
            }
        });
        SmsCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsCancel.setImageResource(R.drawable.cancel2);
                customDialogListener.backName("null", "null");
                //customDialogListener.backNum("null");
                SmsDialog.this.dismiss();
            }
        });
    }
}
