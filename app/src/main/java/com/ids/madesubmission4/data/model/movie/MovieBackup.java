package com.ids.madesubmission4.data.model.movie;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 05,November,2019.
 * Email : infinitydsolution@gmail.com
 * Phone : 0895411149046
 * Company : Infinity Digital Solution
 */

public class MovieBackup implements Parcelable {

    public static final Creator<MovieBackup> CREATOR = new Creator<MovieBackup>() {
        @Override
        public MovieBackup createFromParcel(Parcel in) {
            return new MovieBackup(in);
        }

        @Override
        public MovieBackup[] newArray(int size) {
            return new MovieBackup[size];
        }
    };
    private String title;
    private int image;
    private String detail;
    private String rating;


    public MovieBackup() {

    }

    public MovieBackup(Parcel in) {
        title = in.readString();
        image = in.readInt();
        detail = in.readString();
        rating = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(image);
        dest.writeString(detail);
        dest.writeString(rating);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
