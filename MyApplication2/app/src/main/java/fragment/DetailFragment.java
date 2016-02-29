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
import fragment.Adapter.DetailAdapter;
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
    private static final int UPDATE = 1234;
    SlideListView lv;
    FinalDb db;
    Toolbar toolbar;
    private String type = "outcome";
    private String date;
    public DetailFragment(){
        super();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
//        date = year + "-" + (month + 1) + "-" + day;
        date = year + "-" + (month+1);
        DataBase(date);

        outcomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
//                Toast.makeText(getActivity(),"支出"+flag,Toast.LENGTH_LONG).show();
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
//                Toast.makeText(getActivity(),"收入"+flag,Toast.LENGTH_LONG).show();
                detailIncome.setImageResource(R.drawable.icons_income1);
                detailOutcome.setImageResource(R.drawable.icons_outcome02);
                type = "income";
                DataBase(date);
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.write:
                Intent intent = new Intent(getActivity(),WriteActivity.class);
                intent.putExtra("type",type);
                intent.putExtra("aim","create");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        DataBase(toolbar);
    }

    public void DataBase(final String date){
        if(flag == 0){
            final List<Outcome> outcomeList = db.findAllByWhere(Outcome.class, " outcomeMonth=\"" + date + "\"");
            final ArrayList<Double> listAmount = new ArrayList<>();
            final ArrayList<String> listType = new ArrayList<>();
            final ArrayList<String> listTime = new ArrayList<>();

            for(int i = 0;i<outcomeList.size();i++){
                listAmount.add(outcomeList.get(i).getOutcomeAmount());
                listType.add(outcomeList.get(i).getOutcomeType());
                listTime.add(outcomeList.get(i).getOutcomeTime());
            }
            final DetailAdapter adapter = new DetailAdapter(getActivity(),listAmount,listTime,listType);
            /*Log.e("outcome",outcomeList.toString());
            List<Map<String ,Object>> listItems = new ArrayList<Map<String,Object>>();
            for(int i = 0;i<outcomeList.size();i++){
                Map<String,Object> listItem = new HashMap<String,Object>();
                listItem.put("Amount", outcomeList.get(i).getOutcomeAmount());
                listItem.put("Type", outcomeList.get(i).getOutcomeType());
                listItem.put("Time", outcomeList.get(i).getOutcomeHour());
                listItem.put("id",outcomeList.get(i).getOutcomeId());
                listItems.add(listItem);
            }
            final SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), listItems, R.layout.simple_item, new String[]{"Amount","Type","Time","id"}, new int[]{R.id.detailAmount,R.id.detailType,R.id.detailTime});
            lv.setAdapter(simpleAdapter);*/
            lv.setAdapter(adapter);
            /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
            });*/
            lv.SetRemoveListener(new SlideListView.RemoveListener() {
                @Override
                public void removeItem(SlideListView.RemoveDirection direction, int position) {
                    if (direction == SlideListView.RemoveDirection.RIGHT) {
                        Log.e("id", outcomeList.get(position).getOutcomeId()+"");
                        db.deleteById(Outcome.class, outcomeList.get(position).getOutcomeId());
                        DataBase(date);

                    }else{
                        Intent intent = new Intent(getActivity(),WriteActivity.class);
                        intent.putExtra("id",outcomeList.get(position).getOutcomeId()+"");
                        intent.putExtra("aim","update");
                        intent.putExtra("type",type);
                        startActivityForResult(intent, UPDATE);
                    }
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
