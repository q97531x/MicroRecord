package adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.example.q97531x.myapplication.Bean.FileInfo;
import com.example.q97531x.myapplication.R;


import java.util.ArrayList;

/**
 * Created by q97531x on 2016/5/2.
 */
public class GridViewBaseAdapter extends BaseAdapter {
    int itemWidth, sWidthPix;
    private Context context;
    private ArrayList<FileInfo> images = new ArrayList<>();
    private ArrayList<FileInfo> selectImage = new ArrayList<>();

    public GridViewBaseAdapter(Context context, ArrayList<FileInfo> images) {
        this.context = context;
        this.images = images;
        int spacePix = 4;
        sWidthPix = context.getResources().getDisplayMetrics().widthPixels;
        itemWidth = (sWidthPix - spacePix * 4) / 3;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
//        final Uri imageUri = Uri.withAppendedPath(mUri, "" + images.get(position).getId());
        if (convertView == null) {
            holder = new ViewHolder();
//            convertView = View.inflate(context, R.layout.item_pick_photo, null);
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pick_photo, parent, false);
            ViewGroup.LayoutParams lp = convertView.getLayoutParams();
            lp.width = itemWidth;
            lp.height = itemWidth;
            convertView.setLayoutParams(lp);
            holder.img1 = (ImageView) convertView.findViewById(R.id.img1);
            holder.mark = convertView.findViewById(R.id.mark);
            holder.check = (CheckBox) convertView.findViewById(R.id.check);
            convertView.setTag(holder);
        } else {
            ViewGroup.LayoutParams lp = convertView.getLayoutParams();
            lp.width = itemWidth;
            lp.height = itemWidth;
            convertView.setLayoutParams(lp);
            holder = (ViewHolder) convertView.getTag();
        }
//        Log.e("path",images.get(position).getFilePath());
        //加载图片
//        Picasso.with(context).load(Uri.withAppendedPath(mUri, "" + images.get(position).getId())).into(holder.img1);
        Glide.with(context).load(images.get(position).getUri()).into(holder.img1);
        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    holder.check.setChecked(isChecked);
                    holder.mark.setVisibility(View.VISIBLE);
                    selectImage.add(images.get(position));
                } else {
                    holder.check.setChecked(isChecked);
                    holder.mark.setVisibility(View.GONE);
                    for(int i = 0;i<selectImage.size();i++){
                        if(selectImage.get(i).equals(images.get(position))){
                            selectImage.remove(i);
                        }
                    }
                }
            }
        });
        return convertView;
    }

    public ArrayList<FileInfo> getCheckUri(){
        return selectImage;
    }
    private static class ViewHolder {
        ImageView img1;
        View mark;
        CheckBox check;
    }
}
