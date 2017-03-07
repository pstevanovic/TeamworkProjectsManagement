package com.funnystep.teamworktest.ui.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.funnystep.teamworktest.R;
import com.funnystep.teamworktest.model.Project;
import com.funnystep.teamworktest.ui.presenters.ProjectsListPresenter;
import com.funnystep.teamworktest.ui.presenters.impl.ProjectsListPresenterImpl;

import java.util.List;

public class ProjectsListActivity
        extends AppCompatActivity
        implements ProjectsListPresenter.ViewActions,
        SwipeRefreshLayout.OnRefreshListener,
        ProjectsListAdapter.ListItemClickListener {

    private ProjectsListPresenterImpl mPresenter;
    private View mMainLayout;
    private SwipeRefreshLayout mSwipeRefresh;
    private ProjectsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_list);

        mMainLayout = findViewById(R.id.projects_list_main_layout);

        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.projects_list_refresh);
        mSwipeRefresh.setOnRefreshListener(this);

        mAdapter = new ProjectsListAdapter();
        RecyclerView projectsList = (RecyclerView) findViewById(R.id.projects_list_view);
        projectsList.setAdapter(mAdapter);
        mAdapter.setListInteractionListener(this);

        mPresenter = new ProjectsListPresenterImpl();
        mPresenter.attachView(this);

        mPresenter.fetchAllProjects();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    // SwipeRefreshLayout.OnRefreshListener

    @Override
    public void onRefresh() {
        mPresenter.fetchAllProjects();
    }

    // ProjectsListAdapter.ListItemClickListener

    @Override
    public void onListItemClicked(int position) {
        Project project = mAdapter.getProject(position);

        Intent intent = new Intent(this, ProjectDetailActivity.class);
        intent.putExtra(ProjectDetailActivity.PROJECT_ID, Integer.valueOf(project.id));

        startActivity(intent);
    }

    // ProjectsListPresenter.ViewActions

    @Override
    public void onAllProjectsFetched(List<Project> projectsList) {
        mSwipeRefresh.setRefreshing(false);
        mAdapter.setData(projectsList);
    }

    @Override
    public void onAllProjectsFetchError(String message) {
        mSwipeRefresh.setRefreshing(false);
        Snackbar.make(mMainLayout, message, Snackbar.LENGTH_LONG).show();
    }

}
