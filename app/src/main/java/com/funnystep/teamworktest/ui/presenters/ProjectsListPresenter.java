package com.funnystep.teamworktest.ui.presenters;

import com.funnystep.teamworktest.model.Project;

import java.util.List;

public interface ProjectsListPresenter {

    /**
     * Fetch all users projects
     */
    void fetchAllProjects();

    interface ViewActions {

        /**
         * Callback with a list of users projects
         */
        void onAllProjectsFetched(List<Project> projectsList);

        /**
         * Callback with error message on fetching users projects
         */
        void onAllProjectsFetchError(String message);

    }
}
