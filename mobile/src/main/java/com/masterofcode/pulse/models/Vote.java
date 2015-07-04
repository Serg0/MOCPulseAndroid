package com.masterofcode.pulse.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

/**
 * Created by Serhii Nadolynskyi <serhii.nadolinskyi@gmail.com> on 04.07.15.
 */
public class Vote implements Parcelable {

    @Expose
    private String name;
    @Expose
    private Integer id;
    @Expose
    private Integer type;
    @Expose
    private String result;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", type=" + type +
                ", result='" + result + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeValue(this.id);
        dest.writeValue(this.type);
        dest.writeString(this.result);
    }

    public Vote() {
    }

    protected Vote(Parcel in) {
        this.name = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.type = (Integer) in.readValue(Integer.class.getClassLoader());
        this.result = in.readString();
    }

    public static final Parcelable.Creator<Vote> CREATOR = new Parcelable.Creator<Vote>() {
        public Vote createFromParcel(Parcel source) {
            return new Vote(source);
        }

        public Vote[] newArray(int size) {
            return new Vote[size];
        }
    };
}
