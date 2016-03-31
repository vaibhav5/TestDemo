package com.androidexamples.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidexamples.R;
import com.androidexamples.model.UserInfo;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<UserInfo> mDataSet;

    public MyAdapter(List<UserInfo> dataSet) {
        this.mDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nametxt.setText(mDataSet.get(position).getName());
        holder.phNo.setText(mDataSet.get(position).getPhone_number());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nametxt;
        private TextView phNo;

        public ViewHolder(View itemView) {
            super(itemView);
            nametxt = (TextView) itemView.findViewById(R.id.name);
            phNo = (TextView) itemView.findViewById(R.id.phoneNum);
        }
    }
}
