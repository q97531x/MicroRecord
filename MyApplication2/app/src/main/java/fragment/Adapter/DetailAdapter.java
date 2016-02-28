package fragment.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.q97531x.myapplication.R;

import java.util.ArrayList;

/**
 * Created by XmacZone on 16/2/24.
 */
public class DetailAdapter extends BaseAdapter{
    private Context context;
    public ArrayList<Double> detailAmount;
    private ArrayList<String> detailTime;
    private ArrayList<String> detailType;
    public DetailAdapter(Context context,ArrayList<Double> detailAmount,ArrayList<String> detailTime,ArrayList<String> detailType) {
        this.context = context;
        this.detailAmount = detailAmount;
        this.detailTime = detailTime;
        this.detailType = detailType;
    }

    @Override
    public int getCount() {
        return detailAmount.size();
    }

    @Override
    public Object getItem(int position) {
        return detailAmount.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.simple_item,null);
            viewHolder.detailAmount = (TextView)convertView.findViewById(R.id.detailAmount);
            viewHolder.detailType = (TextView)convertView.findViewById(R.id.detailType);
            viewHolder.detailTime = (TextView)convertView.findViewById(R.id.detailTime);
            viewHolder.detailTypeIcon = (ImageView)convertView.findViewById(R.id.detailTypeIcon);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.detailAmount.setText(detailAmount+"");
        return convertView;
    }
    public class ViewHolder{
        TextView detailAmount;
        TextView detailType;
        TextView detailTime;
        ImageView detailTypeIcon;
    }
}
