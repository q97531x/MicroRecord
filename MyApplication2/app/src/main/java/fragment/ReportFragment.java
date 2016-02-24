package fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.q97531x.myapplication.DateDialogActivity;
import com.example.q97531x.myapplication.FrameActivity;
import com.example.q97531x.myapplication.R;
//import com.example.q97531x.myapplication.RecordActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Income;
import model.Outcome;

/**
 * Created by Administrator on 2015/7/29.
 */
public class ReportFragment extends Fragment {
    FinalDb db;
    Toolbar toolbar;
    TextView reOutcomeBtn,reIncomeBtn,reportDate;
    private PieChart mChart;
    ListView reList;
    ArrayList outcomeType,incomeType;
    ArrayList<Integer> outKey,inKey;
    int year,month,day;
    double[] sum = new double[20];
    Boolean flag = true;
    Boolean mark = false;
    Dialog mdialog;
    Calendar calendar;
    String date;
    String outType[] = {
            "餐饮","购物","交通","娱乐","居家","医药","进修","美妆","旅游","其他"};
    String inType[] = {
            "生活费","兼职","奖金","分红","报销","工资","补贴","其他"
    };
    int l[] = {0,0,0,0,0,0,0,0,0,0};
    int m[] = {0,0,0,0,0,0,0,0,0,0};
    int outtag = 0,intag = 0;
    public ReportFragment(){
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report,container,false);
        db = FinalDb.create(getActivity());
        reportDate = (TextView)view.findViewById(R.id.reportDate);
        reOutcomeBtn = (TextView)view.findViewById(R.id.reOutcomeBtn);
        reIncomeBtn = (TextView)view.findViewById(R.id.reIncomeBtn);

//        reList = (ListView)view.findViewById(R.id.reList);
        mChart = (PieChart) view.findViewById(R.id.pieChart);
        mChart.setHoleRadius(60f);

        mChart.setDescription("");

