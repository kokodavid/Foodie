package com.koko.foodie.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FodieA {
    @SerializedName("extendedIngredients")
    @Expose
    private List<FodieA> ingredients = null ;

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("aisle")
    @Expose
    private String aisle;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("consistency")
    @Expose
    private String consistency;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("original")
    @Expose
    private String original;
    @SerializedName("originalString")
    @Expose
    private String originalString;
    @SerializedName("originalName")
    @Expose
    private String originalName;
    @SerializedName("amount")
    @Expose
    private Float amount;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("meta")
    @Expose
    private List<String> meta = null;
    @SerializedName("metaInformation")
    @Expose
    private List<String> metaInformation = null;
    @SerializedName("measures")
    @Expose
    private FoodInfo.Measures measures;

    public List<FodieA> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<FodieA> results) {
        this.ingredients = results;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getConsistency() {
        return consistency;
    }

    public void setConsistency(String consistency) {
        this.consistency = consistency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getOriginalString() {
        return originalString;
    }

    public void setOriginalString(String originalString) {
        this.originalString = originalString;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<String> getMeta() {
        return meta;
    }

    public void setMeta(List<String> meta) {
        this.meta = meta;
    }

    public List<String> getMetaInformation() {
        return metaInformation;
    }

    public void setMetaInformation(List<String> metaInformation) {
        this.metaInformation = metaInformation;
    }

    public FoodInfo.Measures getMeasures() {
        return measures;
    }

    public void setMeasures(FoodInfo.Measures measures) {
        this.measures = measures;
    }

    public class Measures {

        @SerializedName("metric")
        @Expose
        private FoodInfo.Metric metric;

        public FoodInfo.Metric getMetric() {
            return metric;
        }

        public void setMetric(FoodInfo.Metric metric) {
            this.metric = metric;
        }
    }

    public class Metric {

        @SerializedName("amount")
        @Expose
        private Float amount;
        @SerializedName("unitShort")
        @Expose
        private String unitShort;
        @SerializedName("unitLong")
        @Expose
        private String unitLong;

        public Float getAmount() {
            return amount;
        }

        public void setAmount(Float amount) {
            this.amount = amount;
        }

        public String getUnitShort() {
            return unitShort;
        }

        public void setUnitShort(String unitShort) {
            this.unitShort = unitShort;
        }

        public String getUnitLong() {
            return unitLong;
        }

        public void setUnitLong(String unitLong) {
            this.unitLong = unitLong;
        }

    }
}
