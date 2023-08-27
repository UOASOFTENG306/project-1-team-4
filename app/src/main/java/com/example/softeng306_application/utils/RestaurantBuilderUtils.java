package com.example.softeng306_application.utils;

import com.example.softeng306_application.entity.Asian;
import com.example.softeng306_application.entity.Cafe;
import com.example.softeng306_application.entity.Category;
import com.example.softeng306_application.entity.European;
import com.example.softeng306_application.entity.FastFood;
import com.example.softeng306_application.entity.Restaurant;
import com.example.softeng306_application.entity.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestaurantBuilderUtils {
    private RestaurantBuilderUtils(){}

    public static Restaurant restaurantBuilder(Map<String, Object> data) {

        Category category;
        String restaurantID = (String) data.get("restaurantID");
        String name = (String) data.get("name");
        String description = (String) data.get("description");
        String location = (String) data.get("location");

        Map<String, Object> nestedField = (Map<String, Object>) data.get("category");

        String categoryType = (String) nestedField.get("categoryType");
        String logoImage = (String) data.get("logoImage");
        String price = (String) data.get("price");

        List<Review> reviews = new ArrayList<>();
        List<Map<String, Object>> reviewsArray = (List<Map<String, Object>>) data.get("reviews");
        if (reviewsArray != null || !reviewsArray.isEmpty()) {
            for (Map<String, Object> review : reviewsArray) {
                String username = (String) review.get("userID");
                String comment =(String) review.get("description");
                Number reviewScore = (Number) review.get("reviewScore");
                reviews.add(new Review(username, comment, reviewScore.floatValue()));
            }
        }

        switch (categoryType){
            case "FAST FOOD":
                category = new FastFood();
                break;
            case "EUROPEAN":
                category = new European();
                break;
            case "ASIAN":
                category = new Asian();
                break;
            case "CAFE":
                category = new Cafe();
                break;
            default:
                category = new FastFood();
        }

        return new Restaurant(restaurantID, new ArrayList<>(), name, description, location, category, logoImage, price, reviews);
    }
}
