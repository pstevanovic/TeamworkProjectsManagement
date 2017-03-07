package com.funnystep.teamworktest.network;

import com.funnystep.teamworktest.model.ProjectDetailsResponse;
import com.funnystep.teamworktest.model.ProjectsListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProjectsService {

    String BASE_URL = "https://yat.teamwork.com/";

    @GET("/projects.json?orderby=lastActivityDate")
    Call<ProjectsListResponse> getProjects();

    @GET("/projects/{project_id}.json?")
    Call<ProjectDetailsResponse> getProject(@Path("project_id") int id);

}
