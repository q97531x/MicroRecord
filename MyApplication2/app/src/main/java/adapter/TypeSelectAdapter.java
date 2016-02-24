package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.q97531x.myapplication.R;

import java.util.ArrayList;

/**
 * Created by q97531x on 2016/2/21.
 * 选择类别适配器
 */
public class TypeSelectAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<String> type;
    private ArrayList<Integer> typeIcon;
    public TypeSelectAdapter(Context context,ArrayList<String> type,ArrayList<Integer> typeIcon) {
        this.context = context;
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
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.type_item,null);
            holder.typeItem = (TextView)convertView.findViewById(R.id.type);
            holder.type_icon = (ImageView)convertView.findViewById(R.id.type_icon);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.typeItem.setText(type.get(position));
        holder.type_icon.setBackgroundResource(typeIcon.get(position));
        return convertView;
    }
    public static class ViewHolder{
        TextView typeItem;
        ImageView type_icon;
    }
}
