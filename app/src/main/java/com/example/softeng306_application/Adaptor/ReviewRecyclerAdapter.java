package com.example.softeng306_application.Adaptor;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softeng306_application.Entity.Category;
import com.example.softeng306_application.Entity.Restaurant;
import com.example.softeng306_application.Entity.Review;
import com.example.softeng306_application.R;
import com.google.api.Distribution;

import java.util.ArrayList;
import java.util.List;

public class ReviewRecyclerAdapter extends RecyclerView.Adapter<ReviewRecyclerAdapter.ReviewViewHolder> {

    Context context;
    private List<Review> reviewList = new ArrayList<>();

    public ReviewRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setReviews(List<Review> reviewList) {
        this.reviewList = reviewList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ReviewRecyclerAdapter.ReviewViewHolder(LayoutInflater.from(context).inflate(R.layout.review_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {

        // TODO: refactor to pull review data from database
        int score = (int) reviewList.get(position).getReviewScore();
        holder.username.setText(reviewList.get(position).getUserID());
        holder.comment.setText(reviewList.get(position).getDescription());
        holder.reviewScoreLayout.removeAllViews();
        for (int i = 0; i < score; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.drawable.review_star);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            holder.reviewScoreLayout.addView(imageView);
        }
    }


    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder{

        TextView username, comment;
        LinearLayout reviewScoreLayout;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.txt_review_username);
            comment = itemView.findViewById(R.id.txt_review_comment);
            reviewScoreLayout = itemView.findViewById(R.id.reviewScoreLayout);
        }

    }
}
