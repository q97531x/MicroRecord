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
    public ArrayList<Float> detailAmount;
    private ArrayList<String> detailTime;
    private ArrayList<String> detailType;
    public DetailAdapter(Context context,ArrayList<Float> detailAmount,ArrayList<String> detailTime,ArrayList<String> detailType) {
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

            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.simple_item,null);
            viewHolder.detailAmount = (TextView)convertView.findViewById(R.id.detailAmount);
            viewHolder.detailType = (TextView)convertView.findViewById(R.id.detailType);
            viewHolder.detailTime = (TextView)convertView.findViewById(R.id.detailTime);
            viewHolder.detailTypeIcon = (ImageView)convertView.findViewById(R.id.detailTypeIcon);

        viewHolder.detailAmount.setText(detailAmount.get(position)+"");
        viewHolder.detailTime.setText(detailTime.get(position));
        viewHolder.detailType.setText(detailType.get(position));
        switch (detailType.get(position)){
            case "餐饮":
                viewHolder.detailTypeIcon.setBackgroundResource(R.drawable.icons_food);
                break;
            case "购物":
                viewHolder.detailTypeIcon.setBackgroundResource(R.drawable.icons_shop);
                break;
            case "交通":
                viewHolder.detailTypeIcon.setBackgroundResource(R.drawable.icons_traffic);
                break;
            case "娱乐":
                viewHolder.detailTypeIcon.setBackgroundResource(R.drawable.icons_entertainment);
                break;
            case "居家":
                viewHolder.detailTypeIcon.setBackgroundResource(R.drawable.icons_home);
                break;
            case "医药":
                viewHolder.detailTypeIcon.setBackgroundResource(R.drawable.icons_health);
                break;
            case "进修":
                viewHolder.detailTypeIcon.setBackgroundResource(R.drawable.icons_study);
                break;
            case "人情":
                viewHolder.detailTypeIcon.setBackgroundResource(R.drawable.icons_dividend);
                break;
            case "投资":
                viewHolder.detailTypeIcon.setBackgroundResource(R.drawable.icons_stocks);
                break;
            case "其他":
                viewHolder.detailTypeIcon.setBackgroundResource(R.drawable.icons_others);
                break;
            default:
                viewHolder.detailTypeIcon.setBackgroundResource(R.drawable.icons_others);
                break;
        }

        return convertView;
    }
    public class ViewHolder{
        TextView detailAmount;
        TextView detailType;
        TextView detailTime;
        ImageView detailTypeIcon;
    }
}
