package com.example.pawan.androidtemplate.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Fact implements Parcelable {
    private String title;
    private String description;
    private String imageHref;

    public Fact() {
    }

    protected Fact(Parcel in) {
        title = in.readString();
        description = in.readString();
        imageHref = in.readString();
    }

    public static final Creator<Fact> CREATOR = new Creator<Fact>() {
        @Override
        public Fact createFromParcel(Parcel in) {
            return new Fact(in);
        }

        @Override
        public Fact[] newArray(int size) {
            return new Fact[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(imageHref);
    }
}
