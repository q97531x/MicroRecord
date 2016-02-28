package fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import com.example.q97531x.myapplication.CalculatorActivity;
import com.example.q97531x.myapplication.R;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import fragment.Adapter.BudgetAdapter;
import model.Budget;
import model.Outcome;
import model.Type;

/**
 * Created by Administrator on 2015/7/29.
 */
public class BudgetFragment extends Fragment{
    ListView budgetList;
    FinalDb db;
    int year,month,day;
    String date;
    private  static final int setBudget = 2333;
    private BudgetAdapter adapter;
    TextView budgetDate;
    TextView txBudget;
    TextView txAccount,budgetSum,remind;
    ImageView seekBar,typeicon;
    private ProgressBar image;
    LayoutParams params,paramsall;
    LayoutParams view2,viewall;

    double budgetAccount,outcomeSum = 0,varible;
    List<Outcome> outcomeList;
    List<Budget> bugList;
    List<Outcome> outcomeListAll;
    LinearLayout budgetAll;
    List<Budget> bugListAll,bugOne;
    ArrayList<String> typeList = new ArrayList<>();
    ArrayList<Integer> typeIcon = new ArrayList<>();
    private String[] typeName = {
            "总预算","餐饮","购物","交通","娱乐","居家","医药","进修","人情","投资","其他",

    };
    private int[] typeView = {
            R.drawable.icons_all,R.drawable.icons_food,R.drawable.icons_shop,R.drawable.icons_traffic,
            R.drawable.icons_entertainment,R.drawable.icons_home,R.drawable.icons_health,R.drawable.icons_study,R.drawable.icons_dividend,
            R.drawable.icons_stocks,R.drawable.icons_others
    };
    public BudgetFragment(){
        super();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == setBudget){
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_budget, container, false);
        budgetList = (ListView) view.findViewById(R.id.budgetList);
        budgetDate = (TextView) view.findViewById(R.id.budgetDate);
        db = FinalDb.create(getActivity());
        //initBudgetBar();
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        date = year + "-" + (month + 1);
        budgetDate.setText(date);

        for(int i = 0;i<typeName.length;i++){
            typeList.add(typeName[i]);
            typeIcon.add(typeView[i]);
        }
        adapter = new BudgetAdapter(getActivity(),typeList,typeIcon,db, date);
        budgetList.setAdapter(adapter);

        budgetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<Budget> budgets = db.findAllByWhere(Budget.class, " budgetDate=\"" + date + "\"");
                Intent intent = new Intent(getActivity(), CalculatorActivity.class);
//                Bundle bd = new Bundle();
                intent.putExtra("type", typeList.get(position));
                if(budgets.size()>0) {
                    for (int i = 0; i < budgets.size(); i++) {
                        if (budgets.get(i).getBudgetType().equals(typeList.get(position))) {
                           intent.putExtra("tag", 1);
                        }else {
                            intent.putExtra("tag", -1);
                        }
                    }
                }else {
                    intent.putExtra("tag", -1);
                }
                /*if (!text.get(position).toString().equals("预算未设置")) {
                    bd.putInt("tag", 1);
                } else {
                    bd.putInt("tag", -1);
                }*/
//                Log.e("pp",text.get(position).toString());
//                intent.putExtra("bd", bd);
                startActivityForResult(intent,setBudget);
            }
        });
        return view;
    }
            /*public void setItem(String type,int j){
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
//                Log.e("pp",text.get(position).toString());
                intent.putExtra("bd", bd);
                startActivity(intent);
            }
        });
        return view;
    }*/
    /*public void initBudgetBar(){
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
    }*/
    @Override
    public void onResume() {
        super.onResume();
        bugListAll = db.findAll(Budget.class," budgetDate=\"" + budgetDate.getText().toString() + "\"");
//        adapter.notifyDataSetChanged();//刷新Listview数据
//        initBudgetBar();
        /*budgetList.setAdapter(adapter);
        Log.e("resume","resume");*/
    }
}
