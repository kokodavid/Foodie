package com.koko.foodie.Models;

public class uploadData {

    private String name;
    private String category;
    private String country;
    private String ingredients;
    private String img;


    public uploadData() {
    }

    public uploadData(String name, String category, String country, String ingredients,String img) {
        this.name = name;
        this.category = category;
        this.country = country;
        this.ingredients = ingredients;
        this.img = img;

    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
