package com.example.softeng306_application.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Objects;

public abstract class Category implements ICategory, Parcelable {
    String borderColour;
    String audioFileName;
    String categoryType;

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(borderColour);
        dest.writeString(audioFileName);
        dest.writeString(categoryType);
    }

    public abstract String getCategoryType();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(borderColour, category.borderColour) && Objects.equals(audioFileName, category.audioFileName) && Objects.equals(categoryType, category.categoryType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borderColour, audioFileName, categoryType);
    }
}
