package com.example.softeng306_application.dataprovider;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.softeng306_application.Entity.Asian;
import com.example.softeng306_application.Entity.Cafe;
import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.European;
import com.example.softeng306_application.Entity.FastFood;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CategoriesFirestoreDataProvider {

    public CategoriesFirestoreDataProvider() {
    }

    public void addCategoriesToFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<Category> categoryList = getCategory();
        for (Category category : categoryList) {
            db.collection("categories").document(category.getCategoryType()).set(category).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("Categories Collection Add", category.getCategoryType() + " added.");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Log.w("Numbers Collection Add", category.getCategoryType()+ " NOT added.");
                }
            });
        }
    }

    private List<Category> getCategory() {
        List<Category> categoryArrayList = new ArrayList<>();
        Category categoryAsian = new Asian();
        Category categoryEuropean = new European();
        Category categoryFastFood = new FastFood();
        Category categoryCafe = new Cafe();
        categoryArrayList.add(categoryAsian);
        categoryArrayList.add(categoryEuropean);
        categoryArrayList.add(categoryFastFood);
        categoryArrayList.add(categoryCafe);
        return categoryArrayList;
    }

}

