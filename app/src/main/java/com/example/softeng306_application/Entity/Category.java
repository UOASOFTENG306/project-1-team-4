package com.example.softeng306_application.Entity;

import java.util.List;

//public class Category {
//
//    public Category() {
//
//    }
//
//    public Category(CategoryType categoryType) {
//        this.categoryType = categoryType;
//    }
//
//    private CategoryType categoryType;
//
//    public CategoryType getCategoryType() {
//        return categoryType;
//    }
//
//    // TODO DELETE THIS METHOD! For testing purposes
//    public void setCategoryType(CategoryType categoryType) { this.categoryType = categoryType; }
//
//}

public abstract class Category implements ICategory {
    String borderColour;
    String audioFileName;
    String categoryType;

    public abstract String getCategoryType();

}
