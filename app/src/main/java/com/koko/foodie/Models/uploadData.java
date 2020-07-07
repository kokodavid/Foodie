package com.koko.foodie.Models;

public class uploadData {
    private String img;
    private String uploaded_by;
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

    public uploadData(String img, String uploaded_by, String name, String count, String category, String time, String ingredients, String procedure) {
        this.img = img;
        this.uploaded_by = uploaded_by;
        this.name = name;
        this.count = count;
        this.category = category;
        this.time = time;
        this.ingredients = ingredients;
        this.procedure = procedure;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUploaded_by() {
        return uploaded_by;
    }

    public void setUploaded_by(String uploaded_by) {
        this.uploaded_by = uploaded_by;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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

    public uploadData() {
    }

}
