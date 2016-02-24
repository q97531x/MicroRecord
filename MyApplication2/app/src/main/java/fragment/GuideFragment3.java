package fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.q97531x.myapplication.FrameActivity;
import com.example.q97531x.myapplication.R;

import java.util.Calendar;

/**
 * Created by Administrator on 2015/7/27.
 */
public class GuideFragment3 extends Fragment{
    private Context ctx;
    String date;
    int year,month;
    ImageView btn;
    /*public GuideFragment1(){
        super();
    }*/
    public GuideFragment3(Context ctx){
        super();
        this.ctx = ctx;
    }
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view =  View.inflate(ctx, R.layout.fragment_guide3, null);
        btn = (ImageView)view.findViewById(R.id.btn);
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        date = year+"年"+(month+1)+"月";
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, FrameActivity.class);
                /*Bundle bn = new Bundle();
                bn.putInt("msg",0);
                bn.putString("date",date);

                intent.putExtra("bt", bn);*/
                ctx.startActivity(intent);
                btn.setImageResource(R.drawable.ins2);
                getActivity().finish();
            }
        });
        return view;
    }
}
