package com.example.softeng306_application.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.softeng306_application.Adaptor.ReviewRecyclerAdapter;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.Entity.Review;
import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.DetailsViewModel;

import java.util.ArrayList;
import java.util.List;

public class ReviewFragment extends Fragment {

    private RecyclerView reviewRecyclerView;
    private ReviewRecyclerAdapter reviewRecyclerAdapter;
    private DetailsViewModel detailsViewModel;

    private List<Review> reviewList;

    public ReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_review, container, false);
        reviewRecyclerView = view.findViewById(R.id.recview_reviews);
        detailsViewModel = new ViewModelProvider(requireActivity()).get(DetailsViewModel.class);
        Restaurant restaurant = detailsViewModel.getRestaurant();
        reviewList = detailsViewModel.getReviews();

        reviewRecyclerAdapter = new ReviewRecyclerAdapter(getContext(), reviewList);
        reviewRecyclerView.setAdapter(reviewRecyclerAdapter);
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}