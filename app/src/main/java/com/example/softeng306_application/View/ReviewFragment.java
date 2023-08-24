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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softeng306_application.Adaptor.ReviewRecyclerAdapter;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.Entity.Review;
import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.DetailsViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ReviewFragment extends Fragment {

    private RecyclerView reviewRecyclerView;
    private ReviewRecyclerAdapter reviewRecyclerAdapter;
    private DetailsViewModel detailsViewModel;

    private List<Review> reviewList;

    private class ViewHolder {
        Button reviewButton;
        TextInputEditText editReview;
    }

    public ReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ReviewFragment.ViewHolder vh = new ReviewFragment.ViewHolder();
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        reviewRecyclerView = view.findViewById(R.id.recview_reviews);
        vh.editReview = view.findViewById(R.id.reviewTest);
        vh.reviewButton = view.findViewById(R.id.btn_add_review);
        detailsViewModel = new ViewModelProvider(requireActivity()).get(DetailsViewModel.class);
        //TODO ADD FIELDS FROM FRONT END THAT WILL POPULATE THESE TWO STRINGS
        String rating = "";
        String comment = "";
        String restaurantID = detailsViewModel.getRestaurant().getValue().getRestaurantID();
        vh.reviewButton.setOnClickListener(view1 -> {
            String review1 = String.valueOf(vh.editReview.getText());
            detailsViewModel.addReviews(restaurantID, review1);
        });

        detailsViewModel.getRestaurant().observe(getViewLifecycleOwner(), restaurant -> {
            reviewList = detailsViewModel.getReviewsByRestaurant(restaurant.getRestaurantID());
            detailsViewModel.getReviewsList().observe(getViewLifecycleOwner(), reviews -> {
                //reviewRecyclerAdapter.setReviews(reviews)
                reviewRecyclerAdapter = new ReviewRecyclerAdapter(getContext(), reviewList);
                reviewRecyclerView.setAdapter(reviewRecyclerAdapter);
                reviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            });
        });

        return view;
    }


}