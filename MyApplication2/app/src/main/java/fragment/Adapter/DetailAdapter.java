package fragment.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by XmacZone on 16/2/24.
 */
public class DetailAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<String> detail = new ArrayList<>();
    public DetailAdapter(Context context,ArrayList<String> detail) {
        this.context = context;
        this.detail = detail;
    }

    @Override
    public int getCount() {
        return detail.size();
    }

    @Override
    public Object getItem(int position) {
        return detail.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){

        }
        return null;
    }
    public class ViewHolder{

    }
}
