package com.funnystep.teamworktest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Project implements Parcelable {

    public final static Parcelable.Creator<Project> CREATOR = new Creator<Project>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Project createFromParcel(Parcel in) {
            Project instance = new Project();
            instance.company = ((Company) in.readValue((Company.class.getClassLoader())));
            instance.starred = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.showAnnouncement = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.announcement = ((String) in.readValue((String.class.getClassLoader())));
            instance.description = ((String) in.readValue((String.class.getClassLoader())));
            instance.status = ((String) in.readValue((String.class.getClassLoader())));
            instance.isProjectAdmin = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.createdOn = ((String) in.readValue((String.class.getClassLoader())));
            instance.category = ((Category) in.readValue((Category.class.getClassLoader())));
            instance.startPage = ((String) in.readValue((String.class.getClassLoader())));
            instance.startDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.logo = ((String) in.readValue((String.class.getClassLoader())));
            instance.notifyeveryone = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.lastChangedOn = ((String) in.readValue((String.class.getClassLoader())));
            instance.endDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.harvestTimersEnabled = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Project[] newArray(int size) {
            return (new Project[size]);
        }

    };
    @SerializedName("company")
    @Expose
    public Company company;
    @SerializedName("starred")
    @Expose
    public boolean starred;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("show-announcement")
    @Expose
    public boolean showAnnouncement;
    @SerializedName("announcement")
    @Expose
    public String announcement;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("isProjectAdmin")
    @Expose
    public boolean isProjectAdmin;
    @SerializedName("created-on")
    @Expose
    public String createdOn;
    @SerializedName("category")
    @Expose
    public Category category;
    @SerializedName("start-page")
    @Expose
    public String startPage;
    @SerializedName("startDate")
    @Expose
    public String startDate;
    @SerializedName("logo")
    @Expose
    public String logo;
    @SerializedName("notifyeveryone")
    @Expose
    public boolean notifyeveryone;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("last-changed-on")
    @Expose
    public String lastChangedOn;
    @SerializedName("endDate")
    @Expose
    public String endDate;
    @SerializedName("harvest-timers-enabled")
    @Expose
    public String harvestTimersEnabled;

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(company);
        dest.writeValue(starred);
        dest.writeValue(name);
        dest.writeValue(showAnnouncement);
        dest.writeValue(announcement);
        dest.writeValue(description);
        dest.writeValue(status);
        dest.writeValue(isProjectAdmin);
        dest.writeValue(createdOn);
        dest.writeValue(category);
        dest.writeValue(startPage);
        dest.writeValue(startDate);
        dest.writeValue(logo);
        dest.writeValue(notifyeveryone);
        dest.writeValue(id);
        dest.writeValue(lastChangedOn);
        dest.writeValue(endDate);
        dest.writeValue(harvestTimersEnabled);
    }

    public int describeContents() {
        return 0;
    }

}
