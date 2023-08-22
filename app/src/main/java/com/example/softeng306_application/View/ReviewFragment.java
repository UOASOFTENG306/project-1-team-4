package com.example.softeng306_application.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.softeng306_application.Adaptor.ReviewRecyclerAdapter;
import com.example.softeng306_application.Entity.Review;
import com.example.softeng306_application.R;

import java.util.ArrayList;
import java.util.List;

public class ReviewFragment extends Fragment {

    private RecyclerView reviewRecyclerView;
    private ReviewRecyclerAdapter reviewRecyclerAdapter;

    private List<Review> reviewList;

    public ReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_review, container, false);
        reviewRecyclerView = view.findViewById(R.id.recview_reviews);

        initialiseData();

        reviewRecyclerAdapter = new ReviewRecyclerAdapter(getContext(), reviewList);
        reviewRecyclerView.setAdapter(reviewRecyclerAdapter);
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    public void initialiseData() {
        Review rev1 = new Review("luke", "this place is great");
        Review rev2 = new Review("kai", "this place is ok");
        Review rev3 = new Review("naren", "this place is rank");
        Review rev4 = new Review("luke", "this place is great");
        Review rev5 = new Review("kai", "this place is ok");
        Review rev6 = new Review("naren", "this place is rank");
        Review rev7 = new Review("luke", "this place is great");
        Review rev8 = new Review("kai", "this place is ok");
        Review rev9 = new Review("naren", "this place is rank");
        reviewList = new ArrayList<Review>();
        reviewList.add(rev1);
        reviewList.add(rev2);
        reviewList.add(rev3);
        reviewList.add(rev4);
        reviewList.add(rev5);
        reviewList.add(rev6);
        reviewList.add(rev7);
        reviewList.add(rev8);
        reviewList.add(rev9);
    }
}