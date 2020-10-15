package com.koko.foodie.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Food {
    @SerializedName("results")
    @Expose
    private List<Food> results = null;
     @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("readyInMinutes")
    @Expose
    private Integer readyInMinutes;
    @SerializedName("servings")
    @Expose
    private Integer servings;
    @SerializedName("sourceUrl")
    @Expose
    private String sourceUrl;
    @SerializedName("openLicense")
    @Expose
    private Integer openLicense;
    @SerializedName("image")
    @Expose
    private String image;

    public List<Food> getResults() {
        return results;
    }

    public void setResults(List<Food> results) {
        this.results = results;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(Integer readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public Integer getOpenLicense() {
        return openLicense;
    }

    public void setOpenLicense(Integer openLicense) {
        this.openLicense = openLicense;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
