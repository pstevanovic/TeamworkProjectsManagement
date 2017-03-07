package com.funnystep.teamworktest.ui.views;

import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.funnystep.teamworktest.R;
import com.funnystep.teamworktest.ui.presenters.ProjectDetailsPresenter;
import com.funnystep.teamworktest.ui.presenters.impl.ProjectDetailsPresenterImpl;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProjectDetailActivity
        extends AppCompatActivity
        implements ProjectDetailsPresenter.ViewActions {

    static final String PROJECT_ID = "PROJECT_ID";

    private ProjectDetailsPresenterImpl mPresenter;
    private ImageView mLogo;
    private ProjectDetailsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mLogo = (ImageView) findViewById(R.id.project_detail_logo);
        RecyclerView mDetailsList = (RecyclerView) findViewById(R.id.project_detail_list);

        mAdapter = new ProjectDetailsListAdapter();
        mDetailsList.setAdapter(mAdapter);

        mPresenter = new ProjectDetailsPresenterImpl(this);
        mPresenter.attachView(this);

        int projectId = getIntent().getExtras().getInt(PROJECT_ID);
        mPresenter.fetchProjectDetails(projectId);

        getSupportActionBar().setTitle("");
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    // ProjectDetailsPresenter.ViewActions

    @Override
    public void onProjectDetailsFetched(String name, String logoUrl, List<Pair<String, String>> details) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(name);
        Picasso.with(this).load(logoUrl).into(mLogo);
        mAdapter.setData(details);
    }

    @Override
    public void onProjectFetchError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        finish();
    }
}
