package com.funnystep.teamworktest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectsListResponse {

    @SerializedName("STATUS")
    @Expose
    public String status;

    @SerializedName("projects")
    @Expose
    public List<Project> projects = null;

}
