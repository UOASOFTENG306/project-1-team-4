package com.example.softeng306_application.dataprovider;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.softeng306_application.Entity.Asian;
import com.example.softeng306_application.Entity.Cafe;
import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.CategoryType;
import com.example.softeng306_application.Entity.European;
import com.example.softeng306_application.Entity.FastFood;
import com.example.softeng306_application.Entity.Restaurant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RestaurantFirestoreDataProvider {

    public RestaurantFirestoreDataProvider() {

    }

    // Add number documents to Firestore
    public void addRestaurantToFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<Restaurant> restaurantsList = getRestaurants();
        for (Restaurant restaurant : restaurantsList) {
            db.collection("restaurants").document("restaurant " + restaurant.getRestaurantID()).set(restaurant).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("Numbers Collection Add", "number " + restaurant.getRestaurantID() + " added.");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Log.w("Numbers Collection Add", "number " + restaurant.getRestaurantID() + " NOT added.");
                }
            });
        }
    }

    private List<Restaurant> getRestaurants() {
        List<Restaurant> restaurantsArrayList = new ArrayList<>();
        Restaurant r1 = new Restaurant("1", "McDonalds", "Junk Food", "Auckland", new FastFood());
        Restaurant r2 = new Restaurant("2", "KFC", "Fried Chicken", "Auckland", new FastFood());
        Restaurant r3 = new Restaurant("3", "Burger King", "Better Burgers than Maccas", "Auckland", new FastFood());
        Restaurant r4 = new Restaurant("4", "Federal Delicatessen", "A popular cafe known for its fresh and delicious breakfast and lunch options.", "Wellington", new Cafe());
        Restaurant r5 = new Restaurant("5", "Dear Jervois ", "A trendy cafe that serves creative and beautifully presented dishes along with specialty coffee.", "Auckland", new Cafe());
        Restaurant r6 = new Restaurant("6", "C1 Espresso ", "A unique cafe located in a historic building, famous for its quirky interior and pneumatic tube system that delivers food to customers", "Christchurch", new Cafe());
        Restaurant r7 = new Restaurant("7", "Aroy Thai Street Food ", "A popular Thai restaurant offering authentic Thai street food flavors.", "Auckland", new Asian());
        Restaurant r8 = new Restaurant("8", "Little Penang", "Known for its Malaysian cuisine, including dishes like nasi lemak and char kway teow.", "Wellington", new Asian());
        Restaurant r9 = new Restaurant("9", "O'Connell Street Bistro", "A European-style bistro offering a seasonal menu with a focus on local produce.", "Auckland", new European());
        Restaurant r10 = new Restaurant("10", "Cibo Parnell", "A contemporary European restaurant offering a diverse range of dishes and a sophisticated dining experience.", "Britomart", new European());
        restaurantsArrayList.add(r1);
        restaurantsArrayList.add(r2);
        restaurantsArrayList.add(r3);
        restaurantsArrayList.add(r4);
        restaurantsArrayList.add(r5);
        restaurantsArrayList.add(r6);
        restaurantsArrayList.add(r7);
        restaurantsArrayList.add(r8);
        restaurantsArrayList.add(r9);
        restaurantsArrayList.add(r10);
        return restaurantsArrayList;
    }

}






