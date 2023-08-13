package com.example.softeng306_application.Repository;

import com.example.softeng306_application.Entity.Category;

import java.util.List;

public interface ICategoryRepository {
    CategoryRepository getInstance();

    List<Category> getCategories();
}
