package com.example.jobinterviewpractical.activity.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class DetailBean implements Serializable {
    @SerializedName("Search")
    private ArrayList<search> Search;

    public ArrayList<search> getSearch() {
        return Search;
    }

    public void setSearch(ArrayList<search> search) {
        Search = search;
    }

    public class search implements Serializable {
        @SerializedName("Title")
        private String Title;
        @SerializedName("Year")
        private String Year;
        @SerializedName("imdbID")
        private String imdbID;
        @SerializedName("Type")
        private String Type;
        @SerializedName("Poster")
        private String Poster;

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getYear() {
            return Year;
        }

        public void setYear(String year) {
            Year = year;
        }

        public String getImdbID() {
            return imdbID;
        }

        public void setImdbID(String imdbID) {
            this.imdbID = imdbID;
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public String getPoster() {
            return Poster;
        }

        public void setPoster(String poster) {
            Poster = poster;
        }
    }





}
