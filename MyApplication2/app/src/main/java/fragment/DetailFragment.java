package fragment;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.q97531x.myapplication.R;
//import com.example.q97531x.myapplication.RecordActivity;
import com.example.q97531x.myapplication.WriteActivity;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Widget.SlideListView;
import model.Income;
import model.Outcome;

/**
 * Created by Administrator on 2015/7/28.
 */
public class DetailFragment extends Fragment implements View.OnClickListener{
    //Context ctx;
    //final Toolbar toolbar = null;
    int year,month,day,flag = 0;
    LinearLayout outcomeLayout,incomeLayout;
    ImageView detailOutcome,detailIncome;
    private TextView outcomeText,incomeText,write;
    SlideListView lv;
    FinalDb db;
    Toolbar toolbar;
    private String type = "outcome";
    private String date;
    public DetailFragment(){
        super();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = View.inflate(ctx,R.layout.fragment_detail,null);
        Typeface iconfont = Typeface.createFromAsset(getActivity().getAssets(), "iconfont.ttf");
        View view = inflater.inflate(R.layout.fragment_detail,container,false);
        db = FinalDb.create(getActivity());
        lv = (SlideListView)view.findViewById(R.id.list);
        outcomeLayout = (LinearLayout)view.findViewById(R.id.outcomeLayout);
        incomeLayout = (LinearLayout)view.findViewById(R.id.incomeLayout);
        detailIncome = (ImageView)view.findViewById(R.id.detailIncomeImg);
        detailOutcome = (ImageView)view.findViewById(R.id.detailOutcomeImg);
        outcomeText = (TextView)view.findViewById(R.id.outcomeText);
        incomeText = (TextView)view.findViewById(R.id.incomeText);
        write = (TextView)view.findViewById(R.id.write);
        //设置字体
        write.setTypeface(iconfont);
        /*AssetManager mgr = getActivity().getAssets();//得到AssetManager
        Typeface tf= Typeface.createFromAsset(mgr,"msyh.ttf");//根据路径得到Typeface
        outcomeText.setTypeface(tf);//设置字体
        incomeText.setTypeface(tf);*/
        //listener
        write.setOnClickListener(this);
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        date = year + "-" + (month + 1) + "-" + day;
        DataBase(date);
        /*// App Icon
        toolbar.setNavigationIcon(R.drawable.icons_user);
        // Title
        toolbar.setTitle("小小账簿");
        // Sub Title
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        toolbar.setSubtitle(year + "年" + (month + 1) + "月" + day + "日");
        DataBase(toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //NavigationIcon点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开侧边界面
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });*/
        outcomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                Toast.makeText(getActivity(),"支出"+flag,Toast.LENGTH_LONG).show();
                detailIncome.setImageResource(R.drawable.icons_income02);
                detailOutcome.setImageResource(R.drawable.icons_outcome1);
                type = "outcome";
                DataBase(date);
            }
        });
        incomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                Toast.makeText(getActivity(),"收入"+flag,Toast.LENGTH_LONG).show();
                detailIncome.setImageResource(R.drawable.icons_income1);
                detailOutcome.setImageResource(R.drawable.icons_outcome02);
                type = "income";
                DataBase(date);
            }
        });
     /*   toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_edit:
//                       Toast.makeText(getActivity(),"write",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), RecordActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", -1);
                        intent.putExtra("bd", bundle);
                        startActivity(intent);
                        break;
                    case R.id.action_share:
                        Toast.makeText(getActivity(), "action_share", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.action_date:
                        DatePickerDialog.OnDateSetListener Datelistener=new DatePickerDialog.OnDateSetListener()
                        {
                            *//**params：view：该事件关联的组件
                             * params：myyear：当前选择的年
                             * params：monthOfYear：当前选择的月
                             * params：dayOfMonth：当前选择的日
                             *//*
                            @Override
                            public void onDateSet(DatePicker view, int myyear, int monthOfYear,int dayOfMonth) {
                                //修改year、month、day的变量值，以便以后单击按钮时，DatePickerDialog上显示上一次修改后的值
                                year=myyear;
                                month=monthOfYear;
                                day=dayOfMonth;
                                //更新日期
                                updateDate();
                            }
                            //当DatePickerDialog关闭时，更新日期显示
                            private void updateDate()
                            {
                                //在TextView上显示日期
                                toolbar.setSubtitle(year + "年" + (month+1) + "月" + day + "日");
                                DataBase(toolbar);
                            }
                        };
                        *//*Calendar c = Calendar.getInstance();
                        year = c.get(Calendar.YEAR);
                        month = c.get(Calendar.MONTH)+1;
                        day = c.get(Calendar.DAY_OF_MONTH);*//*
                        Dialog dialog = new DatePickerDialog(getActivity(),  Datelistener, year, month, day);
                        dialog.show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });*/
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.write:
                Intent intent = new Intent(getActivity(),WriteActivity.class);
                intent.putExtra("type",type);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        DataBase(toolbar);
    }

    public void DataBase(String date){
        if(flag == 0){
            List<Outcome> outcomeList = db.findAllByWhere(Outcome.class, " outcomeTime=\"" +date  + "\"");
            Log.e("outcome",outcomeList.toString());
            List<Map<String ,Object>> listItems = new ArrayList<Map<String,Object>>();
            for(int i = 0;i<outcomeList.size();i++){
                Map<String,Object> listItem = new HashMap<String,Object>();
                listItem.put("Amount", outcomeList.get(i).getOutcomeAmount());
                listItem.put("Type", outcomeList.get(i).getOutcomeType());
                listItem.put("Time", outcomeList.get(i).getOutcomeHour());
                listItem.put("id",outcomeList.get(i).getUserId());
                listItems.add(listItem);
            }
            SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), listItems, R.layout.simple_item, new String[]{"Amount","Type","Time","id"}, new int[]{R.id.detailAmount,R.id.detailType,R.id.detailTime});
            lv.setAdapter(simpleAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    // TODO Auto-generated method stub
                    HashMap<String, Object> clickItem = (HashMap<String, Object>) lv.getItemAtPosition(arg2);
                    int id = (Integer) clickItem.get("id");
                    Log.v("select", "" + id);
                    Intent intent = new Intent(getActivity(), WriteActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", id);
                    intent.putExtra("bd", bundle);
                    startActivity(intent);
                }
            });
        }else if(flag == 1){
            List<Income> incomeList = db.findAllByWhere(Income.class, " incomeTime=\"" +date  + "\"");
            Log.e("income",incomeList.toString());
            List<Map<String ,Object>> listItems = new ArrayList<Map<String,Object>>();
            for(int i = 0;i<incomeList.size();i++){
                Map<String,Object> listItem = new HashMap<String,Object>();
                listItem.put("Amount", incomeList.get(i).getIncomeAmount());
                listItem.put("Type", incomeList.get(i).getIncomeType());
                listItem.put("Time", incomeList.get(i).getIncomeHour());
                listItem.put("id",incomeList.get(i).getUserId());
                listItems.add(listItem);
            }
            SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), listItems, R.layout.simple_item, new String[]{"Amount","Type","Time","id"}, new int[]{R.id.detailAmount,R.id.detailType,R.id.detailTime});
            lv.setAdapter(simpleAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    // TODO Auto-generated method stub
                    HashMap<String, Object> clickItem = (HashMap<String, Object>) lv.getItemAtPosition(arg2);
                    int id = (Integer) clickItem.get("id");
                    Intent intent = new Intent(getActivity(), WriteActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", id);
                    bundle.putInt("flag",flag);
                    intent.putExtra("bd", bundle);
                    startActivity(intent);
                }
            });
        }
    }
}
