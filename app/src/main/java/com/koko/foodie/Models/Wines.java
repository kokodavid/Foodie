//package com.koko.foodie.Models;
//
//import java.util.List;
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class Wines {
//
//    @SerializedName("pairedWines")
//    @Expose
//    private List<String> pairedWines = null;
//    @SerializedName("pairingText")
//    @Expose
//    private String pairingText;
//    @SerializedName("productMatches")
//    @Expose
//    private List<Wines> productMatches = null;
//
//    public List<String> getPairedWines() {
//        return pairedWines;
//    }
//
//    public void setPairedWines(List<String> pairedWines) {
//        this.pairedWines = pairedWines;
//    }
//
//    public String getPairingText() {
//        return pairingText;
//    }
//
//    public void setPairingText(String pairingText) {
//        this.pairingText = pairingText;
//    }
//
//    public List<ProductMatch> getProductMatches() {
//        return productMatches;
//    }
//
//    public void setProductMatches(List<Wines> productMatches) {
//        this.productMatches = productMatches;
//    }
//
//
//    public class ProductMatch {
//
//        @SerializedName("id")
//        @Expose
//        private Integer id;
//        @SerializedName("title")
//        @Expose
//        private String title;
//        @SerializedName("description")
//        @Expose
//        private String description;
//        @SerializedName("price")
//        @Expose
//        private String price;
//        @SerializedName("imageUrl")
//        @Expose
//        private String imageUrl;
//        @SerializedName("averageRating")
//        @Expose
//        private Float averageRating;
//        @SerializedName("ratingCount")
//        @Expose
//        private Float ratingCount;
//        @SerializedName("score")
//        @Expose
//        private Float score;
//        @SerializedName("link")
//        @Expose
//        private String link;
//
//        public Integer getId() {
//            return id;
//        }
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//
//        public String getPrice() {
//            return price;
//        }
//
//        public void setPrice(String price) {
//            this.price = price;
//        }
//
//        public String getImageUrl() {
//            return imageUrl;
//        }
//
//        public void setImageUrl(String imageUrl) {
//            this.imageUrl = imageUrl;
//        }
//
//        public Float getAverageRating() {
//            return averageRating;
//        }
//
//        public void setAverageRating(Float averageRating) {
//            this.averageRating = averageRating;
//        }
//
//        public Float getRatingCount() {
//            return ratingCount;
//        }
//
//        public void setRatingCount(Float ratingCount) {
//            this.ratingCount = ratingCount;
//        }
//
//        public Float getScore() {
//            return score;
//        }
//
//        public void setScore(Float score) {
//            this.score = score;
//        }
//
//        public String getLink() {
//            return link;
//        }
//
//        public void setLink(String link) {
//            this.link = link;
//        }
//
//    }
//
//}