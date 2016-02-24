package util;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.q97531x.myapplication.R;

/**
 * Created by q97531x on 2015/9/17.
 */
public class PercentDialog extends Dialog {
    private int percent;
    private OnCustomDialogListener customDialogListener;
    EditText percentEdit;
    ImageView percentConfirm,percentCancel;
    //定义回调事件
    public interface OnCustomDialogListener{
        public void back(int percent);
    }
    public PercentDialog(Context context,OnCustomDialogListener customDialogListener) {
        super(context);
        this.percent = percent;
        this.customDialogListener = customDialogListener;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.percent_dialog);
        //设置标题
        setTitle("百分比设置");
        percentEdit = (EditText)findViewById(R.id.percentEdit);
        percentConfirm = (ImageView)findViewById(R.id.percentConfirm);
        percentCancel = (ImageView)findViewById(R.id.percentCancel);
        percentConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                percentConfirm.setImageResource(R.drawable.ok2);
                if(percentEdit.getText().toString().equals("")){
//                    Toast.makeText(getOwnerActivity(),"请输入数值",Toast.LENGTH_SHORT).show();
                    customDialogListener.back(-2);
                    PercentDialog.this.dismiss();
                }else {customDialogListener.back(Integer.parseInt(percentEdit.getText().toString()));
                    PercentDialog.this.dismiss();
                }
            }
        });
        percentCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                percentCancel.setImageResource(R.drawable.cancel2);
                customDialogListener.back(-1);
                PercentDialog.this.dismiss();
            }
        });
    }


}
