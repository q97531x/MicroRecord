package fragment;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import com.example.q97531x.myapplication.CalculatorActivity;
import com.example.q97531x.myapplication.R;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Budget;
import model.Outcome;

/**
 * Created by Administrator on 2015/7/29.
 */
public class BudgetFragment extends Fragment{
    ListView budgetList;
    FinalDb db;
    double[] sum = new double[10];
    double budgetCount=0,remindSum=0;
    int year,month,day;
    String date;
    FrameLayout frame,frame1;
    TextView txItem,budgetDate;
    TextView txBudget;
    TextView txAccount,budgetSum,remind;
    ImageView image,seekBar,typeicon;
    LayoutParams params,paramsall;
    LayoutParams view2,viewall;
    BaseAdapter adapter;
    double budgetAccount,outcomeSum = 0,varible;
    List<Outcome> outcomeList;
    List<Budget> bugList;
    List<Outcome> outcomeListAll;
    LinearLayout budgetAll;
    List<Budget> bugListAll,bugOne;
    ArrayList<String> typeList = new ArrayList<>();
    ArrayList<String> text = new ArrayList<>();
    int icon[] = new int[]{
            R.drawable.icons_food,R.drawable.icons_shop,R.drawable.icons_traffic,R.drawable.icons_entertainment,R.drawable.icons_home,R.drawable.icons_health
            ,R.drawable.icons_study,R.drawable.icons_beauty,R.drawable.icons_travel,R.drawable.icons_others
    };
    public BudgetFragment(){
        super();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_budget,container,false);
        budgetList = (ListView)view.findViewById(R.id.budgetList);
        budgetSum = (TextView)view.findViewById(R.id.budgetSum);
        frame1 = (FrameLayout)view.findViewById(R.id.frame1);
        seekBar = (ImageView)view.findViewById(R.id.seekBar);
        paramsall = frame1.getLayoutParams();
        viewall = seekBar.getLayoutParams();
        remind = (TextView)view.findViewById(R.id.remind);
        budgetAll = (LinearLayout)view.findViewById(R.id.budgetAll);
        budgetDate = (TextView)view.findViewById(R.id.budgetDate);
        db = FinalDb.create(getActivity());
        //initBudgetBar();
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        date = year+"年"+(month+1)+"月";
        budgetDate.setText(date);
        budgetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                initText(10);
                View view1 = inflater.inflate(R.layout.budgetlist, parent, false);
                frame = (FrameLayout)view1.findViewById(R.id.frame);
                typeicon = (ImageView)view1.findViewById(R.id.typeicon);
                txItem = (TextView)view1.findViewById(R.id.txName);
                txBudget = (TextView)view1.findViewById(R.id.txBudget);
                txAccount = (TextView)view1.findViewById(R.id.txAccount);
                image = (ImageView)view1.findViewById(R.id.listView);
                params = frame.getLayoutParams();
                view2 = image.getLayoutParams();
                switch (position){
                    case 0:
                        setItem("餐饮",position);
                        break;
                    case 1:
                        setItem("购物",position);
                        break;
                    case 2:
                        setItem("交通",position);
                        break;
                    case 3:
                        setItem("娱乐",position);
                        break;
                    case 4:
                        setItem("居家",position);
                        break;
                    case 5:
                        setItem("医药",position);
                        break;
                    case 6:
                        setItem("进修",position);
                        break;
                    case 7:
                        setItem("美妆",position);
                        break;
                    case 8:
                        setItem("旅游",position);
                        break;
                    case 9:
                        setItem("其他",position);
                        break;
                }
                return view1;
            }
            //初始化文字
            public void initText(int k){
                for(int i = 0;i<k;i++){
                    text.add("预算未设置");
                }
            }
            public void setItem(String type,int j){
                budgetAccount=0.00;
                typeicon.setImageResource(icon[j]);
                txItem.setText(type);
                //搜索支出表与当前类型的匹配项
                outcomeList = db.findAllByWhere(Outcome.class, " outcomeType=\"" + txItem.getText().toString() + "\"");
                //搜索预算表与当前类型的匹配项
                bugList = db.findAllByWhere(Budget.class, " budgetType=\"" + txItem.getText().toString() + "\"");
                Log.e("bugList",""+bugList.size());
                typeList.add(txItem.getText().toString());
                if(bugList.size() == 0){
                    text.set(j,"预算未设置");
                    txBudget.setText(text.get(j).toString());
                    budgetAccount = 0;
                    view2.width = 0;
                    image.setLayoutParams(view2);
                }else if(bugList.size()>0) {
                    budgetAccount = bugList.get(bugList.size()-1).getBudgetAccount();
                    text.set(j,""+budgetAccount);
                    txBudget.setText(text.get(j).toString());
                    //Log.e("budget", "" + bugList.size() + "金额" + bugList.get(0).getBudgetAccount()+"tx"+txBudget.getText().toString());
                }
                for (int i = 0;i<outcomeList.size();i++){
                        sum[j] = sum[j]+outcomeList.get(i).getOutcomeAmount();
                     }
                    if((sum[j]/budgetAccount)<1.00){
                        varible = sum[j]/budgetAccount;
                     }else if(sum[j]/budgetAccount>=1.00){
                        varible = 1.00;
                    }
                if(varible == 1.00) {
                    view2.width = (int) (view2.width * varible);
                }else{
                    view2.width = (int)(view2.width*varible);
                }
                image.setLayoutParams(view2);
                if(budgetAccount - sum[j]>=0) {
                    txAccount.setText("余额" + (budgetAccount - sum[j]));
                }else {
                    txAccount.setText("超支" + Math.abs(budgetAccount - sum[j]));
                }
                sum[j] = 0;
            }
        };
        budgetList.setAdapter(adapter);
        budgetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CalculatorActivity.class);
                Bundle bd = new Bundle();
                bd.putString("type", typeList.get(position).toString());
                if (!text.get(position).toString().equals("预算未设置")) {
                    bd.putInt("tag", 1);
                } else {
                    bd.putInt("tag", -1);
                }
                Log.e("pp",text.get(position).toString());
                intent.putExtra("bd", bd);
                startActivity(intent);
            }
        });
        return view;
    }
    public void initBudgetBar(){
        outcomeListAll = db.findAll(Outcome.class," reOutcomeTime=\"" + budgetDate.getText().toString() + "\"");
        bugListAll = db.findAll(Budget.class," budgetDate=\"" + budgetDate.getText().toString() + "\"");
            if (bugListAll.size() > 0) {
                for (int i = 0; i < bugListAll.size(); i++) {
                    budgetCount = budgetCount + bugListAll.get(i).getBudgetAccount();
                }
                budgetSum.setText("" + budgetCount);
            } else {
                budgetSum.setText("预算未设置");
                budgetCount = 0;
            }
        for(int j = 0;j<outcomeListAll.size();j++){
            outcomeSum = outcomeSum+outcomeListAll.get(j).getOutcomeAmount();
        }
        varible = outcomeSum/budgetCount;
        if(budgetCount == 0){
            remind.setText("0.0");
            viewall.width = 0;
            seekBar.setLayoutParams(viewall);
        }else {
            viewall.width = (int)(viewall.width*varible);
            seekBar.setLayoutParams(viewall);
            remindSum = budgetCount - outcomeSum;
            remind.setText("余额" + remindSum);
        }
        budgetCount = 0;
    }
    @Override
    public void onResume() {
        super.onResume();
        bugListAll = db.findAll(Budget.class," budgetDate=\"" + budgetDate.getText().toString() + "\"");
        adapter.notifyDataSetChanged();//刷新Listview数据
        initBudgetBar();
        /*budgetList.setAdapter(adapter);
        Log.e("resume","resume");*/
    }
}
