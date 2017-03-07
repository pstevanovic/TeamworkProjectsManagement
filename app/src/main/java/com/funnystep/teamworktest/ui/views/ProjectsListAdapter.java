package com.funnystep.teamworktest.ui.views;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.funnystep.teamworktest.R;
import com.funnystep.teamworktest.model.Project;
import com.funnystep.teamworktest.ui.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class ProjectsListAdapter extends RecyclerView.Adapter<ProjectsListAdapter.ViewHolder> {

    private ListItemClickListener mItemClickListener;
    private List<Project> mData = new ArrayList<>();

    void setListInteractionListener(ListItemClickListener listener) {
        mItemClickListener = listener;
    }

    Project getProject(int position) {
        return mData.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Project project = mData.get(position);

        holder.mProjectName.setText(project.name);
        holder.mCompanyName.setText(project.company.name);
        holder.mProjectStatus.setText(project.status);

        holder.setOnClickListener(mItemClickListener);

        Picasso.with(holder.mProjectLogo.getContext())
                .load(!TextUtils.isEmpty(project.logo) ? project.logo : "error")
                .transform(new CircleTransform())
                .error(R.mipmap.ic_launcher_round)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(holder.mProjectLogo);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    void setData(List<Project> data) {
        mData.clear();
        if (data != null)
            mData.addAll(data);
        notifyDataSetChanged();
    }

    interface ListItemClickListener {
        void onListItemClicked(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mProjectLogo;
        TextView mProjectName;
        TextView mCompanyName;
        TextView mProjectStatus;
        private ListItemClickListener mOnItemClickListener;

        ViewHolder(View itemView) {
            super(itemView);

            mProjectLogo = (ImageView) itemView.findViewById(R.id.project_list_item_logo);
            mProjectName = (TextView) itemView.findViewById(R.id.project_list_item_name);
            mCompanyName = (TextView) itemView.findViewById(R.id.project_list_item_company);
            mProjectStatus = (TextView) itemView.findViewById(R.id.project_list_item_status);

            itemView.setOnClickListener(this);
        }

        void setOnClickListener(ListItemClickListener itemClickListener) {
            mOnItemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null)
                mOnItemClickListener.onListItemClicked(getAdapterPosition());
        }

    }

}
