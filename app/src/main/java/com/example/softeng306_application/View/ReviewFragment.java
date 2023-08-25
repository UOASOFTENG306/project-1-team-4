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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softeng306_application.Adaptor.ReviewRecyclerAdapter;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.Entity.Review;
import com.example.softeng306_application.R;
import com.example.softeng306_application.ViewModel.DetailsViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class ReviewFragment extends Fragment {

    private RecyclerView reviewRecyclerView;
    private ReviewRecyclerAdapter reviewRecyclerAdapter;
    private DetailsViewModel detailsViewModel;
    private String reviewComment, reviewScore, restaurantID;

    private class ViewHolder {
        Button reviewButton;
        ImageButton addReviewCommentButton;
        TextInputEditText addReviewInput;
        LinearLayout linearLayoutAddReview;
    }

    public ReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ReviewFragment.ViewHolder vh = new ReviewFragment.ViewHolder();
        View view = inflater.inflate(R.layout.fragment_review_mockup, container, false);
        reviewRecyclerView = view.findViewById(R.id.recview_reviews);
        vh.addReviewInput = view.findViewById(R.id.input_add_review);
        vh.reviewButton = view.findViewById(R.id.btn_add_review);
        vh.addReviewCommentButton = view.findViewById(R.id.btn_add_review_comment);
        vh.linearLayoutAddReview = view.findViewById(R.id.linearLayout_add_review);
        detailsViewModel = new ViewModelProvider(requireActivity()).get(DetailsViewModel.class);

        reviewRecyclerAdapter = new ReviewRecyclerAdapter(getContext());
        reviewRecyclerView.setAdapter(reviewRecyclerAdapter);
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));

        //TODO ADD FIELDS FROM FRONT END THAT WILL POPULATE THESE TWO STRINGS
        reviewScore = "";
        reviewComment = "";
        restaurantID = detailsViewModel.getRestaurant().getValue().getRestaurantID();
//        vh.reviewButton.setOnClickListener(view1 -> {
//
//            // Show add review input field
//            vh.linearLayoutAddReview.setVisibility(View.VISIBLE);
//            // TODO: Bring up keyboard
//
////            String review1 = String.valueOf(vh.addReviewInput.getText());
////            detailsViewModel.addReviews(restaurantID, review1);
//        });

        detailsViewModel.getRestaurant().observe(getViewLifecycleOwner(), restaurant -> {
            detailsViewModel.getReviewsByRestaurant(restaurant.getRestaurantID());
        });

        detailsViewModel.getReviewsList().observe(getViewLifecycleOwner(), reviews -> {
            reviewRecyclerAdapter.setReviews(reviews);
        });

        // OnClickListeners
        showAddReviewComment(vh);
        addReviewComment(vh);

        return view;
    }

    private void showAddReviewComment(ViewHolder vh) {
        vh.reviewButton.setOnClickListener(view1 -> {

            // Show add review input field
            vh.linearLayoutAddReview.setVisibility(View.VISIBLE);
            // TODO: Bring up keyboard

        });
    }
    private void addReviewComment(ViewHolder vh) {
        vh.addReviewCommentButton.setOnClickListener(view -> {
            String review1 = String.valueOf(vh.addReviewInput.getText());
            detailsViewModel.addReviews(restaurantID, review1);
        });

    }
}