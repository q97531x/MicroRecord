package fragment.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.q97531x.myapplication.R;

import java.util.ArrayList;

import model.Budget;
import model.Outcome;
import model.Type;

/**
 * Created by XmacZone on 16/2/25.
 */
public class BudgetAdapter extends BaseAdapter{
    private Context context;
    ArrayList<Outcome> outcomes;
    ArrayList<Budget> budgets;
    //类型集合
    ArrayList<Type> type;
    public BudgetAdapter(Context context,ArrayList<Outcome> outcomes,ArrayList<Budget> budgets,ArrayList<Type> type) {
        this.context = context;
        this.outcomes = outcomes;
        this.budgets = budgets;
        this.type = type;
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
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.budgetlist,null);
            holder.typeicon = (ImageView)convertView.findViewById(R.id.typeicon);
            holder.txName = (TextView)convertView.findViewById(R.id.txName);
            holder.progress = (ProgressBar)convertView.findViewById(R.id.progress);
            holder.txBudget = (TextView)convertView.findViewById(R.id.txBudget);
            holder.txAccount = (TextView)convertView.findViewById(R.id.txAccount);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.typeicon.setBackgroundResource(type.get(position).getOutcomeTypeIcon());
        holder.txName.setText(type.get(position).getOutcomeTypeName());
        if(budgets.get(position).getBudgetAccount()==0) {
            holder.progress.setProgress(0);
        }else{

        }
        return null;
    }
    public static class ViewHolder{
        ImageView typeicon;
        TextView txName;
        ProgressBar progress;
        TextView txBudget;
        TextView txAccount;
    }
}
