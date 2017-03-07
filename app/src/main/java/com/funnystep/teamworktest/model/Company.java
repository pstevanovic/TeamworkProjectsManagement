package com.funnystep.teamworktest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Company implements Parcelable {

    public final static Parcelable.Creator<Company> CREATOR = new Creator<Company>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Company createFromParcel(Parcel in) {
            Company instance = new Company();
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.isOwner = ((String) in.readValue((String.class.getClassLoader())));
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Company[] newArray(int size) {
            return (new Company[size]);
        }

    };
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("is-owner")
    @Expose
    public String isOwner;
    @SerializedName("id")
    @Expose
    public String id;

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(isOwner);
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }
}
