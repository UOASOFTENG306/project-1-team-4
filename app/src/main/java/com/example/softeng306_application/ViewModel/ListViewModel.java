package com.example.softeng306_application.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.softeng306_application.Entity.Category;

public class ListViewModel extends AndroidViewModel {
    private MutableLiveData<Category> category = new MutableLiveData<>();

    public ListViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Category> getCategory() {
        return category;
    }

    public void setCategory(MutableLiveData<Category> category) {
        this.category = category;
    }
}
