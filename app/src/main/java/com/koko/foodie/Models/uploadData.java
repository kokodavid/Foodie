package com.koko.foodie.Models;

public class uploadData {
    private String img;
    private String name;
    private  String count;
    private String category;
    private String time;
    private String ingredients;
    private String procedure;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public uploadData(String img, String name, String count, String category, String time, String ingredients, String procedure) {
        this.img = img;
        this.name = name;
        this.count = count;
        this.category = category;
        this.time = time;
        this.ingredients = ingredients;
        this.procedure = procedure;


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


    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public uploadData() {
    }

}
