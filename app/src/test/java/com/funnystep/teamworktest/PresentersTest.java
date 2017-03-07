package com.funnystep.teamworktest;

import android.content.Context;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.util.Log;

import com.funnystep.teamworktest.model.Category;
import com.funnystep.teamworktest.model.Company;
import com.funnystep.teamworktest.model.Project;
import com.funnystep.teamworktest.model.ProjectDetailsResponse;
import com.funnystep.teamworktest.model.ProjectsListResponse;
import com.funnystep.teamworktest.ui.presenters.ProjectDetailsPresenter;
import com.funnystep.teamworktest.ui.presenters.ProjectsListPresenter;
import com.funnystep.teamworktest.ui.presenters.impl.ProjectDetailsPresenterImpl;
import com.funnystep.teamworktest.ui.presenters.impl.ProjectsListPresenterImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class, TextUtils.class})
public class PresentersTest {

    @Mock
    private Context mContext;

    @Mock
    private ProjectsListPresenter.ViewActions mViewProjectsList;

    @Mock
    private ProjectDetailsPresenter.ViewActions mViewProjectDetails;

    private ProjectsListPresenterImpl mProjectsListPresenter;

    private ProjectDetailsPresenterImpl mProjectDetailsPresenter;

    @Before
    public void setUp() throws Exception {
        mProjectsListPresenter = new ProjectsListPresenterImpl();
        mProjectsListPresenter.attachView(mViewProjectsList);

        mProjectDetailsPresenter = new ProjectDetailsPresenterImpl(mContext);
        mProjectDetailsPresenter.attachView(mViewProjectDetails);

        PowerMockito.mockStatic(Log.class);
        PowerMockito.mockStatic(TextUtils.class);
    }

    @After
    public void tearDown() {
        mViewProjectsList = null;
        mViewProjectDetails = null;
        mProjectsListPresenter = null;
        mProjectDetailsPresenter = null;
    }

    @Test
    public void testNullP1() throws Exception {
        mProjectsListPresenter.onProjectsFetched(null);
        Mockito.verify(mViewProjectsList, Mockito.times(1)).onAllProjectsFetchError("Error - status: unknown");
    }

    @Test
    public void testInvalidStatusP1() throws Exception {
        ProjectsListResponse response = new ProjectsListResponse();
        response.status = "Not found";
        mProjectsListPresenter.onProjectsFetched(response);

        Mockito.verify(mViewProjectsList, Mockito.times(1)).onAllProjectsFetchError("Error - status: Not found");
    }

    @Test
    public void testValidResponseP1() throws Exception {
        ProjectsListResponse response = new ProjectsListResponse();
        response.status = "OK";
        response.projects = new ArrayList<>();

        Mockito.when(TextUtils.equals(response.status, "OK")).thenReturn(true);

        mProjectsListPresenter.onProjectsFetched(response);

        Mockito.verify(mViewProjectsList, Mockito.times(1)).onAllProjectsFetched(response.projects);
    }

    @Test
    public void testNullP2() throws Exception {
        mProjectDetailsPresenter.onProjectDetailsFetched(null);
        Mockito.verify(mViewProjectDetails, Mockito.times(1)).onProjectFetchError("Error - status: unknown");
    }

    @Test
    public void testInvalidStatusP2() throws Exception {
        ProjectDetailsResponse response = new ProjectDetailsResponse();
        response.status = "Not found";
        mProjectDetailsPresenter.onProjectDetailsFetched(response);

        Mockito.verify(mViewProjectDetails, Mockito.times(1)).onProjectFetchError("Error - status: Not found");
    }

    @Test
    public void testValidResponseP2() throws Exception {
        ProjectDetailsResponse response = new ProjectDetailsResponse();
        response.status = "OK";
        response.project = new Project();
        Project project = response.project;
        project.name = "pName";
        project.logo = "logoUrl";
        project.category = new Category();
        project.category.name = "cName";
        project.company = new Company();
        project.company.name = "cName";
        project.description = "logoUrl";
        project.createdOn = "logoUrl";
        project.startDate = "logoUrl";
        project.endDate = "logoUrl";
        project.lastChangedOn = "logoUrl";

        List<Pair<String, String>> details = new ArrayList<>();
        details.add(new Pair<>(mContext.getString(R.string.details_status), project.status));
        details.add(new Pair<>(mContext.getString(R.string.details_category), project.category.name));
        details.add(new Pair<>(mContext.getString(R.string.details_company_info), project.company.name));
        details.add(new Pair<>(mContext.getString(R.string.details_description), project.description));
        details.add(new Pair<>(mContext.getString(R.string.details_created_on), project.createdOn));
        details.add(new Pair<>(mContext.getString(R.string.details_start_date), project.startDate));
        details.add(new Pair<>(mContext.getString(R.string.details_end_date), project.endDate));
        details.add(new Pair<>(mContext.getString(R.string.details_last_changed_on), project.lastChangedOn));

        Mockito.when(TextUtils.equals(response.status, "OK")).thenReturn(true);

        mProjectDetailsPresenter.onProjectDetailsFetched(response);

        Mockito.verify(mViewProjectDetails, Mockito.times(1)).onProjectDetailsFetched("pName", "logoUrl", details);
    }

}