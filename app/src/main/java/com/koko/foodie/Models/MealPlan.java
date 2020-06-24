package com.koko.foodie.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MealPlan {

    @SerializedName("meals")
    @Expose
    private List<MealPlan> meals = null;
    @SerializedName("nutrients")
    @Expose
    private Nutrients nutrients;

    public List<MealPlan> getMeals() {
        return meals;
    }

    public void setMeals(List<MealPlan> meals) {
        this.meals = meals;
    }

    public Nutrients getNutrients() {
        return nutrients;
    }

    public void setNutrients(Nutrients nutrients) {
        this.nutrients = nutrients;
    }

    public class Nutrients {

        @SerializedName("calories")
        @Expose
        private Float calories;
        @SerializedName("protein")
        @Expose
        private Float protein;
        @SerializedName("fat")
        @Expose
        private Float fat;
        @SerializedName("carbohydrates")
        @Expose
        private Float carbohydrates;

        public Float getCalories() {
            return calories;
        }

        public void setCalories(Float calories) {
            this.calories = calories;
        }

        public Float getProtein() {
            return protein;
        }

        public void setProtein(Float protein) {
            this.protein = protein;
        }

        public Float getFat() {
            return fat;
        }

        public void setFat(Float fat) {
            this.fat = fat;
        }

        public Float getCarbohydrates() {
            return carbohydrates;
        }

        public void setCarbohydrates(Float carbohydrates) {
            this.carbohydrates = carbohydrates;
        }

    }

    public class Meal {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("imageType")
        @Expose
        private String imageType;
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

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getImageType() {
            return imageType;
        }

        public void setImageType(String imageType) {
            this.imageType = imageType;
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

    }

}