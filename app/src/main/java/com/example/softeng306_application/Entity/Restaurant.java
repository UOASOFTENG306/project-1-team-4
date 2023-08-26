package com.example.softeng306_application.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Restaurant implements Parcelable {
    private String restaurantID;
    private String name;
    private Category category;
    private List<String> backgroundImageUrls;
    private String logoImage;
    private List<Review> reviews = new ArrayList<>();
    private String description;
    private String location;

    private String price;

    public Restaurant() {
    }

    //TODO TEST CONSTRUCTOR - FOR KAI TO DELETE
    public Restaurant(String name, String logoImage, Category category) {
        this.name = name;
        this.logoImage = logoImage;
        this.category = category;
    }
    public Restaurant(String restaurantID, String name, String description, String location, String logoImage, String price) {
        this.restaurantID = restaurantID;
        this.name = name;
        this.description = description;
        this.location = location;
        this.logoImage = logoImage;
        this.price = price;
    }

    public Restaurant(String restaurantID, String name, String description, String location, Category category, String logoImage, String price) {
        //TODO ADD REVIEWS
        this.restaurantID = restaurantID;
        this.name = name;
        this.description = description;
        this.location = location;
        this.category = category;
        this.logoImage = logoImage;
        this.price = price;
        this.reviews = new ArrayList<>();
    }

    public Restaurant(String restaurantID, List<String> backgroundImageUrls, String name, String description, String location, Category category, String logoImage, String price) {
        //TODO ADD REVIEWS
        this.restaurantID = restaurantID;
        this.backgroundImageUrls = backgroundImageUrls;
        this.name = name;
        this.description = description;
        this.location = location;
        this.category = category;
        this.logoImage = logoImage;
        this.price = price;
    }

    public Restaurant(String restaurantID, List<String> backgroundImageUrls, String name, String description, String location, Category category, String logoImage, String price, List<Review> reviews) {
        this.restaurantID = restaurantID;
        this.backgroundImageUrls = backgroundImageUrls;
        this.name = name;
        this.description = description;
        this.location = location;
        this.category = category;
        this.logoImage = logoImage;
        this.price = price;
        this.reviews = reviews;
    }

    protected Restaurant(Parcel in) {
        restaurantID = in.readString();
        name = in.readString();
        category = in.readParcelable(Category.class.getClassLoader());
        logoImage = in.readString();
        description = in.readString();
        location = in.readString();
        price = in.readString();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getRestaurantID() {
        return restaurantID;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getBackgroundImages() {
        return backgroundImageUrls;
    }

    public String getLogoImage() { return logoImage; };

    public List<Review> getReviews() {
        return reviews;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getPrice() { return price; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(restaurantID);
        dest.writeString(name);
        dest.writeParcelable(category, flags);
        dest.writeString(logoImage);
        dest.writeString(description);
        dest.writeString(location);
        dest.writeString(price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(restaurantID, that.restaurantID) && Objects.equals(name, that.name) && Objects.equals(category, that.category) && Objects.equals(backgroundImageUrls, that.backgroundImageUrls) && Objects.equals(logoImage, that.logoImage) && Objects.equals(reviews, that.reviews) && Objects.equals(description, that.description) && Objects.equals(location, that.location) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantID, name, category, backgroundImageUrls, logoImage, reviews, description, location, price);
    }
}
