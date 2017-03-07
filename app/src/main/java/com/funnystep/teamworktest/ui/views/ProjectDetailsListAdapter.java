package com.funnystep.teamworktest.ui.views;

import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.funnystep.teamworktest.R;

import java.util.ArrayList;
import java.util.List;

class ProjectDetailsListAdapter extends RecyclerView.Adapter<ProjectDetailsListAdapter.ViewHolder> {

    private List<Pair<String, String>> mData = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.name_value_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pair<String, String> data = mData.get(position);
        holder.mName.setText(data.first);
        holder.mValue.setText(data.second);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<Pair<String, String>> details) {
        mData.clear();
        mData.addAll(details);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mName;
        TextView mValue;

        public ViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.name_value_name);
            mValue = (TextView) itemView.findViewById(R.id.name_value_value);
        }


    }

}