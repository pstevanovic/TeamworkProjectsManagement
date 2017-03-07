package com.funnystep.teamworktest.ui.presenters;

import com.funnystep.teamworktest.model.Project;

import java.util.List;

public interface ProjectsListPresenter {

    void fetchAllProjects();

    interface ViewActions {

        void onAllProjectsFetched(List<Project> projectsList);

        void onAllProjectsFetchError(String message);

    }
}
