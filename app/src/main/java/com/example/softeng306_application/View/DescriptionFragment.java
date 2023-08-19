package com.example.softeng306_application.View;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.softeng306_application.Adaptor.ViewPageAdapter;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.DetailsViewModel;
import com.google.android.material.tabs.TabLayout;

public class DescriptionFragment extends Fragment {

    private DetailsViewModel detailsViewModel;
    private class ViewHolder {
        TextView descriptionText, categoryNameText;
    }
    public DescriptionFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_description, container, false);
        ViewHolder vh = new ViewHolder();
        this.detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);

        detailsViewModel.getRestaurant().observe(getViewLifecycleOwner(), restaurant -> {
            vh.categoryNameText.setText(restaurant.getName());
            vh.descriptionText.setText(restaurant.getDescription());
        });



        return rootView;
    }
}