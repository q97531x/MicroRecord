/*
package adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.example.q97531x.myapplication.R;

import java.util.zip.Inflater;

*/
/**
 * Created by q97531x on 2016/5/1.
 *//*

public class GridPhotoAdapter extends CursorAdapter{
    LayoutInflater mInflater;
    int itemWidth,sWidthPix;

    public GridPhotoAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        mInflater = LayoutInflater.from(context);
        int spacePix = 4;
        sWidthPix = context.getResources().getDisplayMetrics().widthPixels;
        itemWidth = (sWidthPix - spacePix * 4) / 3;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View convertView = mInflater.inflate(R.layout.item_pick_photo,parent,false);
        ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();
        layoutParams.height = itemWidth;
        layoutParams.width = itemWidth;
        convertView.setLayoutParams(layoutParams);

        GridViewHolder holder = new GridViewHolder();
//        holder.img = (ImageView) convertView.findViewById(R.id.img);
        holder.mark = (ImageView) convertView.findViewById(R.id.mark);
        holder.check = (CheckBox) convertView.findViewById(R.id.check);
        */
/*GridViewCheckTag checkTag = new GridViewCheckTag(holder.iconFore);
        holder.check.setTag(checkTag);*//*

//        holder.check.setOnClickListener(mClickItem);
        convertView.setTag(holder);
        return convertView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }

    static class GridViewHolder {
        ImageView img;
        ImageView mark;
        CheckBox check;
    }
}
*/
