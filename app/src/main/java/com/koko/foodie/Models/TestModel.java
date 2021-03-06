package com.koko.foodie.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestModel {

    @SerializedName("extendedIngredients")
    @Expose
    private List<ExtendedIngredient> extendedIngredients = null;

    public List<ExtendedIngredient> getExtendedIngredients() {
        return extendedIngredients;
    }

    public void setExtendedIngredients(List<ExtendedIngredient> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }

    public class ExtendedIngredient {

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
        private Measures measures;

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

        public Measures getMeasures() {
            return measures;
        }

        public void setMeasures(Measures measures) {
            this.measures = measures;
        }

    }

    public class Measures {

        @SerializedName("us")
        @Expose
        private Us us;
        @SerializedName("metric")
        @Expose
        private Metric metric;

        public Us getUs() {
            return us;
        }

        public void setUs(Us us) {
            this.us = us;
        }

        public Metric getMetric() {
            return metric;
        }

        public void setMetric(Metric metric) {
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

    public class Us {

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