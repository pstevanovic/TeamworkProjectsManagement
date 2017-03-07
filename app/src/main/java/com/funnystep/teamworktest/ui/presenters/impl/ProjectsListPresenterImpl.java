package com.funnystep.teamworktest.ui.presenters.impl;

import android.text.TextUtils;

import com.funnystep.teamworktest.model.ProjectsListResponse;
import com.funnystep.teamworktest.network.DataManager;
import com.funnystep.teamworktest.ui.presenters.BasePresenter;
import com.funnystep.teamworktest.ui.presenters.ProjectsListPresenter;

public class ProjectsListPresenterImpl
        extends BasePresenter<ProjectsListPresenter.ViewActions>
        implements ProjectsListPresenter, DataManager.FetchProjectsCallback {

    // ProjectsListPresenter

    @Override
    public void fetchAllProjects() {
        DataManager.get().fetchProjects(this);
    }

    // DataManager.FetchProjectsCallback

    @Override
    public void onProjectsFetched(ProjectsListResponse response) {
        if (isViewAttached()) {
            if (response != null && TextUtils.equals(response.status, "OK")) {
                mView.onAllProjectsFetched(response.projects);
            } else {
                onProjectsFetchError("status: " + (response != null ? response.status : "unknown"));
            }
        }
    }

    @Override
    public void onProjectsFetchError(String message) {
        mView.onAllProjectsFetchError("Error - " + message);
    }
}
