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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
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

    private class ViewHolder {
        Button reviewButton, submitReviewButton;
        ImageButton addReviewCommentButton;
        TextInputEditText addReviewInput;
        LinearLayout linearLayoutAddReview, linearLayoutRatingPanel, linearLayoutOverallRating, averageScoreLayout;
        RatingBar ratingBar;

        TextView reviewCommentPreview;

    }


    public ReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ReviewFragment.ViewHolder vh = new ReviewFragment.ViewHolder();
        View view = inflater.inflate(R.layout.fragment_review_mockup, container, false);
        reviewRecyclerView = view.findViewById(R.id.recview_reviews);
        vh.addReviewInput = view.findViewById(R.id.input_add_review);
        vh.reviewButton = view.findViewById(R.id.btn_add_review);
        vh.addReviewCommentButton = view.findViewById(R.id.btn_add_review_comment);
        vh.linearLayoutAddReview = view.findViewById(R.id.linearLayout_add_review);
        vh.ratingBar = view.findViewById(R.id.rb_ratingBar);
        vh.linearLayoutRatingPanel = view.findViewById(R.id.linearLayout_rating_panel);
        vh.linearLayoutOverallRating = view.findViewById(R.id.linearLayout_overall_rating);
        vh.averageScoreLayout = view.findViewById(R.id.averageScoreLayout);
        vh.submitReviewButton = view.findViewById(R.id.btn_submit_review);
        vh.reviewCommentPreview = view.findViewById(R.id.txt_review_comment_preview);

        detailsViewModel = new ViewModelProvider(requireActivity()).get(DetailsViewModel.class);
        reviewRecyclerAdapter = new ReviewRecyclerAdapter(getContext());
        reviewRecyclerView.setAdapter(reviewRecyclerAdapter);
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));

        String restaurantID = detailsViewModel.getRestaurant().getRestaurantID();

        getReviews(restaurantID);

        detailsViewModel.getAverageReviewScore().observe(getViewLifecycleOwner(), averageScore -> {
            vh.averageScoreLayout.removeAllViews();
            for (int i = 0; i < averageScore; i++) {

                // Star image view
                ImageView imageView = new ImageView(requireActivity());
                int width = 60;
                int height = 60;
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                imageView.setImageResource(R.drawable.review_star); // Set your image resource here
                imageView.setLayoutParams(new LinearLayout.LayoutParams(
                        width,
                        height
                ));
                vh.averageScoreLayout.addView(imageView);
            }
        });

        // OnClickListeners
        showAddReviewComment(vh);
        addReviewComment(vh,restaurantID);

        return view;
    }

    private void getReviews(String restaurantID){
        detailsViewModel.getReviewsList(restaurantID).observe(getViewLifecycleOwner(), reviews -> {
            reviewRecyclerAdapter.setReviews(reviews);
            detailsViewModel.updateCurrentList(reviews);
            detailsViewModel.calculateAverageReviewScoreFromList(reviews);
        });
    }

    private void showAddReviewComment(ViewHolder vh) {
        vh.reviewButton.setOnClickListener(view1 -> {

            // Show add review input field
            vh.linearLayoutAddReview.setVisibility(View.VISIBLE);

            // Show keyboard
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
            vh.addReviewInput.requestFocus();
        });
    }
    private void addReviewComment(ViewHolder vh, String restaurantID) {
        vh.addReviewCommentButton.setOnClickListener(view -> {
            // Show rating panel
            vh.linearLayoutRatingPanel.setVisibility(View.VISIBLE);
            vh.linearLayoutAddReview.setVisibility(View.GONE);
            vh.linearLayoutOverallRating.setVisibility(View.GONE);

            // Save comment
            String reviewComment = String.valueOf(vh.addReviewInput.getText());
            vh.reviewCommentPreview.setText("\"" + reviewComment + "\"");


            // Hide keyboard
            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            submitReview(vh, restaurantID, reviewComment);
        });
    }

    private void submitReview(ViewHolder vh, String restaurantID, String reviewComment) {
        vh.submitReviewButton.setOnClickListener(view -> {
            // Show overall rating
            vh.linearLayoutOverallRating.setVisibility(View.VISIBLE);
            vh.linearLayoutRatingPanel.setVisibility(View.GONE);

            // Save rating
            float score = vh.ratingBar.getRating();
            detailsViewModel.addReviews(restaurantID, reviewComment, score);
            detailsViewModel.getUsername().observe(getViewLifecycleOwner(), username -> {
                Review review = new Review(reviewComment, username, score);
                detailsViewModel.addToReviewsList(reviewComment, username, score);
                    });
            detailsViewModel.getReviewsCurrentList().observe(getViewLifecycleOwner(), reviews -> {
                reviewRecyclerAdapter.setReviews(reviews);
            });
        });
    }
}

