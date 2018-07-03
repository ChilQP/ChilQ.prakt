    package com.example.zz.chilq.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zz.chilq.R;
import com.example.zz.chilq.model.user_model;

import java.util.List;

/**
 * Created by Pavel on 28.06.2018.
 */

public class child_list_adapter extends RecyclerView.Adapter<child_list_adapter.MyViewHolder> {

    private List<String> stringList;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView child_name;

        public MyViewHolder(View view) {
            super(view);
            child_name = (TextView) view.findViewById(R.id.child_name);
        }
    }

    public child_list_adapter(List<String> stringList) {
        this.stringList = stringList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_child_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.child_name.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

}
