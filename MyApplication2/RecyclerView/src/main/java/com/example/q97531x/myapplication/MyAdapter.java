package com.example.q97531x.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 渡渡鸟 on 2016/1/28.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    ArrayList<Person> list;

    public MyAdapter(ArrayList<Person> list) {
        this.list = list;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int i) {
//        RecyclerView.ViewHolder holder = viewHolder;
        viewHolder.nick.setText(list.get(i).getName());
        viewHolder.detail.setText(list.get(i).getDetail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nick;
        public TextView detail;
        public ViewHolder(View itemView) {
            super(itemView);
            nick = (TextView)itemView.findViewById(R.id.nick);
            detail = (TextView)itemView.findViewById(R.id.detail);
        }
    }
}
