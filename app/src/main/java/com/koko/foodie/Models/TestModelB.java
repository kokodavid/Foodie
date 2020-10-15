package com.koko.foodie.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestModelB {
    @SerializedName("extendedIngredients")
    @Expose
    private List<ExtendedIngredient> extendedIngredients = null;
    @SerializedName("diets")
    @Expose
    private List<String> diets = null;
    @SerializedName("analyzedInstructions")
    @Expose
    private List<AnalyzedInstruction> analyzedInstructions = null;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("readyInMinutes")
    @Expose
    private Integer readyInMinutes;
    @SerializedName("servings")
    @Expose
    private Integer servings;
    @SerializedName("image")
    @Expose
    private String image;

    public List<ExtendedIngredient> getExtendedIngredients() {
        return extendedIngredients;
    }

    public void setExtendedIngredients(List<ExtendedIngredient> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getDiets() {
        return diets;
    }

    public void setDiets(List<String> diets) {
        this.diets = diets;
    }

    public List<AnalyzedInstruction> getAnalyzedInstructions() {
        return analyzedInstructions;
    }

    public void setAnalyzedInstructions(List<AnalyzedInstruction> analyzedInstructions) {
        this.analyzedInstructions = analyzedInstructions;
    }

    public class AnalyzedInstruction {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("steps")
        @Expose
        private List<Step> steps = null;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Step> getSteps() {
            return steps;
        }

        public void setSteps(List<Step> steps) {
            this.steps = steps;
        }

    }
    public class Step {

        @SerializedName("number")
        @Expose
        private Integer number;
        @SerializedName("step")
        @Expose
        private String step;
        @SerializedName("ingredients")
        @Expose
        private List<Ingredient> ingredients = null;

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public String getStep() {
            return step;
        }

        public void setStep(String step) {
            this.step = step;
        }

        public List<Ingredient> getIngredients() {
            return ingredients;
        }

        public void setIngredients(List<Ingredient> ingredients) {
            this.ingredients = ingredients;
        }

    }

    public class Ingredient {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("localizedName")
        @Expose
        private String localizedName;
        @SerializedName("image")
        @Expose
        private String image;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocalizedName() {
            return localizedName;
        }

        public void setLocalizedName(String localizedName) {
            this.localizedName = localizedName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

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
        private TestModel.Measures measures;

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

        public TestModel.Measures getMeasures() {
            return measures;
        }

        public void setMeasures(TestModel.Measures measures) {
            this.measures = measures;
        }

    }

    public class Measures {

        @SerializedName("us")
        @Expose
        private TestModel.Us us;
        @SerializedName("metric")
        @Expose
        private TestModel.Metric metric;

        public TestModel.Us getUs() {
            return us;
        }

        public void setUs(TestModel.Us us) {
            this.us = us;
        }

        public TestModel.Metric getMetric() {
            return metric;
        }

        public void setMetric(TestModel.Metric metric) {
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