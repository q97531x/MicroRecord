package util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.q97531x.myapplication.R;

/**
 * Created by q97531x on 2015/9/17.
 */
public class BalanceDialog extends Dialog {
    private int balance;
    private OnCustomDialogListener customDialogListener;
    EditText balanceEdit;
    ImageView balanceConfirm,balanceCancel;
    //定义回调事件
    public interface OnCustomDialogListener{
        public void back(int balance);
    }
    public BalanceDialog(Context context,OnCustomDialogListener customDialogListener) {
        super(context);
        this.balance = balance;
        this.customDialogListener = customDialogListener;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance_dialog);
        //设置标题
        setTitle("余额设置");
        balanceEdit = (EditText)findViewById(R.id.balanceEdit);
        balanceConfirm = (ImageView)findViewById(R.id.balanceConfirm);
        balanceCancel = (ImageView)findViewById(R.id.balanceCancel);
        balanceConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                balanceConfirm.setImageResource(R.drawable.ok2);
                if(balanceEdit.getText().toString().equals("")){
                    customDialogListener.back(-2);
                }else {
                    customDialogListener.back(Integer.parseInt(balanceEdit.getText().toString()));
                }
                BalanceDialog.this.dismiss();
            }
        });
        //-1标志点击了cancel按钮
        balanceCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                balanceCancel.setImageResource(R.drawable.cancel2);
                customDialogListener.back(-1);
                BalanceDialog.this.dismiss();
            }
        });
    }
}