        mChart.setDrawCenterText(true);

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);
        mChart.setRotationAngle(15);
        mChart.setRotationEnabled(true);
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        date = year+"年"+(month+1)+"月";
        reportDate.setText(date);
        //setData(type.size(),list);
        //for(int j = 0;j<M)
        //setData(MpaOutcome.size(),list);
        /*Bundle bk = getArguments();
        date = bk.getString("redate");
        Log.v("test",date);*/
        /*if(flag == 0){
            date = year+"�?+(month+1)+"�?;
        }else if(flag == 1){
            Bundle bd = getArguments();
            date = bd.getString("date");
            Log.v("callback",date);
        }*/
        //date = year+"�?+(month+1)+"�?;
        // App Logo
        invalidOutcome();
        reOutcomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                //mChart.invalidate();
                reOutcomeBtn.setBackgroundResource(R.drawable.reportout_bg2);
                reIncomeBtn.setBackgroundResource(R.drawable.reportout_bg1);
                Log.e("flag",""+flag);
                invalidOutcome();
            }
        });
        reIncomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
                reOutcomeBtn.setBackgroundResource(R.drawable.reportout_bg1);
                reIncomeBtn.setBackgroundResource(R.drawable.reportout_bg2);
                Log.v("tt", "click" + flag);
                //mChart.invalidate();
                Log.e("flag",""+flag);
                invalidIncome();
            }
        });
        return view;
}
    public void setData (int count,List list){
        ArrayList<Entry> alist = new ArrayList<Entry>();
        ArrayList<String> xlist = new ArrayList<String>();
        PieDataSet dataset = null;
        if(list.size()>0){
        for(int i = 0;i<count;i++){
            Log.e("data","count = "+count);
            Log.e("data","size = "+list.size());
            alist.add(new Entry(Float.parseFloat(list.get(i).toString()),i));
            Log.e("data","alist = "+alist.size());
            if(flag == true){
                for(int j = 0;j<count;j++){
                    xlist.add((outcomeType.get(j)).toString());
                    //Log.e("dota","typename = "+(outcomeType.get(j)).toString());
//                    Log.e("dota","xlistname = "+xlist.get(j));
                    dataset = new PieDataSet(alist, "支出");
                }
            }else if(flag == false){
                for(int j = 0;j<count;j++){
                    xlist.add((incomeType.get(j)).toString());
                    // Log.e("dota","typename = "+(type.get(j)).toString());
                    //Log.e("dota","xlistname = "+xlist.get(j));
                    dataset = new PieDataSet(alist, "收入");
                }

            }

            ArrayList<Integer> colors = new ArrayList<Integer>();
            for (int c : ColorTemplate.VORDIPLOM_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.JOYFUL_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.COLORFUL_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.LIBERTY_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.PASTEL_COLORS)
                colors.add(c);

            colors.add(ColorTemplate.getHoloBlue());

            dataset.setColors(colors);
            PieData data = new PieData(xlist,dataset);
            mChart.setData(data);
            mChart.invalidate();
            //list.clear();
            Log.v("ss",""+list.size());
        }}else{
            for(int i = 0;i<count;i++) {
                alist = new ArrayList<Entry>();
                ArrayList<String> mlist = new ArrayList<String>();
                mlist.add("");
                alist.add(new Entry(0, i));
                dataset = new PieDataSet(alist, "类型");
                dataset.setColor(Color.BLACK);
                PieData data = new PieData(mlist,dataset);
                mChart.setData(data);
                mChart.invalidate();
            }
        }
    }
    public void invalidOutcome(){
            List<Outcome> MpaOutcome = db.findAllByWhere(Outcome.class, " reOutcomeTime=\"" + reportDate.getText().toString() + "\"");
            ArrayList list = new ArrayList();
            outKey = new ArrayList<Integer>();
            outcomeType = new ArrayList();
            Log.v("math", "create");
            Log.v("math", "" + MpaOutcome.size());
            if (MpaOutcome.size() > 0) {
                for (int i = 0; i < MpaOutcome.size(); i++) {
                    Log.v("math", "start");
                    if (MpaOutcome.get(i).getOutcomeType().equals("餐饮")) {
                        if(l[0]<1){
                            outtag++;
                            outKey.add(0);
                        }
                        l[0]++;
                        //Log.v("math", outcomeType.get(0).toString());
                        sum[0] = sum[0] + MpaOutcome.get(i).getOutcomeAmount();
                        //list.set(0,sum[0]);
                        //sum[0]=0;
                    } else if (MpaOutcome.get(i).getOutcomeType().equals("购物")) {
                        if(l[1]<1){
                            outtag++;
                            outKey.add(1);
                        }
                        l[1]++;
                        sum[1] = sum[1] + MpaOutcome.get(i).getOutcomeAmount();
                    } else if(MpaOutcome.get(i).getOutcomeType().equals("交通")){
                        if(l[2]<1){
                            outtag++;
                            outKey.add(2);
                        }
                        l[2]++;
                        sum[2] =  sum[2] + MpaOutcome.get(i).getOutcomeAmount();
                    } else if(MpaOutcome.get(i).getOutcomeType().equals("娱乐")){
                        if(l[3]<1){
                            outtag++;
                            outKey.add(3);
                        }
                        l[3]++;
                        sum[3] = sum[3] + MpaOutcome.get(i).getOutcomeAmount();
                    } else if(MpaOutcome.get(i).getOutcomeType().equals("居家")){
                        if(l[4]<1){
                            outtag++;
                            outKey.add(4);
                        }
                        l[4]++;
                        sum[4] = sum[4] + MpaOutcome.get(i).getOutcomeAmount();
                    }else if(MpaOutcome.get(i).getOutcomeType().equals("医药")){
                        if(l[5]<1){
                            outKey.add(5);
                            outtag++;
                        }
                        l[5]++;
                        sum[5] = sum[5] + MpaOutcome.get(i).getOutcomeAmount();
                    }else if (MpaOutcome.get(i).getOutcomeType().equals("进修")){
                        if(l[6]<1){
                            outKey.add(6);
                            outtag++;
                        }
                        l[6]++;
                        sum[6] = sum[6] + MpaOutcome.get(i).getOutcomeAmount();
                    }else if (MpaOutcome.get(i).getOutcomeType().equals("美妆")){
                        if(l[7]<1){
                            outKey.add(7);
                            outtag++;
                        }
                        l[7]++;
                        sum[7] = sum[7] + MpaOutcome.get(i).getOutcomeAmount();
                    }else if (MpaOutcome.get(i).getOutcomeType().equals("旅游")){
                        Log.e("boolean",""+MpaOutcome.get(i).getOutcomeType().equals("投资"));
                        if(l[8]<1){
                            outKey.add(8);
                            outtag++;
                        }
                        l[8]++;
                        sum[8] = sum[8] + MpaOutcome.get(i).getOutcomeAmount();
                    }else if (MpaOutcome.get(i).getOutcomeType().equals("其他")){
                        if(l[9]<1){
                            outKey.add(9);
                            outtag++;
                        }
                        l[9]++;
                        sum[9] = sum[9] + MpaOutcome.get(i).getOutcomeAmount();
                    }
                }

//                l = 0;
//                m = 0;
                Log.e("count","count = "+outtag);

                for(int k = 0;k<l.length;k++){
                    l[k] = 0;
                }
                //控制sum[l]的l�?
                //Log.e("show",type.size()+type.get(0).toString()+type.get(1).toString());
                for (int l = 0;l < outtag;l++){
                   // Log.v("tt", "" + intag);
                    if(sum[outKey.get(l)]!=0.0) {
                        list.add(sum[outKey.get(l)]);
                        Log.e("aa",""+sum[outKey.get(l)]);
                        outcomeType.add(outType[outKey.get(l)]);
//                        Log.v("math", "" + incomeType.size());
                    }
                }
                for(int j = 0;j<sum.length;j++){
                    sum[j] = 0;
                }
                outKey.clear();
                Log.v("list", "" + list.size());
                setData(list.size(), list);
                Log.v("count", "" + list.size());
                outtag = 0;
            } else {
                setData(1, list);
                Toast.makeText(getActivity(), "该月暂无支出记录", Toast.LENGTH_LONG).show();
            }
    }
    public void invalidIncome(){
        List<Income> MpaIncome = db.findAllByWhere(Income.class, " reIncomeTime=\"" + reportDate.getText().toString() + "\"");
        ArrayList list1 = new ArrayList();
        incomeType = new ArrayList();
        inKey = new ArrayList<Integer>();
        Log.v("math", "create");
        Log.v("math", "" + MpaIncome.size());
        if (MpaIncome.size() > 0) {
            for (int i = 0; i < MpaIncome.size(); i++) {
                if (MpaIncome.get(i).getIncomeType().equals("生活费")) {
                    sum[0] = sum[0] + MpaIncome.get(i).getIncomeAmount();
                    if(m[0]<1){
//                        incomeType.add("食物");
                        inKey.add(0);
                        intag ++;
                    }
                    m[0]++;
                } else if (MpaIncome.get(i).getIncomeType().equals("兼职")) {
                    if(m[1]<1){
//                        incomeType.add("购物");
                        inKey.add(1);
                        intag++;
                    }
                    m[1]++;
                    sum[1] = sum[1] + MpaIncome.get(i).getIncomeAmount();
                }else if(MpaIncome.get(i).getIncomeType().equals("奖金")){
                    sum[2] = sum[2] + MpaIncome.get(i).getIncomeAmount();
                    if(m[2]<1){
                        inKey.add(2);
                        intag++;
                    }
                    m[2]++;
                }else if(MpaIncome.get(i).getIncomeType().equals("分红")){
                    sum[3] = sum[3] + MpaIncome.get(i).getIncomeAmount();
                    if(m[3]<1){
                        inKey.add(3);
                        intag++;
                    }
                    m[3]++;
                }else if (MpaIncome.get(i).getIncomeType().equals("报销")){
                    sum[4] = sum[4] + MpaIncome.get(i).getIncomeAmount();
                    if(m[4]<1){
                        intag++;
                        inKey.add(4);
                    }
                    m[4]++;
                }else if(MpaIncome.get(i).getIncomeType().equals("工资")){
                    sum[5] = sum[5] + MpaIncome.get(i).getIncomeAmount();
                    if(m[5]<1){
                        intag++;
                        inKey.add(5);
                    }
                    m[5]++;
                }else if(MpaIncome.get(i).getIncomeType().equals("补贴")){
                    sum[6] = sum[6] + MpaIncome.get(i).getIncomeAmount();
                    if(m[6]<1){
                        intag++;
                        inKey.add(6);
                    }
                    m[6]++;
                }else if(MpaIncome.get(i).getIncomeType().equals("其他")){
                    sum[7] = sum[7] + MpaIncome.get(i).getIncomeAmount();
                    if(m[7]<1){
                        intag++;
                        inKey.add(7);
                    }
                    m[7]++;
                }

            }
            for(int k = 0;k<m.length;k++){
                m[k] = 0;
            }
            //Log.e("show",type.size()+type.get(0).toString()+type.get(1).toString());
            for (int m = 0;m <intag;m++){
                Log.v("tt", "" + intag);
                if(sum[inKey.get(m)]!=0.0) {
                    list1.add(sum[inKey.get(m)]);
                    incomeType.add(inType[inKey.get(m)]);
                    Log.v("math", "" + incomeType.size());
                }
            }
            Log.e("show",""+list1.size());
            intag = 0;

//            incomeType.set(0,"食物");
//            incomeType.set(1,"购物");
            /*for (int k = 0;k<type.size();k++){
                Log.e("dota","type a = "+ type.get(k).toString());
            }*/

            setData(list1.size(), list1);
            //Log.v("data",""+list1.size());
            for(int j = 0;j<sum.length;j++){
                sum[j] = 0;
            }
            //Log.v("count", "" + list1.size());
        }else {
            setData(1, list1);
            //Log.v("tag","无收支记录");
            Toast.makeText(getActivity(), "该月暂无收入记录",Toast.LENGTH_LONG).show();
        }
    }
    }
