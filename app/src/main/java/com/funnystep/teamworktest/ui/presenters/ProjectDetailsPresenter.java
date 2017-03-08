package com.funnystep.teamworktest.ui.presenters;

import android.support.v4.util.Pair;

import java.util.List;

public interface ProjectDetailsPresenter {

    /**
     * Fetch project details for a given project id
     */
    void fetchProjectDetails(int id);

    interface ViewActions {

        /**
         * Callback with fetched project data
         * @param name od project
         * @param logoUrl projects logo URL
         * @param details project details represented as a list of name-value pairs
         */
        void onProjectDetailsFetched(String name, String logoUrl, List<Pair<String, String>> details);

        /**
         * Callback with error message on fetching projects details
         */
        void onProjectFetchError(String message);

    }

}
