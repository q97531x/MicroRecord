package fragment.Adapter;

import android.content.Context;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.q97531x.myapplication.R;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.List;

import model.Budget;
import model.Outcome;
import model.Type;

/**
 * Created by XmacZone on 16/2/25.
 */
public class BudgetAdapter extends BaseAdapter{
    private Context context;
    List<Outcome> outcomes;
    List<Budget> budgets;
    FinalDb db;
    String date;
    //设置的预算
    float budget;
    float outcome;
    float a;
    float b;
    //类型集合
    ArrayList<String> type;
    ArrayList<Integer> typeIcon;
    public BudgetAdapter(Context context,ArrayList<String> type,ArrayList<Integer> typeIcon,FinalDb db,String date) {
        this.context = context;
        this.db = db;
        this.date = date;
        this.type = type;
        this.typeIcon = typeIcon;
    }

    @Override
    public int getCount() {
        return type.size();
    }

    @Override
    public Object getItem(int position) {
        return type.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        int width;
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.budgetlist,null);
            holder.typeicon = (ImageView)convertView.findViewById(R.id.type_icon);
            holder.txName = (TextView)convertView.findViewById(R.id.txName);
            holder.progress = (ProgressBar)convertView.findViewById(R.id.progress);
            holder.txBudget = (TextView)convertView.findViewById(R.id.txBudget);
            holder.txAccount = (TextView)convertView.findViewById(R.id.txAccount);

        if(position == 0){
            holder.typeicon.setBackgroundResource(typeIcon.get(position));
            holder.txName.setText(type.get(position));
        }else {
            holder.typeicon.setBackgroundResource(typeIcon.get(position));
            holder.txName.setText(type.get(position));
            //Log.e("position",""+position);
            //查找表中类型匹配的数据
            budgets = db.findAllByWhere(Budget.class, " budgetType=\"" + type.get(position) + "\"");
            outcomes = db.findAllByWhere(Outcome.class, " outcomeType=\"" + type.get(position) + "\"");
            Log.e("num",budgets.toString());
            if (budgets.size() == 0) {
                holder.txBudget.setText("预算未设置");
            } else{
                for (int i = 0;i < budgets.size();i++){
                    if(budgets.get(i).getBudgetDate().equals(date)){
                        budget = budgets.get(i).getBudgetAccount();
                    }
                }
                for (int i = 0;i < outcomes.size();i++){
                    if(outcomes.get(i).getOutcomeMonth().equals(date)){
                        outcome = outcome + outcomes.get(i).getOutcomeAmount();
                    }
                }
                a = outcome/budget;
                if(a>1){
                    a = 1;
                }
                width = (int)(a*100);
//                Log.e("budget",""+budget+a+"xx"+width);
                holder.txBudget.setText("预算" + budget + "￥");
                holder.progress.setProgress(width);
                b = budget - outcome;
                if(b<0){
                    holder.txAccount.setText("超支"+(-b));
                }else{
                    holder.txAccount.setText("结余"+b);
                }
                outcome = 0;
            }
        }
        return convertView;
    }
    public static class ViewHolder{
        ImageView typeicon;
        TextView txName;
        ProgressBar progress;
        TextView txBudget;
        TextView txAccount;
    }
}
