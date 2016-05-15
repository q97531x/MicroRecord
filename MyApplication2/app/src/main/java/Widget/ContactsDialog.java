package Widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.q97531x.myapplication.R;

import java.util.zip.Inflater;

import util.Utils;

/**
 * Created by XmacZone on 16/3/21.
 */
public class ContactsDialog extends Dialog implements View.OnClickListener{
    private LayoutInflater inflater;
    private String phone = "",phone_name = "";
    private Context context;
    private EditText phone_num,name;
    private TextView confirm,cancel;
    private boolean isCancel = false;
    public ContactsDialog(Context context) {
        super(context);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = LayoutInflater.from(context);
        initView();
    }

    private void initView() {
        View view = inflater.inflate(R.layout.dialog_contacts,null);
        setContentView(view);
        phone_num = (EditText)view.findViewById(R.id.phone_num);
        name = (EditText)view.findViewById(R.id.name);
        confirm = (TextView)view.findViewById(R.id.confirm);
        cancel = (TextView)view.findViewById(R.id.cancel);
        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用

        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.8
        dialogWindow.setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirm:

                if(!phone_num.getText().toString().equals("")||!name.getText().toString().equals("")){
                    phone = phone_num.getText().toString();
                    phone_name = name.getText().toString();
                    isCancel = false;
                    dismiss();
                }else{
                    Toast.makeText(context,"请输入完整信息",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cancel:
                isCancel = true;
                dismiss();
                break;
        }
    }
    public String getNumber(){
        return phone;
    }

    public String getPhone_name() {
        return phone_name;
    }
    public boolean isCancel(){
        return isCancel;
    }
}
