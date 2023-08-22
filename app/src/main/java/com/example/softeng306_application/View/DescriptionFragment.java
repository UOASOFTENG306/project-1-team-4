package com.example.softeng306_application.View;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
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


    public static DescriptionFragment newInstance(Restaurant restaurant) {
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle args = new Bundle();
        args.putParcelable("restaurant", restaurant);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsViewModel = new ViewModelProvider(requireActivity()).get(DetailsViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_description, container, false);
        ViewHolder vh = new ViewHolder();
        vh.descriptionText = rootView.findViewById(R.id.txt_description);
        YoYo.with(Techniques.BounceInLeft).duration(700).playOn(vh.descriptionText);

        vh.categoryNameText = rootView.findViewById(R.id.txt_category);
        YoYo.with(Techniques.FadeInDown).duration(700).playOn(vh.categoryNameText);
        detailsViewModel.getRestaurant().observe(getViewLifecycleOwner(), new Observer<Restaurant>() {
            @Override
            public void onChanged(Restaurant restaurant) {
                 vh.descriptionText.setText(restaurant.getDescription());
                 vh.categoryNameText.setText(restaurant.getCategory().getCategoryType());
            }
        });
        return rootView;
    }
}