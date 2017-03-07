package com.funnystep.teamworktest.ui.presenters;

import android.support.v4.util.Pair;

import java.util.List;

public interface ProjectDetailsPresenter {

    void fetchProjectDetails(int id);

    interface ViewActions {

        void onProjectDetailsFetched(String name, String logoUrl, List<Pair<String, String>> details);

        void onProjectFetchError(String message);

    }

}
