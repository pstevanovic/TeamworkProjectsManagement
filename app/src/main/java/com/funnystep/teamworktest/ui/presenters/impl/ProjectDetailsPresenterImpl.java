package com.funnystep.teamworktest.ui.presenters.impl;

import android.content.Context;
import android.support.v4.util.Pair;
import android.text.TextUtils;

import com.funnystep.teamworktest.R;
import com.funnystep.teamworktest.model.Project;
import com.funnystep.teamworktest.model.ProjectDetailsResponse;
import com.funnystep.teamworktest.network.DataManager;
import com.funnystep.teamworktest.ui.presenters.BasePresenter;
import com.funnystep.teamworktest.ui.presenters.ProjectDetailsPresenter;

import java.util.ArrayList;
import java.util.List;

public class ProjectDetailsPresenterImpl
        extends BasePresenter<ProjectDetailsPresenter.ViewActions>
        implements ProjectDetailsPresenter, DataManager.FetchProjectDetailsCallback {

    private Context mContext;

    public ProjectDetailsPresenterImpl(Context context) {
        mContext = context;
    }

    // ProjectDetailsPresenter

    @Override
    public void fetchProjectDetails(int id) {
        DataManager.get().fetchProjectDetails(id, this);
    }

    // DataManager.FetchProjectDetailsCallback

    @Override
    public void onProjectDetailsFetched(ProjectDetailsResponse response) {
        if (isViewAttached()) {
            if (response != null && TextUtils.equals(response.status, "OK")) {
                Project project = response.project;

                List<Pair<String, String>> details = new ArrayList<>();
                details.add(new Pair<>(mContext.getString(R.string.details_status), project.status));
                details.add(new Pair<>(mContext.getString(R.string.details_category), project.category.name));
                details.add(new Pair<>(mContext.getString(R.string.details_company_info), project.company.name));
                details.add(new Pair<>(mContext.getString(R.string.details_description), project.description));
                details.add(new Pair<>(mContext.getString(R.string.details_created_on), project.createdOn));
                details.add(new Pair<>(mContext.getString(R.string.details_start_date), project.startDate));
                details.add(new Pair<>(mContext.getString(R.string.details_end_date), project.endDate));
                details.add(new Pair<>(mContext.getString(R.string.details_last_changed_on), project.lastChangedOn));

                mView.onProjectDetailsFetched(project.name, project.logo, details);
            } else {
                onProjectDetailsFetchError("status: " + (response != null ? response.status : "unknown"));
            }
        }
    }

    @Override
    public void onProjectDetailsFetchError(String message) {
        mView.onProjectFetchError("Error - " + message);
    }
}