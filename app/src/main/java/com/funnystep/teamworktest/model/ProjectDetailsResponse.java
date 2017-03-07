package com.funnystep.teamworktest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjectDetailsResponse {

    @SerializedName("STATUS")
    @Expose
    public String status;

    @SerializedName("project")
    @Expose
    public Project project = null;

}
